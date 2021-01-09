package attackontitan.frontend.input;

import attackontitan.frontend.entities.Wall;
import attackontitan.frontend.entities.Weapon;
import attackontitan.frontend.state.GameState;
import attackontitan.frontend.state.MenuState;
import attackontitan.frontend.state.State;
import attackontitan.frontend.world.Stats;
import attackontitan.frontend.world.World;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftPressed;

    public MouseManager() {

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void noPressed() {
        leftPressed = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public synchronized void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        }
        System.out.println("Pressed");
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }

        if (State.getCurrentState().toString().equals("Game")) {

            for (int i = 0; i < 10; i++) {
                Wall.walls[i].onMouseReleased();
                Weapon.weapons[i].onMouseReleased();
            }
            GameState.onMouseReleased();

            World.onMouseReleased();
        }

        MenuState.onMouseReleased();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public synchronized void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(State.getCurrentState() != null && State.getCurrentState().toString().equals("Game")) {
            for (int i=0; i<10; i++) {
                Wall.walls[i].onMouseMove(e);
                Weapon.weapons[i].onMouseMove(e);
            }
            GameState.onMouseMoved(e);
            World.onMouseMove(e);
        }

        MenuState.onMouseMoved(e);
    }
}
