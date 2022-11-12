/****************************************************/
/* Titre : Dame.java                                */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

// La classe Dame est une sous classe de la classe Piece
class Dame extends Piece{
    
    public Dame(){
        super();
    }

    // pour cree une dame nous avons besoin de la couleur et de la position. On recuperera c'est attribut dans la classe mère Piece
    public Dame(char couleur, Position position){
        super(couleur , position);
    }

    // retoune le type de la piece : " Dame "
    @Override
    public String getType() {
        return "dame";
    }

    // methode que vas retourner une ArrayListe de Position de tout les deplacement posible pour pour la dame choisie
    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau p) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        // ajout dans l'ArrayList "listePositionsPossibles" toute les position trouvez grace a different l'increment de x et y 
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 1, 0));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -1, 0));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 0, 1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 0, -1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -1, -1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 1, -1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, 1, 1));
        listePositionsPossibles.addAll(this.getDeplacementPossibleSens(p, -1, 1));

        // retoune une ArrayList de Position 
        return listePositionsPossibles;
    }

    // methode retounant une ArrayListe de Position posible selon differant increment de x et y choisi
    private ArrayList<Position> getDeplacementPossibleSens(Plateau p, int incrementX, int incrementY) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        // creation d'un nouveau x et y a partir de celle de la dame choisi et de l'increment voulu
        int nouveauX = this.getPosition().getX() + incrementX;
        int nouveauY = this.getPosition().getY() + incrementY;

        // condition d'arret de la boucle
        boolean deplacemementPossible = true;

        // tant que le deplacement n'est pas possible et que la case n'existe pas 
        while (deplacemementPossible && p.casePosible(nouveauX, nouveauY)) {

            // creation d'une Piece avec les nouvelles coordonnes
            Piece piece = p.getCase(nouveauX, nouveauY);

            // ajout de la position si la case est nul ou si il y a une piece adverse
            if (piece == null) {
                listePositionsPossibles.add(new Position(nouveauX, nouveauY));
                nouveauX = nouveauX + incrementX;
                nouveauY = nouveauY + incrementY;
            } else if (this.getCouleur() != piece.getCouleur()) {
                listePositionsPossibles.add(new Position(nouveauX, nouveauY));
                deplacemementPossible = false;
            } else {
                deplacemementPossible = false;
            }
        }
        // retoune une ArrayList de Position 
        return listePositionsPossibles;
    }
    
}
