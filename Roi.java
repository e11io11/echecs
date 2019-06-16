import java.awt.*;

public class Roi extends Piece{

  private static final long serialVersionUID = -4430023746076062020L;





  public Roi(boolean c) {
    super(c);
  }





  public Roi(Roi r){
    super(r);
  }





  public String toString(){
    return "6";
  }






  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    if ((xPiece - xDestination == 1 || xPiece - xDestination == 0 || xPiece - xDestination == -1) && (yPiece - yDestination == 1 || yPiece - yDestination == 0 || yPiece - yDestination == -1))
      //mouvement qui ne requiert pas de check les pieces sur le chemin
      return 1;
    else if (xPiece == 4 && yPiece == yDestination && ((this.couleur && yPiece == 7) || (!this.couleur && yPiece == 0))){
      if (xDestination ==  2)
        //grand roque
        return 13;
      else if (xDestination == 6)
        //petit roque
        return 14;
    }
    return 0;
  }





  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("img/WhiteKing.png");
    else
      return Toolkit.getDefaultToolkit().getImage("img/BlackKing.png");
  }


  
}
