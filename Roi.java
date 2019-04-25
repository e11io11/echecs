public class Roi extends Piece{

  public Roi(boolean c){
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
      return 1;
    else if (!this.aEffectueUnMouvement && yPiece == yDestination){
      if (xDestination ==  2)
        return 13;
      else if (xDestination == 6)
        return 14;
    }
    return 0;
  }
}
