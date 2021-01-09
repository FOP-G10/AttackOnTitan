package attackontitan.frontend.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Asset {
    private static final int width = 32, height = 32;
    public static BufferedImage ground, wall, armouredTitan, colossusTitan, endturn, weapon1, weapon2, weapon3, weapon0, menu;
    public static BufferedImage wallDamage1, wallDamage2, menuIcon, volumeIcon, muteIcon;
    public static BufferedImage fireball;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite-inprogress(2).png"));
        SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu.png"));
        SpriteSheet endButton = new SpriteSheet(ImageLoader.loadImage("/textures/endbutton.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/sprites(2).png"));

        ground = sheet.crop(0, 0, width, height);
        wall = sheet.crop(width, 0, width, height);
        wallDamage1 = sheet2.crop(0, 0, width, height);
        wallDamage2 = sheet2.crop(width, 0, width, height);
        colossusTitan = sheet.crop(width * 2, 0, width, height);
        armouredTitan = sheet.crop(0, height, width, height);
        weapon1 = sheet.crop(width, height, width, height);
        weapon2 = sheet.crop(width * 2, height, width, height);
        weapon3 = sheet.crop(0, height * 2, width, height);
        weapon0 = sheet.crop(width, height * 2, width, height);
        endturn = endButton.getSheet();
        menu = menuSheet.getSheet();
        volumeIcon = sheet2.crop(2*width, 0, width, height);
        muteIcon = sheet2.crop(0, height, width, height);
        menuIcon = sheet2.crop(width, height, width, height);
        fireball = sheet2.crop(2 * width, height, width, height);
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
