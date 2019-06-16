import java.awt.*;

public class Cavalier extends Piece{

  private static final long serialVersionUID = -480779306599009373L;





  public Cavalier(boolean c) {
    super(c);
  }





  public Cavalier(Cavalier c){
    super(c);
  }





  public String toString(){
    return "3";
  }

  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    if ((xDestination == xPiece-1 && (yDestination == yPiece-2 || yDestination == yPiece+2))
     || (xDestination == xPiece+1 && (yDestination == yPiece-2 || yDestination == yPiece+2))
     || (xDestination == xPiece-2 && (yDestination == yPiece-1 || yDestination == yPiece+1))
     || (xDestination == xPiece+2 && (yDestination == yPiece-1 || yDestination == yPiece+1)))
      // mouvement qui ne requiert pas de check les pieces sur le chemin
      return 1;
    return 0;
  }






  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("img/WhiteKnight.png");
    else
      return Toolkit.getDefaultToolkit().getImage("img/BlackKnight.png");
  }



}