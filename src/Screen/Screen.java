import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Font;
import java.awt.FontFormatException;
import java.util.ArrayList;

public class Screen extends JPanel {
	private final String IMAGE_PATH = "resources/img/";
	private final String FONT_PATH = "resources/font/";
	private final String BACKGROUND = "bg-800x600-with-shop.png";
	private final String DEFAULT_IMAGE = IMAGE_PATH + "unknown.png";
	private final String DEFAULT_FONT = FONT_PATH + "Sansation-Regular.ttf";
	private final String[] AREA_NAMES = {
		"shop-guppy",
		"shop-piranha",
		"shop-telur-1",
		"shop-telur-2",
		"shop-telur-3"
	};

	private int left;
	private int top;
	private int width;
	private int height;
	private BufferedImage defaultImage;
	private Font defaultFont;
	private Map<String, BufferedImage> images = new TreeMap();
	private Map<String, Font> fonts = new TreeMap();
	private ArrayList<Area> areas = new ArrayList<Area>();
	private Aquarium aquarium;
	private double secondPerFrame;
	private DummyEntity notEnoughMoneyMessage;

	public Screen(int width, int height, int left, int top, double secondPerFrame) throws IOException, FontFormatException {
		this.defaultImage = ImageIO.read(new File(DEFAULT_IMAGE));
		this.defaultFont = Font.createFont(Font.TRUETYPE_FONT, new File(DEFAULT_FONT));
		this.width = width;
		this.height = height; 
		this.left = left;
		this.top = top;
		this.secondPerFrame = secondPerFrame;
		Integer integer = Integer.valueOf(this.height);
		Integer res = Double.valueOf(90d*(integer.doubleValue()/600d)).intValue();
		this.aquarium = new Aquarium(0, width, res, height);

		setupAquarium();
		setupAreas();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(readFont("Sansation-Bold.ttf").deriveFont(Font.PLAIN, 25.0f));
		g.setColor(Color.CYAN);
		g.drawImage(readImage(BACKGROUND), 0, 0, this.width, this.height, null);
		g.drawString("Money: " + this.aquarium.getMoney(), this.width-250, 30);
		drawAreas(g);
		drawAllEntities(g, aquarium.getCoins());
		drawAllEntities(g, aquarium.getFoods());
		drawAllEntities(g, aquarium.getGuppies());
		drawAllEntities(g, aquarium.getPiranhas());
		drawEntity(g, aquarium.getSnail());
		drawDummyEntities(g);
		aquarium.tick(secondPerFrame);
	}

	public void onMouseEvent(MouseEvent e) {
		int x = e.getX()-this.left;
		int y = e.getY()-this.top;
		for (Area area : areas) {
			if (area.isInside(x, y)) {
				boolean buySuccess = true;
				if (area.getName().equals("aquarium")) {
					if (buySuccess = aquarium.buy(ShopItem.FOOD.price)) {
						aquarium.add(new Food(x, y, 0, 0));
					}
				} else if (area.getName().equals(AREA_NAMES[0])) {
					if (buySuccess = aquarium.buy(ShopItem.GUPPY.price)) {
						aquarium.add(new Guppy(randomX(), this.aquarium.getTop(), 0, 0));
					}
				} else if (area.getName().equals(AREA_NAMES[1])) {
					if (buySuccess = aquarium.buy(ShopItem.PIRANHA.price)) {
						aquarium.add(new Piranha(randomX(), this.aquarium.getTop(), 0, 0));
					}
				} else if (area.getName().equals(AREA_NAMES[2])) {
					buySuccess = aquarium.buy(ShopItem.EGG_1.price);
				} else if (area.getName().equals(AREA_NAMES[3])) {
					buySuccess = aquarium.buy(ShopItem.EGG_2.price);
				} else if (area.getName().equals(AREA_NAMES[4])) {
					buySuccess = aquarium.buy(ShopItem.EGG_3.price);
				}
				if (!buySuccess) {
					this.notEnoughMoneyMessage = new DummyEntity(this.width-200, 60, 1.5);
					this.notEnoughMoneyMessage.setImage("not-enough-money.png");
				}
			}
		}
	}

	public void onKeyEvent(KeyEvent e) {
		if (e.getKeyChar() == 'r') {
			Coin coin = new Coin(0, height+1, 0, 0, 10);
			coin.setWidth(readImage(coin.getImage()).getWidth());
			coin.setHeight(readImage(coin.getImage()).getHeight());
			this.aquarium.add(coin);
			coin.move(randomX(), this.aquarium.getTop() + coin.getRadY());
		}
	}

	private void setupAquarium() {
		Snail snail = new Snail(0, height+1, 0, 0);
		snail.setWidth(readImage(snail.getImage()).getWidth());
		snail.setHeight(readImage(snail.getImage()).getHeight());
		this.aquarium.add(snail);
		snail.move(randomX(), this.aquarium.getBottom() - snail.getRadY());
	}

	private void setupAreas() {
		Area aquariumArea = new Area("aquarium", 
			this.aquarium.getLeft(),
			this.aquarium.getRight(),
			this.aquarium.getTop(),
			this.aquarium.getBottom(),
			null);
		this.areas.add(aquariumArea);

		String[] images = {
			"shop-icon-guppy.jpg",
			"shop-icon-piranha.jpg",
			"shop-icon-telur-1.jpg",
			"shop-icon-telur-2.jpg",
			"shop-icon-telur-3.jpg"
		};

		int margin = 5;
		int distanceFromLeft = 0;		
		for (int i = 0; i < AREA_NAMES.length; ++i) {
			Area area = new Area(AREA_NAMES[i], distanceFromLeft, distanceFromLeft+getImageWidth(images[i]),
				0, this.aquarium.getTop(), images[i]);
			distanceFromLeft += getImageWidth(images[i]) + margin;
			this.areas.add(area);
		}
	}

	private void drawDummyEntities(Graphics g) {
		if (this.notEnoughMoneyMessage != null) {
			drawEntity(g, this.notEnoughMoneyMessage);
			this.notEnoughMoneyMessage.tick(this.secondPerFrame);
			if (this.notEnoughMoneyMessage.isDie()) {
				this.notEnoughMoneyMessage = null;
			}
		}
	}

	private void drawAreas(Graphics g) {
		for (Area area : areas) {
			if (area.getImage() != null) {
				g.drawImage(readImage(area.getImage()), area.getLeft(), area.getTop(), 
					area.getWidth(), 
					area.getHeight(), null);
			}
		}
	}

	private double randomX() {
		Random random = new Random();
		return random.nextDouble()*(this.aquarium.getRight() - this.aquarium.getLeft()) + this.aquarium.getLeft();
	}

	private double randomY() {
		Random random = new Random();
		return random.nextDouble()*(this.aquarium.getBottom() - this.aquarium.getTop()) + this.aquarium.getTop();
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

	private Font readFont(String filename) {
		String path = FONT_PATH + filename;
		Font font =	this.fonts.get(path);
		if (font == null) {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
				this.fonts.put(path, font);
			} catch (Exception e) {
				font = defaultFont;
			}
		}
		return font;
	}

	private BufferedImage readImage(String filename) {
		String path = IMAGE_PATH + filename;
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

	private int getImageWidth(String filename) {
		BufferedImage image = readImage(filename);
		return image.getWidth();
	}

	private int getImageHeight(String filename) {
		BufferedImage image = readImage(filename);
		return image.getHeight();
	}
}