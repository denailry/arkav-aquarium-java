import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.FontFormatException;

public class Frame extends JFrame {
	private final long secondInNano = 1000000000l;
	private Screen screen;
	private long framePerSecond;
	private double secondPerFrame;

	public Frame(int width, int height, long framePerSecond) throws IOException, FontFormatException {
		this.framePerSecond = framePerSecond;
		this.secondPerFrame = 1d/Long.valueOf(this.framePerSecond).doubleValue();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		this.addMouseListener(new Mouse());
		this.screen = new Screen(getRootPane().getWidth(), getRootPane().getHeight(), 
			width-getRootPane().getWidth()-8, height-getRootPane().getHeight()-7, secondPerFrame);
		this.setContentPane(screen);
	}

	public void start() {
		long frameInterval = secondInNano/this.framePerSecond;
		long lastFrameStart = System.nanoTime();
		long prevtime = System.nanoTime();
		long now = System.nanoTime();
		while (true) {
			now = System.nanoTime();
			prevtime = now;
			if ((now - lastFrameStart) >= frameInterval) {
				this.invalidate();
				this.validate();
				this.repaint();
				lastFrameStart = now;
			}
		}
	}

	private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
        	screen.onMouseEvent(e);
        }
	}

	private class Keyboard extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			screen.onKeyEvent(e);
		}
	}
}