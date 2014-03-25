package graphicdiff;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import jsyntaxpane.DefaultSyntaxKit;
import model.Model;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * View knows only about Model
 * @author Luke
 */
public class View extends JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browse;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTextArea author;
    private javax.swing.JTextArea notes;

    private javax.swing.JPanel editorPanePanels;
//    private javax.swing.JPanel seperator1;
//    private javax.swing.JPanel seperator2;
    private javax.swing.JPanel seperator3;
    private javax.swing.JPanel jumpFormPanel;
    private javax.swing.JPanel everyNthPanel; // Used for statsig values
    private javax.swing.JPanel seperator4;
    private javax.swing.JTextField levenDistance;
    private javax.swing.JTextField everyNthItem;
    
    private javax.swing.JScrollPane scrollPane1;
    //private RTextScrollPane sp1;
    private javax.swing.JScrollPane scrollPane2;
    //private RTextScrollPane sp2;
    
    private javax.swing.JTextField jumpField;
    private javax.swing.JButton left;
    private javax.swing.JTextArea fileInfo;  
    private javax.swing.JPanel cloneTypePanel;
    private javax.swing.JPanel minorControlsPanel;
    private javax.swing.JRadioButton noClone;
    private javax.swing.JRadioButton notSure;
    private javax.swing.JRadioButton type1Choice;
    private javax.swing.JRadioButton type2Choice;
    private javax.swing.JRadioButton type3Choice;
    private javax.swing.JRadioButton type4Choice;
    private javax.swing.JRadioButton notClassified;
    private javax.swing.JTextField everyNthClone;
    
    private ButtonGroup group;
    
//    private javax.swing.JButton saveNotes;
    private javax.swing.JEditorPane clonePane1;
    private javax.swing.JEditorPane clonePane2;
    
    private javax.swing.JButton right;
    private javax.swing.JButton skip;
    // End of variables declaration//GEN-END:variables

    Model model;
    
    public View() {
        initComponents();
    }
    
    public View(Model model) {
        initComponents();
        this.model = model;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

         try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        
        //leftPanel.setSize(1000,100);
        
        
        author = new javax.swing.JTextArea();
        author.setText("");
        //author.setText("(Author Name Here)");
        notes = new javax.swing.JTextArea();
        
        cloneTypePanel = new javax.swing.JPanel();
        minorControlsPanel = new javax.swing.JPanel();

        
        noClone = new javax.swing.JRadioButton();
        notSure = new javax.swing.JRadioButton();
        type1Choice = new javax.swing.JRadioButton();
        type2Choice = new javax.swing.JRadioButton();
        type3Choice = new javax.swing.JRadioButton();
        type4Choice = new javax.swing.JRadioButton();
        notClassified = new javax.swing.JRadioButton();
        
        group = new ButtonGroup();
        
        group.add(noClone);
        group.add(notSure);
        group.add(type1Choice);
        group.add(type2Choice);
        group.add(type3Choice);
        group.add(type4Choice);
        group.add(notClassified);
      
        
 //       noClone.setSelected(true);
        notSure.setText("Unsure");
        type1Choice.setText("Type 1");
        type2Choice.setText("Type 2");
        type3Choice.setText("Type 3");
        noClone.setText("No Clone");
        type4Choice.setText("Type 4");
        notClassified.setText("Not Classified");
        notClassified.setSelected(true);
        
        notes.setText("Put your notes here.");
       
        //saveNotes = new javax.swing.JButton("Commit note");
        
        browse = new javax.swing.JButton();
 //       seperator1 = new javax.swing.JPanel();
        right = new javax.swing.JButton();
  //      seperator2 = new javax.swing.JPanel();
        left = new javax.swing.JButton();
        seperator3 = new javax.swing.JPanel();
        jumpFormPanel = new javax.swing.JPanel();
        everyNthPanel = new javax.swing.JPanel();
        jumpField = new javax.swing.JTextField();
        everyNthClone = new javax.swing.JTextField(5);
        skip = new javax.swing.JButton();
        seperator4 = new javax.swing.JPanel();
        levenDistance = new javax.swing.JTextField();
        levenDistance.setEditable(false);
        levenDistance.setText("Levenshtein Distance: ");
        
        everyNthItem = new javax.swing.JTextField();
        everyNthItem.setEditable(false);
        everyNthItem.setText("Cycle to every Nth Item");
        everyNthClone.setText("1"); // Make it have a default value of 1 - This could change
           
        editorPanePanels = new javax.swing.JPanel();
        jsyntaxpane.DefaultSyntaxKit.initKit();
        fileInfo = new JTextArea();
        clonePane1 = new javax.swing.JEditorPane();   
        scrollPane1 = new javax.swing.JScrollPane(clonePane1);
        clonePane1.setContentType("text/c");
        clonePane2 = new javax.swing.JEditorPane();
        scrollPane2 = new javax.swing.JScrollPane(clonePane2);
        clonePane2.setContentType("text/c");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Set layout for west pane (Buttons and stuff)
        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("CloneDiff - Daniel Krutz"));
        leftPanel.setLayout(new java.awt.GridLayout(10, 1));

        browse.setText("Browse (CSV)");
        
        minorControlsPanel.add(browse);
        
