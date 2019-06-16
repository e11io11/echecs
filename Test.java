public class Test {
  public static void main(String[] args) {

    //Test mouvement
    
    Menu m = new Menu();
    m.run();
    



    //Test echec/echec et mat
    /*
    Piece[] plateau = {new Roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new Tour(true),null,null,null,new Pion(false),new Reine(false),null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,new Roi(true)};
    Partie partie = new Partie(plateau);
    Menu m = new Menu(partie);
    m.run();
    */



    //Test pat
    /*
    Piece[] plateau = {new Roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,new Tour(true),null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new Tour(true),null,null,null,null,null,new Roi(true)};
    Partie partie = new Partie(plateau);
    Menu m = new Menu(partie);
    m.run();
    */


    //Test roque
    /*
    Piece[] plateau = new Piece[64];
    //1ere rangée noir
    plateau[0] = new Tour(false);
    plateau[4] = new Roi(false);
    plateau[15] = new Tour(false);

    //2e rangée noir
    for(int i=8; i<15; i++)
      plateau[i] = new Pion(false);

    //2e rangée blanc
    for(int i=48; i<56; i++)
      plateau[i] = new Pion(true);

    //1ere rangée blanc
    plateau[56] = new Tour(true);
    plateau[57] = new Cavalier(true);
    plateau[58] = new Fou(true);
    plateau[59] = new Reine(true);
    plateau[60] = new Roi(true);
    plateau[63] = new Tour(true);
    Partie partie = new Partie(plateau);
    Menu m = new Menu(partie);
    m.run();
    */



    //Test promotion
    /*
    Piece[] plateau = {new Roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,new Pion(true),null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new Pion(false),new Tour(false),null,null,null,null,null,
                       null,null,null,null,null,null,null,new Roi(true)};
    Partie partie = new Partie(plateau);
    Menu m = new Menu(partie);
    m.run();
    */

  }
}
