import java.awt.*;

public abstract class Piece{
  public boolean couleur;
  public boolean aEffectueUnMouvement;

  public Piece(boolean c){
    this.couleur = c;
    this.aEffectueUnMouvement = false;
  }

  public Piece(Piece p){
    this.couleur = p.getCouleur();
    this.aEffectueUnMouvement = p.aEffectueUnMouvement;
  }

  public boolean getAEffectueUnMouvement() {
    return this.aEffectueUnMouvement;
  }

  public void effectueUnMouvement() {
    this.aEffectueUnMouvement = true;
  }

  public boolean getCouleur(){
    return this.couleur;
  }

  public void setCouleur(boolean c){
    this.couleur = c;
  }

  public int getAvant(){
    if (this.couleur)
      return -1;
    else
      return 1;
  }

  public void afficher(Graphics g, int x, int y){
    Graphics2D g2 = (Graphics2D) g;

   Image img1 = Toolkit.getDefaultToolkit().getImage("img/BlackQueen.png");
   g2.drawImage(img1, x, y, null);
  }

  public abstract int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination);

}
