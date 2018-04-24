import javax.imageio.ImageIO;
import javax.swing.JPanel;
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

public class Screen extends JPanel {
	private final String BACKGROUND = "resources/bg-800x600-with-shop.png";
	private final String DEFAULT_IMAGE = "resources/unknown.png";

	private int width;
	private int height;
	private BufferedImage defaultImage;
	private Map<String, BufferedImage> images;
	private Aquarium aquarium;

	public Screen(Aquarium aquarium, int width, int height) throws IOException {
		this.defaultImage = ImageIO.read(new File(DEFAULT_IMAGE));
		this.images = new TreeMap();
		this.width = width;
		this.height = height; 
		this.aquarium = aquarium;
		this.addMouseListener(new Mouse());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(readImage(BACKGROUND), 0, 0, null);
		drawAllEntities(g, aquarium.getCoins());
		drawAllEntities(g, aquarium.getFoods());
		drawAllEntities(g, aquarium.getGuppies());
		drawAllEntities(g, aquarium.getPiranhas());
		drawEntity(g, aquarium.getSnail());
	}

	private <T extends Entity> void drawAllEntities(Graphics g, LinkedList<T> list) {
		Element<T> element = list.getFirst();
		while (element != null) {
			T entity = element.getInfo();
			drawEntity(g, entity);
			element = element.getNext();
		}
	}

	private void drawEntity(Graphics g, Entity entity) {
		if (entity != null) {
			BufferedImage image = readImage(entity.getImage());
			entity.setWidth(image.getWidth());
			entity.setHeight(image.getHeight());
			g.drawImage(image, 
				(Double.valueOf(entity.getLeft())).intValue(), 
				(Double.valueOf(entity.getTop())).intValue(), 
				null);
		}
	}

	private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
        	aquarium.add(new Food(e.getX(), e.getY(), 0, 0));
        }
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
}