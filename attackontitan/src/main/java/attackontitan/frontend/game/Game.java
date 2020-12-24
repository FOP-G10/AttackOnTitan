package attackontitan.frontend.game;

import attackontitan.frontend.display.Display;

public class Game implements Runnable {
    String title;
    int width, height;
    Display display;
    boolean running = false;
    Thread thread;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);
    }

    public void tick() {

    }

    public void render() {

    }

    @Override
    public void run() {
        init();

    }

    public void start() {
        if(running) {
            return;
        }
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void stop() {
        if(!running) {
            return;
        }
        this.running = false;

        try{
            this.thread.join();
        }catch(InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}
