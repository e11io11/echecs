public class Test {
  public static void main(String[] args) {
    /*
    Partie p = new Partie();
    p.jouer();
    */

    Piece[] plateau = {null,null,null,null,null,null,null,new Roi(false),
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,new Reine(true),null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,new Tour(true),null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null};

    Partie p = new Partie(plateau);
    p.jouer();

  }
}
