package Core;

//import java.io.File;
import java.io.PrintWriter;
	public class my_main {
		public static void main(String[] args) {
			Robo.TakeScreenShot(50,240,400,450,"D:\\testfiles\\Screenshot_1.png");
			String input_file="D:\\testfiles\\Screenshot_1.png";
			//String input_file="D:\\testfiles\\1478896350.png";
			String output_file="D:\\testfiles\\33";	
			String tesseract_install_path="D:\\Tesseract-OCR\\tesseract";
			String[] command =
		    {
		        "cmd",
		    };
		    Process p;
			try {
				p = Runtime.getRuntime().exec(command);
			        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
		                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
		                PrintWriter stdin = new PrintWriter(p.getOutputStream());
		                stdin.println("\""+tesseract_install_path+"\" \""+input_file+"\" \""+output_file+"\" -l eng");
		        	    stdin.close();
		        	    System.out.println("\""+tesseract_install_path+"\" \""+input_file+"\" \""+output_file+"\" -l eng ");
		                p.waitFor();
		                System.out.println();
		                System.out.println();
		                System.out.println();
		                System.out.println();
		                String A=Read_File.read_a_file(output_file+".txt");
		                //System.out.println(A);
		                try {
		                Parse.Parse_string(A);
		                }catch (NullPointerException e) {}
		                //File file = new File(input_file);
		               // file.delete();
		    	} catch (Exception e) {
		 		e.printStackTrace();
			}
		}	
	}	