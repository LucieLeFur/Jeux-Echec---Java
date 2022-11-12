/****************************************************/
/* Titre : PionNoir.java                            */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

// La classe PionNoir est une sous classe de la classe Pion
class PionNoir extends Pion {

    public PionNoir() {
        super();
    }

    // pour cree un PionNoir nous avons besoin de la couleur et de la position. On recuperera c'est attribut dans la classe mère Piece
    public PionNoir(Position position) {
        super(position, 'N');
    }

    // methode que vas retourner une ArrayListe de Position de tout les deplacement posible pour pour le pion Noir choisie
    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau p) {
        ArrayList<Position> listePositionsPossibles = new ArrayList<>();

        // verifie que la case voulue existe
        if (p.casePosible(this.getPosition().getX() - 1, this.getPosition().getY() - 1)) {
            Piece piece = p.getCase(this.getPosition().getX() - 1, this.getPosition().getY() - 1);

            // ajoute la position que si il y a un pion de couleur differante sur la case voulue
            if (piece != null && piece.getCouleur() == 'B') {
                listePositionsPossibles.add(piece.getPosition());
            }
        }
        // verifie que la case voulue existe
        if (p.casePosible(this.getPosition().getX() + 1, this.getPosition().getY() - 1)) {
            Piece piece = p.getCase(this.getPosition().getX() + 1, this.getPosition().getY() - 1);

            // ajoute la position que si il y a un pion de couleur differante sur la case voulue
            if (piece != null && piece.getCouleur() == 'B') {
                listePositionsPossibles.add(piece.getPosition());
            }
        }

        // verifie que la case voulue existe
        if (p.casePosible(this.getPosition().getX(), this.getPosition().getY() - 1)) {
            Piece piece = p.getCase(this.getPosition().getX(), this.getPosition().getY() - 1);

            // si la case est vide -> ajoute la position
            if (piece == null) {
                listePositionsPossibles.add(new Position(this.getPosition().getX(), this.getPosition().getY() - 1));

                // si le pion noir ce trouve a la case 6 et que la case 4 est vide alors on ajoute cette position a l'ArrayListe
                if (this.getPosition().getY() == 6
                        && p.getCase(this.getPosition().getX(), this.getPosition().getY() - 2) == null) {
                    listePositionsPossibles.add(new Position(this.getPosition().getX(), this.getPosition().getY() - 2));
                }
            }
        }
        return listePositionsPossibles;
    }
}