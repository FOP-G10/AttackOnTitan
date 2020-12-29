package attackontitan.frontend.input;

import attackontitan.frontend.entities.Wall;
import attackontitan.frontend.entities.Weapon;
import attackontitan.frontend.state.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftPressed, rightPressed;
    private static int count = 0;

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

    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1) {
//            leftPressed = true;
//        }else {
//            leftPressed = false;
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
//            System.out.println("left pressed");
            leftPressed = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        }

        for (int i=0; i<10; i++) {
            Wall.walls[i].onMouseReleased(e);
            Weapon.weapons[i].onMouseReleased(e);
        }
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
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if(State.getCurrentState().toString().equals("Game")) {
            for (int i=0; i<10; i++) {
                Wall.walls[i].onMouseMove(e);
                Weapon.weapons[i].onMouseMove(e);
            }
        }

    }
}
