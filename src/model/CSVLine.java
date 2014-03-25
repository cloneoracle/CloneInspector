package model;

public class CSVLine {
    
	graphicdiff.Util util = new graphicdiff.Util();
    
    private String fullLine = "";
    int lineNumber = 0;
    private int levenDistance = 0;
    String[] directoryFileNames = new String[2];
    String[] methods = new String[2];
    String[] fullmethods = new String[2];
    String[] ctagpath = new String[2];
    String[] shortPath = new String[2];
    String[] sourcepath = new String[2];
    String[] className = new String[2];
    String[] ctagpathAndFile = new String[2];

    public CSVLine(String fullLine, int lineNumber, String inputReportName) {
    	
    	// convert each line into a specific value so there do not have to be two types of "IF" needed in the future
    	   
    	
 //   	System.out.println("Dan is here");
//    	System.exit(0);
    	
    	
    	// Kill the application if specific values are not found
    	if(!fullLine.contains("txt")){
    		System.out.println("Invalid input format - Application will now exit - From: CSVLine");
    		System.exit(0);
    	}
    	

    	// Ugly hack
    	// Refactor this
    	if(!fullLine.contains(".txt")){
    		fullLine = convertInputLine(fullLine);
    	}

        this.fullLine = fullLine;
        this.lineNumber = lineNumber;
        
        for (int i=0; i<2; i++) {
            
        	// Get the 1st or 2nd "clone" in code clone
            String half = fullLine.split(",")[0]; 
            half = half.split(".txt-")[i];
            
            // if half does not end in .txt then add it 
            if(!half.endsWith(".txt")){
            	half+=".txt";
            }
            
          // If target named the same as class, the incorrect file is going to be removed.
          
            // Remove the source name from the "half" -  There may be a better way to do this
            half = half.toLowerCase().replace(inputReportName.toLowerCase(), ""); 
            
            // If half starts with a "." , then remove it
            half = half.startsWith(".") ? half.substring(1) : half;
            
            levenDistance = Integer.parseInt(fullLine.split(",")[1].replace(".0", "").trim()); //updated
           
            String method = half.substring(half.indexOf(".."));
           
           String directoryFileName = half.replace(method, "");
           
           String[] str1Array = directoryFileName.split("\\" + "."); // always look for this slash, for any OS
           StringBuilder tempPath = new StringBuilder();
           for(int b=0; b<str1Array.length-1;b++){
        	   tempPath.append(util.getSeperatorChar() + str1Array[b]);
           }
            
           className[i] = str1Array[str1Array.length-1];         
           shortPath[i] = tempPath.toString(); // Short Path only contains a small segment of the file structure
           sourcepath[i] = "clonesource" + util.getSeperatorChar() + inputReportName.toLowerCase() + shortPath[i];
           ctagpath[i] ="clonectag" + util.getSeperatorChar() + inputReportName.toLowerCase()+"_copy_ctag_output" + util.getSeperatorChar() + inputReportName.toLowerCase()+ "_copy" + shortPath[i];
           ctagpathAndFile[i] = ctagpath[i]+ util.getSeperatorChar() + className[i]+".c.txt";
           methods[i] = method.replace(".txt", "").replace(".", "");
           directoryFileNames[i] = sourcepath[i] + util.getSeperatorChar() + className[i]+".c";
            
            
            // Useful for debugging
         /*
            System.out.println(methods[i]);
          
            System.out.println("Classname: " + className[i]);
            System.out.println("sourcepath: " + sourcepath[i]);
            System.out.println("Ctag path: " + ctagpath[i]);
            
            System.out.println("Source and File: " + directoryFileNames[i]);
            System.out.println("Ctag File: " + ctagpathAndFile[i]);
            System.exit(0);
           */
            
        }
    	
    }
    
    public String[] getDirectoryFileName() {
        return directoryFileNames;
    }
    
    public String[] getMethods() {
        return methods;
    }

    public int getLevenDistance() {
        return levenDistance;
    }

    public void setLevenDistance(int levenDistance) {
        this.levenDistance = levenDistance;
    }

    public String getFullLine() {
        return fullLine;
    }

    public void setFullLine(String fullLine) {
        this.fullLine = fullLine;
    }
    
    
    
    
	// Convert all input into a "friendly format"
	private String convertInputLine(String input){
		
				
		// This method is kind of messy, but is needed for two different types of input format
		// May slow down the application a bit, but should not be all that much
		
		final String levenValue = input.split(",")[1]; // Store the levenstein value for later
	
		if(!input.contains("()")){ // Should never get this far
			System.out.println("Error - Invalid input format");
			System.exit(0);
		}
		
		// Begin the conversion process
		String temp = input;
		temp=temp.replace("()", ".txt-");

        String halfa = replaceLast(temp.split(".txt-")[0],".","..").replace("/", ".")+".txt";
        String halfb = removeFirstChar(replaceLast(temp.split(".txt-")[1],".","..").replace("/", "."))+".txt";

		return halfa + "-" + halfb + ", " + levenValue;
	}

    private String removeFirstChar(String s){
		return s.substring(1);
	}
	
	
	private String replaceLast(String string, String substring, String replacement){
	  int index = string.lastIndexOf(substring);
	  if (index == -1)
	    return string;
	  return string.substring(0, index) + replacement
	          + string.substring(index+substring.length());
	}
	
    
}
