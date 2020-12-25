package attackontitan.frontend.display;

import javax.swing.*;
import java.awt.*;

public class Display {
    JFrame frame;
    Canvas canvas;
    int width, height;
    String title;

    public Display(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.createDisplay();
    }

    public void createDisplay() {
        this.frame = new JFrame();
        this.frame.setSize(this.width, this.height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(this.width, this.height));
        this.canvas.setMaximumSize(new Dimension(this.width, this.height));
        this.canvas.setMinimumSize(new Dimension(this.width, this.height));
        this.canvas.setFocusable(false);

        this.frame.add(this.canvas);
        this.frame.pack();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }
}
