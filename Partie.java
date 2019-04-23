public class Partie{
  private Piece[] plateau;
  private boolean joueur;
  private String historique;


  public Partie(){
    this.plateau = new Piece[64];
    this.initPlateau();
    this.joueur = true;
    this.historique = "";
  }

  public Partie(Partie p){
    this.plateau = new Piece[64];
    Piece[] pPlateau = p.getPlateau();
    for (int i=0; i<64; i++){
      Piece pPiece = pPlateau[i];
      if (pPiece == null)
        this.plateau[i] = null;
      else if (pPiece.getClass() == Pion.class)
        this.plateau[i] = new Pion((Pion) pPiece);
      else if (pPiece.getClass() == Tour.class)
        this.plateau[i] = new Tour((Tour) pPiece);
      else if (pPiece.getClass() == Cavalier.class)
        this.plateau[i] = new Cavalier((Cavalier) pPiece);
      else if (pPiece.getClass() == Fou.class)
        this.plateau[i] = new Fou((Fou) pPiece);
      else if (pPiece.getClass() == Reine.class)
        this.plateau[i] = new Reine((Reine) pPiece);
      else
        this.plateau[i] = new Roi((Roi) pPiece);
    }
    this.joueur = p.getJoueur();
    this.historique = p.getHistorique();
  }

  public Partie(Piece[] plateau){
    this.plateau = plateau;
    this.joueur = true;
    this.historique = "";
  }

  private void initPlateau(){
    //1ere rangée noir
    this.plateau[0] = new Tour(false);
    this.plateau[1] = new Cavalier(false);
    this.plateau[2] = new Fou(false);
    this.plateau[3] = new Reine(false);
    this.plateau[4] = new Roi(false);
    this.plateau[5] = new Fou(false);
    this.plateau[6] = new Cavalier(false);
    this.plateau[7] = new Tour(false);

    //2e rangée noir
    for(int i=8; i<16; i++)
      this.plateau[i] = new Pion(false);

    //2e rangée blanc
    for(int i=48; i<56; i++)
      this.plateau[i] = new Pion(true);

    //1ere rangée blanc
    this.plateau[56] = new Tour(true);
    this.plateau[57] = new Cavalier(true);
    this.plateau[58] = new Fou(true);
    this.plateau[59] = new Reine(true);
    this.plateau[60] = new Roi(true);
    this.plateau[61] = new Fou(true);
    this.plateau[62] = new Cavalier(true);
    this.plateau[63] = new Tour(true);
  }

  public Piece[] getPlateau(){
    return this.plateau;
  }

  public boolean getJoueur(){
    return this.joueur;
  }

  public String getHistorique(){
    return this.historique;
  }

  public void setJoueur(boolean j){
    this.joueur = j;
  }

  public void nextJoueur(){
    this.joueur = !this.joueur;
  }

  public boolean deplacementPossible(int xPiece, int yPiece, int xDestination, int yDestination, boolean joueur){
    //verifie qu'il y a bel et bien un déplacement
    if (xPiece == xDestination && yPiece == yDestination){
      //System.out.println("aucun deplacement effectue");
      return false;
    }

    //verifie que la case contenant la piece existe
    if (!(xPiece <= 7 && xPiece >= 0 ) || !(yPiece <= 7 && yPiece >= 0 )){
      //System.out.println("la case indiquée n'existe pas ");
      return false;
    }

    //verifie que la case de destination existe
    if (!(xDestination <= 7 && xDestination >= 0 ) || !(yDestination <= 7 && yDestination >= 0 )){
      //System.out.println("la case indiquée n'existe pas ");
      return false;
    }

    Piece pieceDepart = this.plateau[xPiece + 8*yPiece];
    Piece pieceArrivee = this.plateau[xDestination + 8*yDestination];

    //verifie qu'il y a bien une piece alliée sur la case de depart
    if (pieceDepart == null || pieceDepart.getCouleur() != joueur){
      //System.out.println("il n'y a aucune piece sur cette case");
      return false;
    }

    //verifie qu'il n'y a pas de piece allié sur la case de destination
    if (pieceArrivee != null && pieceArrivee.getCouleur() == joueur){
      //System.out.println("la case d'arivée contient un pion allié");
      return false;
    }

    /*
    Regarde quel type de mouvement est effectué:
    0 -> mouvement impossible
    1 -> mouvement qui ne requiert pas de check les pieces sur le chemin
    2 -> mouvement en avant qui requiert qu'il n'y ai pas d'ennemi sur la case de destination
    3 -> mouvement qui requiert que le pion n'ai pas deja effectué un mouvement et qu'il n'y ai pas d'enemi sur le chemin
    4 -> mouvement qui requiert qu'il y ai une piece ennemi sur la case d'arrivee
    5 -> mouvement en diagonale haut gauche + check chemin
    6 -> mouvement en diagonale haut droite + check chemin
    7 -> mouvement en diagonale bas gauche + check chemin
    8 -> mouvement en diagonale bas droite + check chemin
    9 -> mouvement en ligne haut + check chemin
    10 -> mouvement en ligne bas + check chemin
    11 -> mouvement en ligne gauche + check chemin
    12 -> mouvement en ligne droite + check chemin
    13 -> grand roque
    14 -> petit roque
    */
    int typeMouvement = pieceDepart.typeMouvement(xPiece, yPiece, xDestination, yDestination);
    int avant = pieceDepart.getAvant();


    //mouvement impossible si typeMouvement = 0
    if (typeMouvement == 0){
      //System.out.println("le mouvement indiquée est impossible");
      return false;
    }

    else if(typeMouvement == 1){
      return true;
    }

    //On applique differents tests en fonction des requierement specifiques du move (ex: qu'il n'ai pas de piece entre la case de depart et la case d'arrivee)
    else if (typeMouvement == 2){
      if (pieceArrivee != null && pieceArrivee.getCouleur() != joueur)
        return false;
    }

    else if (typeMouvement == 3){
      if (((Pion)pieceDepart).getAEffectueUnMouvement() || pieceArrivee != null || this.plateau[xPiece + 8*(yPiece+avant)] != null)
        return false;
    }

    else if (typeMouvement == 4){
      if (pieceArrivee == null || pieceArrivee.getCouleur() == joueur)
        return false;
    }

    else if (typeMouvement == 5 || typeMouvement == 6 || typeMouvement == 7 || typeMouvement == 8){
      int xDirection;
      int yDirection;
      if (typeMouvement == 5){
        xDirection = -1;
        yDirection = -1;
      }
      else if (typeMouvement == 6){
        xDirection = 1;
        yDirection = -1;
      }
      else if (typeMouvement == 7){

        xDirection = -1;
        yDirection = 1;
      }
      else{
        xDirection = 1;
        yDirection = 1;
      }

      for(int i=1; i<Math.abs(xDestination - xPiece); i++){
        if (this.plateau[(xPiece+xDirection*i) + 8*(yPiece+yDirection*i)] != null){
          return false;
        }
      }
    }

    else if(typeMouvement == 9 || typeMouvement == 10 || typeMouvement == 11 || typeMouvement == 12){
      if (typeMouvement == 9){
        for(int i = 1; i < yPiece-yDestination; i++){
          if (this.plateau[xPiece + 8*(yPiece-i)] != null)
            return false;
        }
      }
      else if (typeMouvement == 10){
        for(int i = 1; i < yDestination-yPiece; i++){
          if (this.plateau[xPiece + 8*(yPiece+i)] != null)
            return false;
        }
      }
      else if (typeMouvement == 11){
        for(int i = 1; i < xPiece-xDestination; i++){
          if (this.plateau[xPiece-i + 8*yPiece] != null)
            return false;
        }
      }
      else{
        for(int i = 1; i < xDestination-xPiece; i++){
          if (this.plateau[xPiece+i + 8*yPiece] != null)
            return false;
        }
      }
    }

    else if (mouvement == 13){
      if (!grandRoquePossible())
        return false;
    }

    else{
      if (!petitRoquePossible())
        return false;
    }

    return true;
  }

  public boolean deplacerPiece(int xPiece, int yPiece, int xDestination, int yDestination){
    if (!this.deplacementPossible(xPiece, yPiece, xDestination, yDestination, this.joueur) || this.enEchecApresMouvemement(xPiece, yPiece, xDestination, yDestination))
      return false;

    Piece pieceDepart = this.plateau[xPiece + 8*yPiece];
    Piece pieceArrivee = this.plateau[xDestination + 8*yDestination];

    //on prend en compte qu'un pion a effectué un mouvement pour eviter qu'il puisse refaire un mouvement de deux cases
    if (pieceDepart.getClass() == Pion.class){
      ((Pion)pieceDepart).effectueUnMouvement();
    }

    //enregistrement du coup dans l'historique
    this.historique += "{"+pieceDepart+","+xPiece+","+yPiece+","+pieceArrivee+","+xDestination+","+yDestination+","+this.joueur+"}";

    //le mouvement est effectué
    this.plateau[xPiece + 8*yPiece] = null;
    this.plateau[xDestination + 8*yDestination] = pieceDepart;

    return true;
  }


  public int[] getPosRoi(){
    int xRoi = -1;
    int yRoi = -1;
    for(int i = 0; i<64 && xRoi == -1; i++){
      if(this.plateau[i] != null && this.plateau[i].getClass() == Roi.class && this.plateau[i].getCouleur() == this.joueur){
        xRoi = i%8;
        yRoi = i/8;
      }
    }
    return new int[] {xRoi, yRoi};
  }


  public boolean enEchec(){
    int[] posRoi = this.getPosRoi();
    int xRoi = posRoi[0];
    int yRoi = posRoi[1];
    boolean ennemi = !this.joueur;

    for(int i = 0; i<64; i++){
      if (this.plateau[i] != null && this.plateau[i].getCouleur() == ennemi && this.deplacementPossible(i%8, i/8, xRoi, yRoi, ennemi))
        return true;
    }
    return false;
  }

  public boolean enEchecApresMouvemement(int xPiece, int yPiece, int xDestination, int yDestination){
    //on cree une copie de la partie
    Partie copiePartie = new Partie(this);
    //on simule le mouvement
    Piece pieceDepart = copiePartie.plateau[xPiece + 8*yPiece];
    copiePartie.plateau[xPiece + 8*yPiece] = null;
    copiePartie.plateau[xDestination + 8*yDestination] = pieceDepart;
    //on regarde si le move met le joueur en echec
    return copiePartie.enEchec();
  }

  public void afficherPlateau(){
    for(int i=0; i<8; i++){
      String row = "";
      for(int j=0; j<8; j++){
        if (this.plateau[8*i+j] == null)
          row += "0";
        else
          row += this.plateau[8*i+j];
      }
      System.out.println(row);
    }
  }

  public boolean echecEtMat(){
    if (enEchec()){
      for(int i=0; i<64; i++){
        if (this.plateau[i] != null && this.plateau[i].getCouleur() == this.joueur){
          for(int j=0; j<64; j++){
            if (this.deplacementPossible(i%8, i/8, j%8, j/8, this.joueur) && !this.enEchecApresMouvemement(i%8, i/8, j%8, j/8))
              return false;
          }
        }
      }
      return true;
    }
    return false;
  }


  public void jouer(){
  }




  public static void main(String[] args) {
    /*
    Partie p = new Partie();
    p.afficherPlateau();
    p.deplacerPiece(4, 6, 4, 4);
    System.out.println("\n");
    p.afficherPlateau();
    p.deplacerPiece(1, 7, 2, 5);
    System.out.println("\n");
    p.afficherPlateau();
    System.out.println("\n"+p.getHistorique());
    p.nextJoueur();
    p.deplacerPiece(3, 1, 3, 2);
    System.out.println("\n");
    p.afficherPlateau();
    p.nextJoueur();
    p.deplacerPiece(5, 7, 1, 3);
    System.out.println("\n");
    p.afficherPlateau();
    p.nextJoueur();
    p.deplacerPiece(1, 0, 0, 2);
    System.out.println("\n");
    p.afficherPlateau();
    p.deplacerPiece(3, 0, 3, 1);
    System.out.println("\n");
    p.afficherPlateau();
    p.nextJoueur();
    p.deplacerPiece(1, 3, 3, 1);
    System.out.println("\n");
    p.afficherPlateau();
    p.nextJoueur();
    p.deplacerPiece(4, 0, 3, 1);
    System.out.println("\n");
    p.afficherPlateau();
    p.nextJoueur();
    p.deplacerPiece(3, 6, 4, 5);
    System.out.println("\n");
    p.afficherPlateau();
    p.deplacerPiece(4, 4, 4, 2);
    System.out.println("\n");
    p.afficherPlateau();
    */

    Piece[] plateau = new Piece[64];
    plateau[7] = new Roi(false);
    plateau[63] = new Roi(true);
    plateau[30] = new Reine(true);
    plateau[21] = new Pion(true);
    Partie p2 = new Partie(plateau);

    p2.afficherPlateau();
    System.out.println("\n");
    p2.nextJoueur();
    System.out.println(p2.echecEtMat());
    System.out.println("\n");
    p2.nextJoueur();
    p2.deplacerPiece(6, 3, 6, 1);
    p2.afficherPlateau();
    System.out.println("\n");
    p2.nextJoueur();
    System.out.println(p2.echecEtMat());
  }
}
