package attackontitan.frontend.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Asset {
    private static final int width = 32, height = 32;
    public static BufferedImage ground, wall, armouredTitan, colossusTitan, endturn, weapon1, weapon2, weapon3, weapon0, menu;
    public static BufferedImage muteButton;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite-inprogress.png"));
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu.png"));
        SpriteSheet endButton = new SpriteSheet(ImageLoader.loadImage("/textures/endbutton.png"));

        ground = sheet.crop(0, 0, width, height);
        wall = sheet.crop(width, 0, width, height);
        colossusTitan = sheet.crop(width * 2, 0, width, height);
        armouredTitan = sheet.crop(0, height, width, height);
        weapon1 = sheet.crop(width, height, width, height);
        weapon2 = sheet.crop(width * 2, height, width, height);
        weapon3 = sheet.crop(0, height * 2, width, height);
        weapon0 = sheet.crop(width, height * 2, width, height);
        endturn = endButton.getSheet();
        menu = menuSheet.getSheet();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
