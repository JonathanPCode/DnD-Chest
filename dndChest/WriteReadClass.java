import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.awt.Desktop;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.stage.FileChooser;

//import com.sun.javafx.logging.Logger;

public class WriteReadClass {
	private BufferedWriter Writebw;
	private BufferedReader Readbw;
	private File readFile;
	
	
	public WriteReadClass (File file) {
		//Create read file
			try {
				FileReader fr = new FileReader(file);
				Readbw = new BufferedReader(fr);
				
			} catch (IOException e){
			      System.out.println("An error occurred.");
			      e.printStackTrace();	
			}
		}		
			
	
	
	public WriteReadClass(){
	};
		
	

	
	public String readFile(BufferedReader br) {
		//System.out.println("IN READ FILE FUNC");
		String line = new String();
		
		try {

			line = Readbw.readLine();
			//System.out.println("in try " + line);
			if( line  != null )
			{
				return (line);
				
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}	
		return "return test";
	}

	
	public void writeFile(BufferedWriter bw, String str) {
		try {
			
			System.out.println("in writeFile");
			bw.write(str);
			bw.newLine();
			
		} catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		
	}
	
}
