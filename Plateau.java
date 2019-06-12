import java.awt.*;
import javax.swing.*;
import java.util.*;


public class Plateau extends JFrame {
  public DrawPanel drawPanel;
  public Piece[] plateau;

  public Plateau(Piece[] plateau){
    super();
    this.plateau = plateau;
    this.setTitle("Plateau");
    this.getContentPane().setPreferredSize(new Dimension(640, 640));
    this.pack();
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.drawPanel = new DrawPanel();
    this.add(drawPanel, BorderLayout.CENTER);
  }

  public void refreshPlateau(Piece[] plateau){
    this.plateau = plateau;
    drawPanel.repaint();
  }


  class DrawPanel extends JPanel {
    public DrawPanel(){
      this.setOpaque(true);

      this.setBackground(Color.WHITE);
    }

    public void paint(Graphics g){
      super.paintComponent(g);
      g.setColor(Color.LIGHT_GRAY);
      this.drawPlateau(g);
      //this.drawPiece(g);
    }

    public void drawPlateau(Graphics g){
      for (int i=0; i<8; i++){
        if (i%2 == 0){
          g.fillRect(80, i*80, 80, 80);
          g.fillRect(240, i*80, 80, 80);
          g.fillRect(400, i*80, 80, 80);
          g.fillRect(560, i*80, 80, 80);
        }

        else {
          g.fillRect(0, i*80, 80, 80);
          g.fillRect(160, i*80, 80, 80);
          g.fillRect(320, i*80, 80, 80);
          g.fillRect(480, i*80, 80, 80);
        }
      }
    }

    public void drawPiece(Graphics g){
      for (int i=0; i<8; i++){
        for (int j=0; j<8; j++){
          plateau[i*8+j].afficher(g, i*80,j*80);
        }
      }
    }
  }

}
