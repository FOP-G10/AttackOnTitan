package attackontitan.frontend.ui;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
