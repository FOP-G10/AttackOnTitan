package attackontitan.frontend.game;

import attackontitan.frontend.display.Display;
import attackontitan.frontend.gfx.Asset;
import attackontitan.frontend.gfx.ImageLoader;
import attackontitan.frontend.input.KeyboardManager;
import attackontitan.frontend.input.MouseManager;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.state.MenuState;
import attackontitan.frontend.state.State;
import attackontitan.frontend.world.World;

import javax.swing.*;
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

    private MouseManager mouseManager;
    private KeyboardManager keyboardManager;

    private World world;

    private State gameState;
    public State menuState;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
//        this.gameProcess = gameProcess;
        this.mouseManager = new MouseManager();
        this.keyboardManager = new KeyboardManager();
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getFrame().addKeyListener(keyboardManager);
        display.getCanvas().addKeyListener(keyboardManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        // initialize the assets
        // what this will do is
        // load the spritesheet
        // crop the spritesheet to subimage
        // assign them to static variables in the asset class
        Asset.init();

        menuState = new MenuState(this);
        State.setCurrentState(menuState); //  set the current state of the game to game state
    }

    public void tick() {
        keyboardManager.tick();
        if(State.getCurrentState() != null) {
            State.getCurrentState().tick(); // run the tick method in the current state, here is game state
        }
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2); // create the buffer strategy if there is none
            return; // return to prevent error
        }
        g = bs.getDrawGraphics(); // magical paint brush

        // clear canvas
        g.clearRect(0, 0, this.width, this.height); // clear the window before every rendering

        // Start drawing here
        if(State.getCurrentState() != null) { // check if the current state is set
            State.getCurrentState().render(g); // run the render method in the game state
        }

        // End drawing
        bs.show();
        g.dispose();
    }

    public KeyboardManager getKeyboardManager() {
        return keyboardManager;
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
            delta += (now - lastTime) / timePerTick; // divide the tick to check how much time per timePerTick is use
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {

                tick();
                ticks += 1; // increment to count the tick
                render();
                delta -= 1; // one delta is equal to the total time per tick
            }

            if (timer >= 1000000000) {
//                System.out.println(ticks + " fps");
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

    public JFrame getFrame() {
        while(this.display == null) {
            System.out.println("wait");
        }
        return this.display.getFrame();
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public State getGameState() {
        return gameState;
    }
}
