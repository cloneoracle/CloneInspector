package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Model knows NOTHING about view / context. Could be command line, GUI, web..
 * @author Luke
 */
public class Model {
    
	
	
    private ArrayList<CSVLine> clones = new ArrayList();
    private ArrayList<String[]> cloneMethods = new ArrayList();
   
    private File csvFile; // The file object for the CSV file. Used for loading and then again for saving.
    
    private String inputFileName;
    
    // change the values to this
    private HashMap<String, String> clonesTypesMap = new HashMap();
    
    int currentLine = 0;
    graphicdiff.Util util = new graphicdiff.Util(); // Reference utility class
    
    // Takes in the directory of the CSV file
    public void processFile(File file) {
    	

    	csvFile = file;
    	
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String line = reader.readLine();
            System.out.println(line);

            int lineNumber = 1;
            while (line != null) {
                this.addLine((line.split(",")[0]+","+line.split(",")[1]), lineNumber);
                addLegacy(line);
                line = reader.readLine();
                lineNumber++;
            }
           System.out.println("Clones loaded!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // adds to the clonesTypesMap if already a analyzed clone
    public void addLegacy(String line) {
        if (line.split(",").length > 2) {
            String numberClone = line.split(",")[2];
            clonesTypesMap.put(numberClone, line.substring(line.indexOf(line.split(",")[3])));
        }
    }
   
    // Save all the notes the user wrote
    // Format: #x, notes go here
    public void saveNotes() {
    	System.out.println("Export Notes");
        try {
        	String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HHmm").format(Calendar.getInstance().getTime());
            
        	// Create a new output file
        	BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile.getAbsolutePath(), false));

            writer.write("\n");
            for (int i=0; i<clones.size(); i++) {
                if (clonesTypesMap.containsKey(String.valueOf(i))) {
                	String key = String.valueOf(i);
                    String value = clonesTypesMap.get(key);
                    writer.write(clones.get(i).getFullLine()+","+String.valueOf(key)+","+value.replaceAll("\n", " ")+"\n");
                } else {
                    writer.write(clones.get(i).getFullLine()+"\n");
                }
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JPanel(), "Export Failed!", "Export Status", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Add a note to the map
    // Saves current info
    public void addNotes(String s) {
    	clonesTypesMap.put(String.valueOf(currentLine), s);
    }
    
    
    // Given a CSVline (direct from file), returns method content
    
    // LC: MISTAKE: Should be string array of method contents
    public String[] retreiveMethod(CSVLine csv) {
         	
    	// array of size 2 for method contents
        String methods[] = new String[2];
        // Serves no purpose other then getting to curr directory
        File file = new File("");
        
        // Directory of C File (used for both clones)
        String dir = "";
        // Directory of corresponding ctag file (used for both clones)
        String ctagDir = "";
        // string array of 2 for the ctag method names (the code clone methods)
        String ctagFullMethodName[] = new String[2];
        BufferedReader reader;
        try {
            // For both clones in the CSV
            for (int i=0; i<2; i++) {
                // Get the nth directory code clone
            	
                dir = file.getAbsolutePath() +util.getSeperatorChar()+ csv.directoryFileNames[i];
                // navigate to ctag file (source folder hard coded in - sorry)
            //    ctagDir = dir.replace("clonesource", "clonectag") + ".txt"; // Luke's method
               ctagDir = file.getAbsolutePath() +util.getSeperatorChar()+ csv.ctagpathAndFile[i];
  
                reader = new BufferedReader(new FileReader(ctagDir));
                String line = reader.readLine();
                  
                // Find the method in the ctag file
                while (line != null) {
                //	System.out.println("143"); 
                	if (line.split("\\s++")[0].toLowerCase().equals(csv.methods[i].toLowerCase()) && line.contains("/^") && line.contains("$/")) {
                        // Adjust index because it starts on the start of the pattern
                        int sub1 = line.indexOf("/^")+2;
                        int sub2 = line.indexOf("$/");
                        ctagFullMethodName[i] = line.substring(sub1, sub2);
                        break;
                    }
                    line = reader.readLine();
                }
                
          
                
                reader.close();
                
                // Now we go into the actual C file and find the ctag method we just got 
                reader = new BufferedReader(new FileReader(dir));
                line = reader.readLine();
                while (line != null) {

                    if (line.contains(ctagFullMethodName[i])) {
                        // use a stack, pop when we see a } and push for {
                        Stack<String> s = new Stack();   
                        // Get the first { to start the stack
                        while (!line.contains("{")) {
                            methods[i] += line + "\n";
                            //System.out.println("Current Method Line:"+line);
                            line = reader.readLine();
                        }
                        s.push("{");
                        // In the rare case that there is a "}" on the same line
                        if (line.contains("}")) {
                            s.pop();
                            methods[i] = line;
                        }
                        line = reader.readLine();
                        
                        // Keep going through the method content until stack empty
                        while (s.empty()==false) {
                            if (!reader.ready() && line == null) {
                                System.err.println("EOF BUT STACK !EMPTY");
                                methods[i] += "EOF?";
                                break;
                            }
                            if (line.contains("{")) {
                                for (int a=0; a<countSymbols(line, '{'); a++) {
                                    s.push("{");
                                }
                                
                            } if (line.contains("}")) {
                                for (int a=0; a<countSymbols(line, '}'); a++) {
                                    s.pop();
                                }
                                //System.out.println(s.size());
                            }
                            methods[i] += line + "\n";
                            //System.out.println("Current Method Line:"+line);
                            line = reader.readLine();
                        } 
                    // send output to jeditorpane or something in form of string?
                    }
                    line = reader.readLine();
                }
                reader.close();  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return methods;
    }
    
    // Return current leven distance
    public int getCurrlevenDistance() {
         return clones.get(currentLine).getLevenDistance();
    }
    
    public int getCurentLine() {
        return currentLine;
    }
    
    public String getCurrFileInfo() {
        return clones.get(currentLine).getFullLine();
    }
        
    // See how many of a given symbol are on a line
    public int countSymbols(String line, char sym) {
        int count = 0;
        for (int i=0; i<line.length(); i++) {
            if (line.charAt(i)==sym) {
                count++;
            }
        }
        return count;
    }
    
    // Add each line from the CSV file here (used by process file)
    public void addLine(String fullLine, int lineNumber) {
    	
    	
    	// spelled both ways due to misspellings
        CSVLine clone = new CSVLine(fullLine, lineNumber,csvFile.getName().toLowerCase().replace("_comparisonreport.csv", "").replace("_comparisionreport.csv", ""));
        																							  
        // Add the clone row .csv object to the arraylist
        clones.add(clone);

        // Get the full method from the clone item
        cloneMethods.add(retreiveMethod(clone));
        
    }
    
    // Sifts to next element in list of CSVlines (sifts cursor forward)
    public String[] next(int amount) {
    	int newLine = currentLine + amount;
    	System.out.println("next");
        if (newLine < clones.size()) {
         //   currentLine++;
        	currentLine=newLine;
            System.out.println("Current line is: " + currentLine);
            return cloneMethods.get(currentLine);
        } else {
        	System.out.println("Not enough items to cycle to.");
            return null;
        }
    }
    
    // Sifts to previous element (sifts cursor back)
    public String[] prev(int amount) {
    	int newLine = currentLine - amount;
      	System.out.println("prev");
       if (newLine >= 0) {
         //   currentLine--;
    	   currentLine=newLine;
            System.out.println("Current line is: " + currentLine);
            return cloneMethods.get(currentLine);
        } else {
        	System.out.println("Not enough items to cycle to.");
            return null;
        }
    }
    
    public String[] skip(int SkipNumber) {
        if (SkipNumber < cloneMethods.size() && SkipNumber >= 0) {
            currentLine = SkipNumber;
            return cloneMethods.get(SkipNumber);
        } else {
            return null;
        }
    }
    
    public String getCloneTypes() {
    	String retVal = "";
        if (clonesTypesMap.containsKey(String.valueOf(currentLine))) { 
            retVal= clonesTypesMap.get(String.valueOf(currentLine));
        } 
        return retVal;
    }
    
 // Return the total number of clones in the input file
    public int getTotalNumberOfCloneCandidates(){
    	return clones.size();
    }
  
    public int getCurrentLine(){
    	return currentLine;
    }
    
}
