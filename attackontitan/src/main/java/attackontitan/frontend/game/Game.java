package attackontitan.frontend.game;

import attackontitan.frontend.display.Display;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.gfx.ImageLoader;
import attackontitan.frontend.world.World;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    private String title;
    private int width, height;
    private Display display;
    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private World world;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);
        this.world = new World("");
        // initialize the assets
        // what this will do is
        // load the spritesheet
        // crop the spritesheet to subimage
        // assign them to static variables in the asset class
        Asset.init();
    }

    int y = 0;

    public void tick() {
        y += 1;
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        // clear canvas
        g.clearRect(0, 0, this.width, this.height);

        // Start drawing here
//        g.drawImage(Asset.ground, 10, 10, null);
//        g.drawImage(Asset.wall, 30, 30, null);
        world.render(g);

        // End drawing
        bs.show();
        g.dispose();
    }

    @Override
    public void run() { // called when the thread start
        init(); // initialize the display

        int fps = 60;
        double timePerTick = 1000000000.0 / fps; // get the time available before the next tick
        int ticks = 0; // count the ticks
        long timer = 0;
        double delta = 0; // to keep track of the time run
        long now; // keep the current time in the loop
        long lastTime = System.nanoTime(); // keep the last time in the loop

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; // divide the tick to check how much per timePerTick is use
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                ticks += 1; // increment to count the tick
                render();
                delta -= 1; // one delta is equal to the total time per tick
            }

            if (timer >= 1000000000) {
                System.out.println(ticks + " fps");
                ticks = 0; // reset the value
                timer = 0; // reset the value
            }
        }
        stop(); // close the thread
    }

    public synchronized void start() {
        if(running) {
            return; // to prevent the thread from starting if it is already started
        }
        this.running = true; // show that the thread is running
        this.thread = new Thread(this);
        this.thread.start(); // this will call the run method
    }

    public synchronized void stop() {
        if(!running) {
            return; // does not have to stop if it is not running
        }
        this.running = false; // notify the program that the thread is not running

        try{
            this.thread.join(); // end the thread
        }catch(InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
