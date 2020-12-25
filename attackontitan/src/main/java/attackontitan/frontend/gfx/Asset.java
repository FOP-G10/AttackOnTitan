package attackontitan.frontend.gfx;

import java.awt.image.BufferedImage;

public class Asset {
    private static final int width = 32, height = 32;
    public static BufferedImage ground, wall, armouredTitan, colossusTitan, weapon;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

        ground = sheet.crop(0, 0, width, height);
        wall = sheet.crop(width, 0, width, height);
        colossusTitan = sheet.crop(width * 2, 0, width, height);
        armouredTitan = sheet.crop(0, height, width, height);
        weapon = sheet.crop(width, height, width, height);
    }
}
