// Helper class


package graphicdiff;

public class Util {

	// Determine if a environment is Windows
	// This is useful when referencing files
	// Check to see if I can get rid of this.
	public boolean isWindows(){
		boolean retVal = false;
		if (System.getProperty("os.name").startsWith("Windows")) {
			retVal=true;
	    }
		return retVal;
	}
	
	
	// Alterate the switching value based on windows vs. Unix		
	// Check to see if I can get rid of this.
	public String getSeperatorChar(){
		String sepVal="";			
		if (isWindows()){
			sepVal = "\\";
		}else{
			//sepVal = "//";
			sepVal = "/";
		}
		return sepVal;
	}
	
/*
	// Seperate the "Note" information from the clone type and the notes.
	// Check to see if I can get rid of this.
	public String getParsedNotesOutput(int Section, String getNotesInfo){
		// 0 for the clone type
		// 1 for the notes added by the reader
		
		String[] output = getNotesInfo.split(",");
		return output[Section];
		
	}
		*/
	
	/*
	// Pull in the name of the report for the name of the Application being analyzed
	public String getTargetAppname(){
	//	return "kraw";
		//return "Python-2.5.1";
		//return "postgresql-8.4.9";
		//return "python-2.5.1"
//		return "httpd-2.2.14";
		return "xyz";
		
	}
*/
	
	
	
}
