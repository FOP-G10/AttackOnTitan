package attackontitan.frontend.input;

import attackontitan.frontend.state.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
    private boolean[] keys;

    public KeyboardManager() {
        keys = new boolean[256];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void tick() {
        if(keys[KeyEvent.VK_ENTER]) {
            GameState.nextRound = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
