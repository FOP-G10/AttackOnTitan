package attackontitan.frontend.entities;

import attackontitan.frontend.input.MouseManager;

import java.awt.*;

public abstract class Entity {

    protected int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g, MouseManager mouseManager);
}
