import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;

public class Checkstyler {
	private static final String COMMAND = "java -jar lib/checkstyle-8.9-all.jar";
	private static final String CONFIG = "-c checkstyle/config/google_checks.xml";
	private static final String[] CLASSES = {
		"src/Screen.java"
	};

	private static final PrintStream SYSTEM_OUT_DEFAULT = System.out;

	private static void resetSystemOut() {
		System.setOut(SYSTEM_OUT_DEFAULT);
	}

	private static void setPrintOut(String filepath) throws FileNotFoundException {
		String outname = (filepath.replace("/", "-")).replace(".java", "");
		String outpath = "checkstyle/results/" + outname + ".txt";
      	FileOutputStream f = new FileOutputStream(outpath);
      	System.setOut(new PrintStream(f));
	}

	private static void printResult(String filepath) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(COMMAND + " " + CONFIG + " " + filepath);
		String line;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		while ((line = stdInput.readLine()) != null) {
			System.out.println(line);
		}	
	}

	private static boolean fileExist(String filepath) {
		File file = new File(filepath);
		return file.exists();
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0 || args[0] == null) {
			int iClass = 0;  
	  		while (iClass < CLASSES.length) {
		  		String filepath = CLASSES[iClass];
		  		if (fileExist(filepath)) {
		  			try {
						setPrintOut(filepath);
					} catch (FileNotFoundException e) {
						System.out.println("Error to write output file for " + filepath + "!");
						break;
					}
					printResult(filepath);
					resetSystemOut();
					System.out.println(filepath + " checkstyle result has been generated!");
				    iClass++;
		  		} else {
					System.out.println(filepath + " is not exist!");
				}
	  		}
		} else {
			String filepath = args[0];
			if (fileExist(filepath)) {
				try {
					setPrintOut(filepath);
				} catch (FileNotFoundException e) {
					System.out.println("Error to write output file for " + filepath + "!");
				}
				printResult(filepath);
				resetSystemOut();
				System.out.println(filepath + " checkstyle result has been generated!");
			} else {
				System.out.println(filepath + " does not exist!");
			}
		}
	}
}