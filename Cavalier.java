/****************************************************/
/* Titre : Cavalier.java                            */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

// La classe cavalier est une sous classe de la classe Piece
class Cavalier extends Piece {

    public Cavalier() {
        super();
    }

    // pour cree un cavalier nous avons besoin de la couleur et de la position. On recuperera c'est attribut dans la classe mère Piece
    public Cavalier(char couleur, Position position) {
        super(couleur, position);
    }

    // retoune le type de la piece : " cavalier "
    @Override
    public String getType() {
        return "cavalier";
    }

    // methode que vas retourner une ArrayListe de Position de tout les deplacement posible pour pour le cavalier choisie
    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau p) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        // ajout dans l'ArrayList "listePositionsPossibles" toute les position trouvez grace a different l'increment de x et y 
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -2, -1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -1, -2));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 1, -2));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 2, -1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 2, 1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 1, 2));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -1, 2));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -2, 1));

        // retourne une ArrayList de Position
        return listePositionsPossibles;
    }

    // methode retounant une ArrayListe de Position posible selon differant increment de x et y
    private ArrayList<Position> getDeplacementPossibleSens(Plateau p, int incrementX, int incrementY) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        int nouveauX = this.getPosition().getX() + incrementX;
        int nouveauY = this.getPosition().getY() + incrementY;

        // verification que la case a analyser existe 
        if (p.casePosible(nouveauX, nouveauY)) {
            // creation d'une Piece avec la nouvelle Position
            Piece piece = p.getCase(nouveauX, nouveauY);

            // ajout de la position si la case est nul ou si il y a une piece adverse
            if (piece == null) {
                listePositionsPossibles.add(new Position(nouveauX, nouveauY));
            } else if (this.getCouleur() != piece.getCouleur()) {
                listePositionsPossibles.add(new Position(nouveauX, nouveauY));
            } 
        }

        // retourne une ArrayList de Position
        return listePositionsPossibles;
    }

}
