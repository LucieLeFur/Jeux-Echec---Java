/****************************************************/
/* Titre : Plateau.java                             */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

class Plateau {

    private ArrayList<Piece> plateau;

    // constructeur plateau
    public Plateau() {
        plateau = new ArrayList<>();

        plateau.add(new Tour('B', new Position(0, 0)));
        plateau.add(new Cavalier('B', new Position(1, 0)));
        plateau.add(new Fou('B', new Position(2, 0)));
        plateau.add(new Dame('B', new Position(3, 0)));
        plateau.add(new Roi('B', new Position(4, 0)));
        plateau.add(new Fou('B', new Position(5, 0)));
        plateau.add(new Cavalier('B', new Position(6, 0)));
        plateau.add(new Tour('B', new Position(7, 0)));

        plateau.add(new PionBlanc(new Position(0, 1)));
        plateau.add(new PionBlanc(new Position(1, 1)));
        plateau.add(new PionBlanc(new Position(2, 1)));
        plateau.add(new PionBlanc(new Position(3, 1)));
        plateau.add(new PionBlanc(new Position(4, 1)));
        plateau.add(new PionBlanc(new Position(5, 1)));
        plateau.add(new PionBlanc(new Position(6, 1)));
        plateau.add(new PionBlanc(new Position(7, 1)));

        plateau.add(new Tour('N', new Position(0, 7)));
        plateau.add(new Cavalier('N', new Position(1, 7)));
        plateau.add(new Fou('N', new Position(2, 7)));
        plateau.add(new Dame('N', new Position(3, 7)));
        plateau.add(new Roi('N', new Position(4, 7)));
        plateau.add(new Fou('N', new Position(5, 7)));
        plateau.add(new Cavalier('N', new Position(6, 7)));
        plateau.add(new Tour('N', new Position(7, 7)));

        plateau.add(new PionNoir(new Position(0, 6)));
        plateau.add(new PionNoir(new Position(1, 6)));
        plateau.add(new PionNoir(new Position(2, 6)));
        plateau.add(new PionNoir(new Position(3, 6)));
        plateau.add(new PionNoir(new Position(4, 6)));
        plateau.add(new PionNoir(new Position(5, 6)));
        plateau.add(new PionNoir(new Position(6, 6)));
        plateau.add(new PionNoir(new Position(7, 6)));

    }

    // constructeur par copie 
    public Plateau(Plateau p) {
        plateau = new ArrayList<>();

        for (int i = 0; i < p.getPiecesPlateau().size(); i++) {
            Piece piece = p.getPiecesPlateau().get(i);
            if (piece instanceof Tour) {
                plateau.add(new Tour(piece.getCouleur(), new Position(piece.getPosition())));
            }
            else if (piece instanceof Cavalier) {
                plateau.add(new Cavalier(piece.getCouleur(), new Position(piece.getPosition())));
            }
            else if (piece instanceof Fou) {
                plateau.add(new Fou(piece.getCouleur(), new Position(piece.getPosition())));
            }
            else if (piece instanceof Dame) {
                plateau.add(new Dame(piece.getCouleur(), new Position(piece.getPosition())));
            }
            else if (piece instanceof Roi) {
                plateau.add(new Roi(piece.getCouleur(), new Position(piece.getPosition())));
            }      
            else if (piece instanceof PionNoir) {
                plateau.add(new PionNoir(new Position(piece.getPosition())));
            }       
            else if (piece instanceof PionBlanc) {
                plateau.add(new PionBlanc(new Position(piece.getPosition())));
            }                                  
        }
    }

    // methode qui retourne le plateau
    public ArrayList<Piece> getPiecesPlateau() {
        return this.plateau;
    }

    // methode qui donne la piece qui ce trouve a telle position avec des entier
    public Piece getCase(int x, int y) {

        Position p = new Position(x, y);
        for (int i = 0; i < plateau.size(); i++) {
            if (plateau.get(i).getPosition().equals(p)) {
                return plateau.get(i);
            }
        }
        // retoune null si il y a rien
        return null;

    }

