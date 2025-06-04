import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{

  public boolean isMouseDragged = false;
  public boolean isMouseClicked = false;
  public int mouseX;
  public int mouseY;

  @Override
  public void mouseDragged(MouseEvent e) {
    isMouseDragged = true;
    mouseX = e.getPoint().x;
    mouseY = e.getPoint().y;
  }

  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    isMouseClicked = true;
    mouseX = e.getX();
    mouseY = e.getY();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    isMouseClicked = false;
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}



}