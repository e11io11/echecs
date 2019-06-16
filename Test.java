public class Test {
  public static void main(String[] args) {
    /*
    Partie p = new Partie();
    p.jouer();
    */


    Piece[] plateau = {null,new Roi(false),null,null,null,null,null,null,
                       null,null,null,new Pion(true),null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,new Reine(true),null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,new Tour(true),null,
                       null,null,null,null,new Pion(false),null,null,null,
                       null,new Roi(true),null,null,null,null,null,null};

    Partie partie = new Partie(plateau);

    Menu m = new Menu(partie);
    m.run();

  }
}
