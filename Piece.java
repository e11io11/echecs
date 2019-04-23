public abstract class Piece{
  public boolean couleur;

  public Piece(boolean c){
    this.couleur = c;
  }

  public Piece(Piece p){
    this.couleur = p.getCouleur();
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

  public abstract int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination);

}
