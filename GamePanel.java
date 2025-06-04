import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

  final int width = 1280;
  final int height = 720;

  private int boxSize = 20;

  int FPS = 60;

  MouseHandler mouseH = new MouseHandler();
  Thread gameThread;

  ArrayList<Node> wallList = new ArrayList<>();

  public GamePanel(){
    this.setPreferredSize(new Dimension(width, height));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addMouseListener(mouseH);
    this.addMouseMotionListener(mouseH);
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

    int mouseX = mouseH.mouseX;
    int mouseY = mouseH.mouseY;

    int gridX = mouseX / boxSize;
    int gridY = mouseY / boxSize;

    if(mouseH.isMouseClicked || mouseH.isMouseDragged){
      wallList.add(new Node(gridX * boxSize, gridY * boxSize));
    }

  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;


    // height (720) / boxSize (20) = 36
    // width (1280) / boxSize (20) = 64

    // draw 64 x 36 grid on the window
    g2.setColor(Color.black);
    for(int i = 0; i < height; i+=boxSize){
      for(int j = 0; j < width; j+=boxSize){
        g2.drawRect(j, i, boxSize, boxSize);
      }
    }

    // display walls
    for(int i = 0; i < wallList.size(); i++){
      g2.fillRect(wallList.get(i).x, wallList.get(i).y, boxSize, boxSize);
    }

    g2.dispose();
  }

}