package CloneInspector;

import model.Model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;

/**
 * Controller processes user requests - Inner classes are observer patterns
 */
public class Controller {

	
	
	Util util = new Util();;
    Model model;
    View view;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        
        view.addBrowseListener(new browseListener());
        view.addLeftListener(new leftListener());
        view.addRightListener(new rightListener());
        view.addSkipListener(new skipListener());
     //   view.addSaveNoteListner(new saveNoteListener());
          
        view.addCloneChangeListener(new cloneChangeListener());
        
        
        // These next few lines are used for debugging only
       // final String reportName = "httpd-2.2.14_comparisonReport.csv";
        final String reportName = "smallApache_comparisionReport.csv";
        
        File inputfile = new File("Reports" + util.getSeperatorChar() + reportName);
         //     model.processFile(inputfile);  
    }
    

    class cloneChangeListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		// update the record - This is done here as well because the user cannot always 
    		//-		move backwards and forwards, or simply may just not complete this task
    		
    		model.addNotes(view.getSelectedType()); // save the values temp
        	model.saveNotes(); //save values to csv sheet
    	}
    }
    
    // Inner Classes
        // All need callbacks on actionPerformed - finish later
    class browseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	// Have the user initially look in the reports directory
            JFileChooser chooser = new JFileChooser("Reports");
            int returnVal = chooser.showOpenDialog(new JFrame());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " + chooser.getSelectedFile());
                System.out.println("Loading file..........");
            }
            
            
           
        	model.processFile(chooser.getSelectedFile());
 
        	// Enable all of the hidden boxes
           view.setleftPanelvisible();
           view.setnotesvisible();
           view.setauthorvisible();
           view.seteditorPanePanelsvisible();
           view.setWindowLarge(); // Make the entire GUI window larger
      
        	//initialize first screen to the 0th clone match
        	String[] clones = model.skip(0);
            if (clones!=null) {
            	
            	
                view.setPanel1(clones[0].replace("null", "")); // An ugly hack
                view.setPanel2(clones[1].replace("null", ""));
                view.setLevenDistance(model.getCurrlevenDistance());
                view.setFileInfo(model.getCurrFileInfo(), model.getCurentLine());
                view.clearNotes();
                view.setValuesGUI(model.getCloneTypes());
            }
       
            // // Enable and disable back and forward buttons
              view.checkCycleButtonStatus(model.getCurentLine(), model.getTotalNumberOfCloneCandidates());
               
        }
    }
    
    /*
    //type text author
    class saveNoteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.addNotes(view.getSelectedType()+","+view.getNoteText().replaceAll(",", ";") +","+view.getAuthor().replaceAll(",", ";"));
        }
    }
    */
    

    // Cycle "right" button is clicked
    class rightListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 

        	// Try Saving information
        	// Try refactoring this
        
        	model.addNotes(view.getSelectedType()); // save the values temp
        	model.saveNotes(); //save values to csv sheet
        	
           	String[] clones = model.next(view.getEveryNthItem());
            if (clones!=null) {
                view.setPanel1(clones[0].replace("null", "")); // An ugly hack
                view.setPanel2(clones[1].replace("null", ""));
                view.setLevenDistance(model.getCurrlevenDistance());
                view.setFileInfo(model.getCurrFileInfo(), model.getCurentLine());
                view.clearNotes();
                view.setValuesGUI(model.getCloneTypes());
            }
        
         // Enable and disable back and forward buttons
            view.checkCycleButtonStatus(model.getCurentLine(), model.getTotalNumberOfCloneCandidates());  
        }
    }
    
    // Cycle "left" button is clicked
    class leftListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { 
        	    	
        	// Enable and disable back and forward buttons
            view.checkCycleButtonStatus(model.getCurentLine(), model.getTotalNumberOfCloneCandidates());
                  	
        	// Try Saving information
        	model.addNotes(view.getSelectedType()); // save the values locally
        	model.saveNotes(); //save values to csv sheet

            String[] clones = model.prev(view.getEveryNthItem());
            if (clones!=null) {
            	
                view.setPanel1(clones[0].replace("null", "")); // An ugly hack
                view.setPanel2(clones[1].replace("null", ""));
                view.setLevenDistance(model.getCurrlevenDistance());
                view.setFileInfo(model.getCurrFileInfo(), model.getCurentLine());
                view.clearNotes();
                view.setValuesGUI(model.getCloneTypes());
              
            }
            
         // Enable and disable back and forward buttons
            view.checkCycleButtonStatus(model.getCurentLine(), model.getTotalNumberOfCloneCandidates());
            
        }
    }
    
    class skipListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Skip");
            String skipNumber = view.getSkipField();
            try {
                int skipInt = Integer.parseInt(skipNumber)-1;	// To keep the skip number and clone count in sync. Kind of dirty hack
                String[] clones = model.skip(skipInt);
                if (clones!=null) {
                    view.setPanel1(clones[0].replace("null", "")); // An ugly hack
                    view.setPanel2(clones[1].replace("null", ""));
                    view.setLevenDistance(model.getCurrlevenDistance());
                    view.setFileInfo(model.getCurrFileInfo(), model.getCurentLine());
                    view.clearNotes();
                    view.setValuesGUI(model.getCloneTypes());
                    
                 // Enable and disable back and forward buttons
                    view.checkCycleButtonStatus(model.getCurentLine(), model.getTotalNumberOfCloneCandidates());
                 
                }
            } catch (NumberFormatException numForEx) {
             
            } 
        }
    }
    
    // End Inner Classes 
}