//        leftPanel.add(seperator1);
        
        right.setText("->");
        leftPanel.add(right);

    //    leftPanel.add(seperator2);
        
        leftPanel.add(cloneTypePanel);

        left.setText("<-");
        leftPanel.add(left);

        leftPanel.add(seperator3);
        
        jumpFormPanel.setLayout(new java.awt.GridLayout(1, 2));
        jumpFormPanel.add(jumpField);
        
        everyNthPanel.add(everyNthItem);
        everyNthPanel.add(everyNthClone);
        
        
        skip.setText("Jump to it!");
        jumpFormPanel.add(skip);
        leftPanel.add(jumpFormPanel);
        leftPanel.add(everyNthPanel);
        
        leftPanel.add(seperator4);
        leftPanel.add(levenDistance);
        
        getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);
        editorPanePanels.setLayout(new java.awt.GridLayout(1, 2));

        editorPanePanels.add(scrollPane1);
        
        editorPanePanels.add(scrollPane2);
        
        getContentPane().add(editorPanePanels, java.awt.BorderLayout.CENTER);
        getContentPane().add(fileInfo, java.awt.BorderLayout.SOUTH);
        
       rightPanel.setLayout(new BorderLayout());
       rightPanel.add(notes, BorderLayout.CENTER);
        
        minorControlsPanel.setLayout(new GridLayout(3,2));  

//      cloneTypePanel.add(saveNotes);
        minorControlsPanel.add(new javax.swing.JLabel());
       
        cloneTypePanel.setLayout(new GridLayout(4,2));
