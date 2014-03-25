CloneDiff is a tool for analyzing segments of source code and manually determining if two compared methods are code clones. 



Building:
- You may need to add "jxl.jar" and "jsyntaxpane-0.9.5-b29.jar" to the build path 



Running:
- Run the application by invoking the graphicdiff.Main



Usage:
- Select "Browse (CSV)"
- Choose the csv file to analyze. Examples are provided in the application.
	- Loading the data may take from 10 seconds to 1 minute in time.
- A method will be loaded on both the left and right sides of the screen. 
- Choose the type of clone, or no clone.
- Cycle to the next clone possibility by clicking the left or right button.
	- Data is automatically saved whenever you cycle.
- Final results are recorded in the chosen .csv file 


Notes:
