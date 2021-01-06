package attackontitan.frontend.gfx;

import java.awt.image.BufferedImage;

public class Asset {
    private static final int width = 32, height = 32;
    public static BufferedImage ground, wall, armouredTitan, colossusTitan, endturn, weapon1, weapon2, weapon3, weapon0, menu;

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
}