    // methode qui retoune si la case existe
    public boolean casePosible(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }
        return true;
    }

    // methode qui donne la piece qui ce trouve a telle position
    public Piece getCase(Position p) {

        for (int i = 0; i < plateau.size(); i++) {
            if (plateau.get(i).getPosition().equals(p)) {
                return plateau.get(i);
            }
        }
        // retoune null si il y a rien
        return null;

    }

    // methode qui retoune le roi choisie
    public Piece getRoi(char couleur) {

        for (int i = 0; i < plateau.size(); i++) {
            Piece piece = plateau.get(i);

            if (piece instanceof Roi && piece.getCouleur() == couleur) {
                return piece;
            }
        }
        return null;
    }

    // methode qui regarde si le roi est en echec
    public boolean estEchec(char couleur) {
        Piece leRoi = getRoi(couleur);
        ArrayList<Piece> pieceAdverse;

        if (couleur == 'N') {
            pieceAdverse = this.getPiecesBlanches();
        } else {
            pieceAdverse = this.getPiecesNoires();
        }
        // boucle sur toute les piece adverse est regarde si le roi est en echec
        for (int i = 0; i < pieceAdverse.size(); i++) {
            if (pieceAdverse.get(i).getDeplacementPossible(this).contains(leRoi.getPosition())) {
                return true;
            }
        }
        return false;
    }

    // methode qui donne la piece qui ce trouve a telle position
    public Piece getCase(String pos) {

        Position p = new Position(pos);
        for (int i = 0; i < plateau.size(); i++) {
            if (plateau.get(i).getPosition().equals(p)) {
                return plateau.get(i);
            }
        }

        return null;
    }

    // methode qui donne la liste des piece blanche qui reste
    public ArrayList<Piece> getPiecesBlanches() {

        ArrayList<Piece> platt;
        platt = new ArrayList<>();

        for (int i = 0; i < plateau.size(); i++) {
            if (plateau.get(i).getCouleur() == 'B') {
                platt.add(plateau.get(i));
            }
        }
        return platt;
    }

    // methode qui donne la liste des piece blanche qui reste
    public ArrayList<Piece> getPiecesNoires() {

        ArrayList<Piece> platt;
        platt = new ArrayList<>();

        for (int i = 0; i < plateau.size(); i++) {
            if (plateau.get(i).getCouleur() == 'N') {
                platt.add(plateau.get(i));
            }
        }
        return platt;
    }

    // methode qui verifie si la piece peut etre deplacer
    public boolean deplacer(Position from, Position to) {
        Piece pieceFrom = this.getCase(from);
        // si le piece est null -> retoune faux
        if (pieceFrom == null) {
            return false;
        } else {
            ArrayList<Position> deplacementsPossiblesPiece = pieceFrom.getDeplacementPossible(this);
            if (deplacementsPossiblesPiece.contains(to)) {
                Piece pieceTo = this.getCase(to);
                // si il y a une piece -> supprime la piece sur la case "to"
                if (pieceTo != null) {
                    this.plateau.remove(pieceTo);
                }
                pieceFrom.setPosition(new Position(to));

                return true;
            }
        }
        return false;
    }

    // affiche le plateau
    public String toString() {
        String chh = "";
        for (int y = 0; y <= 7; y++) {
            chh = chh + "|---|---|---|---|---|---|---|---|\n";
            for (int x = 0; x <= 7; x++) {

                if (this.getCase(x, y) == null) {
                    chh = chh + "|" + "   ";
                    if (y == 7)
                        chh = chh + "|";
                } else if (x == 7)
                    chh = chh + "|" + this.getCase(x, y).getNomCourt() + "|";

                else
                    chh = chh + "|" + this.getCase(x, y).getNomCourt();
            }
            chh = chh + "\n";
        }
        return chh;

    }

    public static void main(String[] args) {

    }
}