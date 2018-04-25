import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.awt.FontFormatException;

public class Main {	
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private static final long FPS = 128l;

	public static void main(String[] args) throws IOException, FontFormatException {
		// setUpLogging();
		Frame frame = new Frame(SCREEN_WIDTH, SCREEN_HEIGHT, FPS);
		frame.start();
	}

	private static void setUpLogging() {
      	FileOutputStream f;
  		try {
		  	f = new FileOutputStream("log/screen-log.txt");
		  	PrintStream ps = new PrintStream(f);
			System.setOut(ps);
			System.setErr(ps);
  		} catch (FileNotFoundException e) {
  			f = null;
  		}
	}
}