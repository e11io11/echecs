public class Cavalier extends Piece{

  public Cavalier(boolean c){
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
      return 1;
    return 0;
  }
}
