import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Screen extends JPanel {

	private final String CURRENT_IMAGE = "resources/small-guppy-right.png";
	private final String BACKGROUND = "resources/bg-800x600-with-shop.png";
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;

	private BufferedImage defaultImage;
	private Map<String, BufferedImage> images;

	private int x;
	private int y;
	private long fps;

	public Screen(String defaultObjectImagePath) throws IOException {
		this.defaultImage = ImageIO.read(new File(defaultObjectImagePath));
		this.images = new TreeMap();

		this.x = 0;
		this.y = 0;
		this.fps = 1000000000l/128l;
	}

	private BufferedImage readImage(String path) {
		BufferedImage newImage = this.images.get(path);
		if (newImage == null) {
			try {
				newImage = ImageIO.read(new File(path));	
			} catch (IOException e) {
				newImage = defaultImage;
			}
			this.images.put(path, newImage);
		}
		return newImage;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(readImage(BACKGROUND), 0, 0, null);
		g.drawImage(readImage(CURRENT_IMAGE), x, y, null);
		this.x = (x + 1) % SCREEN_WIDTH;
		this.y = (y + 1) % SCREEN_HEIGHT;
	}

	private static class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
           	System.out.println("PRESSED");
        }
	}

	private static void setUpLogging() {
      	FileOutputStream f;
  		try {
		  	f = new FileOutputStream("log/screen-log.txt");
			System.setOut(new PrintStream(f));
  		} catch (FileNotFoundException e) {
  			f = null;
  		}
	}

	public static void main(String[] args) throws IOException {
		setUpLogging();

		JFrame frame = buildFrame();
		Screen screen;
		try {
			screen = new Screen("resources/unknown.png");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		screen.addMouseListener(new Mouse());

		frame.setContentPane(screen);

		long lastFrameStart = System.nanoTime();
		long prevtime = System.nanoTime();
		long now = System.nanoTime();
		long systemDelay = now - prevtime;
		while (true) {
			now = System.nanoTime();
			systemDelay = prevtime - now;
			prevtime = now;
			
			if ((now - lastFrameStart) >= screen.fps) {
				frame.invalidate();
				frame.validate();
				frame.repaint();
				lastFrameStart = now;
			}
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