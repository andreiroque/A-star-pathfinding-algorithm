import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

  final int width = 1280;
  final int height = 720;

  private int boxSize = 20;

  int FPS = 60;

  Thread gameThread;

  public GamePanel(){
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.setFocusable(true);
  }

  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    long drawCount = 0;

    while(gameThread != null){

      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer += (currentTime - lastTime);

      lastTime = currentTime;

      if(delta >= 1){
        update();
        repaint();

        delta--;
        drawCount++;
      }

      if(timer >= 1000000000){
        System.out.println("FPS: " + drawCount);
        drawCount = 0;
        timer = 0;
      }

    }
  }

  public void update(){

  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
  }

}