package CloneInspector;

import java.io.File;
import model.Model;

/**
 *
 * @author Luke
 */
public class Main {

    /**
     * @param args the command line arguments
     */
	
    public static void main(String[] args) {
    Util u = new Util();	
    	
        // You can clearly see the privacy here
        File file = new File(".");
        //System.out.println(file.getAbsolutePath());
        Model model = new Model();
        View view = new View(model);
     //   System.out.println("AppName: " + u.getTargetAppname() );
        System.out.println();
        Controller controller = new Controller(model, view);
        System.out.println("Please ignore that error. Make sure your source and ctag files are in the right folders (clonesource and clonectag)");
        view.appear();
    }
    
    
    
    
}
