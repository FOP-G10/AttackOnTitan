package attackontitan.frontend.input;

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
            leftPressed = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("released");
            leftPressed = true;
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
    }
}
