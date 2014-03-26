package CloneInspector;

import model.CSVLine;
import model.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*
- Test reading 

*/


public class test {

	Util u = new Util();
	private List<Number> cloneSectionList  = new ArrayList<Number>();
	
	public static void main(String[] args) throws IOException {
		test t = new test();
		t.Run();
	}
	
	
	public void Run() throws IOException{

		
		final int numberofRows = 9;
		
		cloneSectionList.add(3);
		cloneSectionList.add(1); 				
		cloneSectionList.add(4);
		cloneSectionList.add(9);
		cloneSectionList.add(8);
		
		
//		System.out.println(cloneSectionList.size());
	
	//	printResults(cloneSectionList,9);
		}	
	
	
	
}
