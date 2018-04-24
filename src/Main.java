import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.WindowConstants;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class Main {	
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	private static final long FPS = 128l;
	private static final long secondInNano = 1000000000l;

	public static void main(String[] args) throws IOException {
		setUpLogging();

		JFrame frame = buildFrame();

		Aquarium aquarium = new Aquarium(0, SCREEN_WIDTH, 100, SCREEN_HEIGHT);
		Screen screen = new Screen(aquarium, SCREEN_WIDTH, SCREEN_HEIGHT);

		frame.setContentPane(screen);

		double secondPerFrame = 1d/Long.valueOf(FPS).doubleValue();
		long frameInterval = secondInNano/FPS;
		long lastFrameStart = System.nanoTime();
		long prevtime = System.nanoTime();
		long now = System.nanoTime();
		while (true) {
			now = System.nanoTime();
			prevtime = now;
			if ((now - lastFrameStart) >= frameInterval) {
				frame.invalidate();
				frame.validate();
				frame.repaint();
				aquarium.tick(secondPerFrame);
				lastFrameStart = now;
			}
		}
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

	private static JFrame buildFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setVisible(true);
		return frame;
	}
}