//        cloneTypePanel.add(saveNotes);
        cloneTypePanel.add(new javax.swing.JLabel());
        
        cloneTypePanel.add(type1Choice);
        cloneTypePanel.add(notClassified);
        cloneTypePanel.add(type2Choice);
        cloneTypePanel.add(notSure);
        cloneTypePanel.add(type3Choice);    
        cloneTypePanel.add(noClone);
        cloneTypePanel.add(type4Choice);
      
        rightPanel.add(minorControlsPanel, BorderLayout.SOUTH);
        //rightPanel.add(saveNotes, BorderLayout.SOUTH);
        rightPanel.add(author, BorderLayout.NORTH);
        
        getContentPane().add(rightPanel, java.awt.BorderLayout.EAST);
        
    //    rightPanel.setSize(400, 300);
   //     disableBeforeLoad(); // Disable buttons which should not be active before input file is loaded

        leftPanel.setVisible(false);
    //    clonePane1.setVisible(false);
    //    clonePane2.setVisible(false);
        notes.setVisible(false);
        author.setVisible(false);
      //  rightPanel.setVisible(fals

        
        editorPanePanels.setVisible(false);
        
 //       getContentPane().setVisible(false);
        
        pack();
    }
    
    public void appear() {
       this.setVisible(true);
    }
    
    public String getNoteText() {
        return notes.getText();
    }

    public void setPanel1(String s) {
        clonePane1.setText(s);
    }
    
    public void setPanel2(String s) {
        clonePane2.setText(s);
    }
    
    public String getSkipField() {
        return jumpField.getText();
    }
    
    // Return every nTh amount
    public int getEveryNthItem(){
    	return Integer.parseInt(everyNthClone.getText());
    }
    
    void addBrowseListener(ActionListener list) {
        browse.addActionListener(list);
    }
    
    void addRightListener(ActionListener list) {
        right.addActionListener(list);
    }
    
    void addLeftListener(ActionListener list) {
        left.addActionListener(list);
    }
    
    void addSkipListener(ActionListener list) {
        skip.addActionListener(list);
    }
  

    void addCloneChangeListener(ActionListener list) {
        //skip.addActionListener(list);
    	//clonePane1.addActionListener(list);

    	 noClone.addActionListener(list);
         notSure.addActionListener(list);
         type1Choice.addActionListener(list); 
         type2Choice.addActionListener(list);
         type3Choice.addActionListener(list);
         type4Choice.addActionListener(list);
         notClassified.addActionListener(list); 

    }
    
    
    // Used when the save notes button was being utilized.
 //   void addSaveNoteListner(ActionListener list) {
 //       saveNotes.addActionListener(list);
 //   }
  
    
    void setLevenDistance(int l) {
        levenDistance.setText("Levenshtein Distance: "+l);
    }
    
    void setFileInfo(String s, int currentLine) {
        fileInfo.setText("#"+(model.getCurentLine()+1)+"/" + model.getTotalNumberOfCloneCandidates() + ": "+s);
    }
    
    void clearNotes() {
        notes.setText("");
    }
    
    // Load values on the GUI interface
    void setValuesGUI(String s) {
    	setCloneType(s);
    
    }

    // Set the radio button for the type of clone
    void setCloneType(String s){
    
    	s = s.toLowerCase(); // Remove case sensitive errors when doing checks
    	if(s.equals("type 1")){
    		type1Choice.setSelected(true);  
        } else if(s.equals("type 2")){
        	type2Choice.setSelected(true);
        } else if(s.equals("type 3")){
        	type3Choice.setSelected(true);
        } else if(s.equals("type 4")){;
        	type4Choice.setSelected(true);
        } else if(s.equals("no clone")){ 
        	noClone.setSelected(true);
        } else if(s.equals("unsure")){
        	notSure.setSelected(true);
        } else {
        	noClone.setSelected(true); // changed from not sure
        }	
    }
    
    String getAuthor() {
        return author.getText();
    }
    
    String getSelectedType() {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    
    
    public void setleftPanelvisible(){
    	leftPanel.setVisible(true);
    }
   
    public void setnotesvisible(){
    	notes.setVisible(true);
    }
    
    public void setauthorvisible(){
    	author.setVisible(true);
    }
    
    public void seteditorPanePanelsvisible(){
    	editorPanePanels.setVisible(true);
    }
    
    public void disableBack(){
    	left.setEnabled(false);
    }
    
    public void disableFront(){
    	right.setEnabled(false);
    }
    
    public void enableBack(){
    	left.setEnabled(true);
    }
    
    public void enableFront(){
    	right.setEnabled(true);
    }
    
    
// Once a CSV file is loaded, make the GUI window larger
    public void setWindowLarge(){
    	// Try inserting blank or empty text into clone fields
    	// Very ugly hack, but it seems to work
    	setPanel1("                                          ");
        pack();
    }
    
    
    // Enable and Disable back and forward buttons depending on where you are in the list
    public void checkCycleButtonStatus(int currentRecord, int totalRecordCount){
    	
    	if(currentRecord==0){
    		disableBack();
    	}
    		
    	if((currentRecord+1) == totalRecordCount){
    		disableFront();
    	}
    	
    	if(currentRecord!=0){
    		enableBack();
    	}
    	
    	if((currentRecord+1) < totalRecordCount){
    		enableFront();
    	}
    	
    }
    	
    
    
}
