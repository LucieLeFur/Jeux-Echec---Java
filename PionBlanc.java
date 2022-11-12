/****************************************************/
/* Titre : PionBlanc.java                           */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

// La classe PionBlanc est une sous classe de la classe Pion
class PionBlanc extends Pion {

    public PionBlanc() {
        super();
    }

    // pour cree un PionBlanc nous avons besoin de la couleur et de la position. On recuperera c'est attribut dans la classe mère Piece
    public PionBlanc(Position position) {
        super(position, 'B');
    }

    // methode que vas retourner une ArrayListe de Position de tout les deplacement posible pour pour le pion blanc choisie
    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau p) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        // verifie que la case voulue existe
        if (p.casePosible(this.getPosition().getX() - 1, this.getPosition().getY() + 1)) {
            Piece piece = p.getCase(this.getPosition().getX() - 1, this.getPosition().getY() + 1);
            
            // ajoute la position que si il y a un pion de couleur differante sur la case voulue
            if (piece != null && piece.getCouleur() == 'N') {
                listePositionsPossibles.add(piece.getPosition());
            }
        }
        // verifie que la case voulue existe 
        if (p.casePosible(this.getPosition().getX() + 1, this.getPosition().getY() + 1)) {
            Piece piece = p.getCase(this.getPosition().getX() + 1, this.getPosition().getY() + 1);

            // ajoute la position que si il y a un pion de couleur differante sur la case voulue
            if (piece != null && piece.getCouleur() == 'N') {
                listePositionsPossibles.add(piece.getPosition());
            }
        }

        // verifie que la case voulue existe 
        if (p.casePosible(this.getPosition().getX(), this.getPosition().getY() + 1)) {
            Piece piece = p.getCase(this.getPosition().getX(), this.getPosition().getY() + 1);

            // si la case est vide ajoute la position dans la liste
            if (piece == null) {
                listePositionsPossibles.add(new Position(this.getPosition().getX(), this.getPosition().getY() + 1));

                // si le pion blanc ce trouve a la case 1 et que la case 3 est vide alors on ajoute cette position a l'ArrayListe
                if (this.getPosition().getY() == 1
                        && p.getCase(this.getPosition().getX(), this.getPosition().getY() + 2) == null) {
                    listePositionsPossibles.add(new Position(this.getPosition().getX(), this.getPosition().getY() + 2));
                }
            }
        }
        return listePositionsPossibles;
    }
}
