public class Pion extends Piece{

  public Pion(boolean c){
    super(c);
  }

  public Pion(Pion p){
    super(p);
  }

  public String toString(){
    return "1";
  }

  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    int avant = getAvant();
    if (xDestination == xPiece && yDestination - yPiece == avant)
      //mouvement en avant qui requiert qu'il n'y ai pas d'ennemi sur la case de destination
      return 2;
    else if (xDestination == xPiece && yDestination - yPiece == 2*avant)
      //mouvement qui requiert que le pion n'ai pas deja effectué un mouvement et qu'il n'y ai pas d'enemi sur le chemin
      return 3;
    else if ((xDestination - xPiece == -1 && yDestination - yPiece == avant) || (xDestination - xPiece == 1 && yDestination - yPiece == avant))
      //mouvement qui requiert qu'il y ai une piece ennemi en haut à gauche
      return 4;
    else
      return 0;

  }
}
