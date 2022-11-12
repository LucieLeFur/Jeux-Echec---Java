/****************************************************/
/* Titre : MainGraphique.java                       */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.awt.Font;
import java.util.ArrayList;

import MG2D.*;
import MG2D.geometrie.*;

public class MainGraphique {

    // creaton d'uen methode qui vas permetre de construire le plateau
    public static void constructionPlateau(Fenetre f, Plateau plateau) {

        // boucle sur les 64 case du plateau
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Piece p = plateau.getCase(i, j);

                // construction des case noire
                if ((i + j) % 2 == 0) {

                    // si la case est vide -> ajoute un carre gris
                    if (p == null) {
                        Carre c = new Carre(Couleur.GRIS, new Point(i * 75, j * 75), 75, true);
                        f.ajouter(c);
                        f.rafraichir();
                    
                    // si la case possède une piece -> ajoute un carre gris + image de la piece
                    } else {
                        Carre c = new Carre(Couleur.GRIS, new Point(i * 75, j * 75), 75, true);
                        f.ajouter(c);
                        Texture t = new Texture("images/" + p.getType() + "_" + p.getCouleur() + ".png",
                                new Point(i * 75, j * 75), 75, 75);
                        f.ajouter(t);
                        f.rafraichir();
                    }
                
                // construction des case blanche
                } else {

                    // si la case est vide -> ajoute un carre blanche
                    if (p == null) {
                        Carre c = new Carre(Couleur.BLANC, new Point(i * 75, j * 75), 75, true);
                        f.ajouter(c);
                        f.rafraichir();

                    // si la case est vide -> ajoute un carre blanche + ajoute l'image de la piece
                    } else {
                        Carre c = new Carre(Couleur.BLANC, new Point(i * 75, j * 75), 75, true);
                        f.ajouter(c);
                        Texture t = new Texture("images/" + p.getType() + "_" + p.getCouleur() + ".png",
                                new Point(i * 75, j * 75), 75, 75);
                        f.ajouter(t);
                        f.rafraichir();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Souris souris;
        Plateau plateau = new Plateau();

        // creation d'une fenetre de 600*600 
        Fenetre f = new Fenetre("jeu d'Echec ", 600, 670); 
        constructionPlateau(f, plateau);

        // Rectangle pour le texte qu'on affichera au dessus du plateau
        f.ajouter(new Rectangle(new Couleur(248, 249, 249), new Point(0, 600), new Point(600, 670), true));

        // creation d'un texte de presentation en haut du plateau
        Texte texte = new Texte(Couleur.ROUGE, "~ Bienvenue dans le jeux d'echec ~",
                new Font(Font.SANS_SERIF, Font.BOLD, 20), new Point(300, 655));
        f.ajouter(texte);
        f.rafraichir();

        // creation du texte des joueur en haut du plateau
        Texte texteJ = new Texte(Couleur.BLEU, "Joueur 1 à toi de jouer !", new Font(Font.SANS_SERIF, Font.BOLD, 20),
                new Point(300, 620));
        f.ajouter(texteJ);
        f.rafraichir();

        boolean fin = true;
        souris = f.getSouris();
        Position fromPosition = null;
        char joueur = 'B';
        boolean joueurEnEchec = false;

        while (fin) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

            // clique droit pour annuler. On rafrechie le plateau 
            if (souris.getClicDroit() && fromPosition != null) {
                fromPosition = null;
                constructionPlateau(f, plateau);

            // si il y a clique gauche 
            } else if (souris.getClicGauche()) {
                int positionX = souris.getPosition().getX();
                int positionY = souris.getPosition().getY();
                Piece piece = plateau.getCase(positionX / 75, positionY / 75);
                // si le joueur a bien cliquer sur une piece de sa couleur -> on vas recuperer les position disponible
                if (piece != null && fromPosition == null && piece.getCouleur() == joueur) {

                    ArrayList<Position> deplacementsPossibles = piece.getDeplacementPossible(plateau);
                    if (deplacementsPossibles.size() > 0) {
                        fromPosition = new Position(piece.getPosition());
                    }
                    for (int i = 0; i < deplacementsPossibles.size(); i++) {

                        // creation des rond de couleur a la position des depacement posible
                        for (int j = 0; j < 5; j++) {
                            Cercle cercle = new Cercle(Couleur.ROUGE,
                                    new Point(deplacementsPossibles.get(i).getX() * 75 + 37,
                                            deplacementsPossibles.get(i).getY() * 75 + 37),
                                    35 - j);
                            f.ajouter(cercle);
                            f.rafraichir();
                        }
                    }
                } else if (fromPosition != null) {
                    // On teste une première fois le déplacement en faisant une copie du plateau pour vérifier que le roi n'est pas en échec sur ce nouveau plateau
                    Plateau plateauTestDeplacement = new Plateau(plateau);
                    boolean deplacement = plateauTestDeplacement.deplacer(fromPosition,
                            new Position(positionX / 75, positionY / 75));

                    if (deplacement) {
                        boolean echec = plateauTestDeplacement.estEchec(joueur);
                        // si il y a pas d'echec 
                        if (echec == false) {
                            // Pas d'échec pour le joueur, le déplacement est possible, on refait le déplacement maintenant sur le vrai plateau
                            plateau.deplacer(fromPosition, new Position(positionX / 75, positionY / 75));

                            fromPosition = null;
                            constructionPlateau(f, plateau);

                            // creation d'un rectangle blanc pour efacer le texte
                            Rectangle r = new Rectangle(new Couleur(248, 249, 249), new Point(0, 600),
                                    new Point(600, 640),
                                    true);
                            f.ajouter(r);
                            String texteJoueur = "";

                            // changement de joueur 
                            if (joueur == 'B') {
                                joueur = 'N';
                                texteJoueur = "Joueur 2 à toi de jouer !";
                            } else {
                                joueur = 'B';
                                texteJoueur = "Joueur 1 à toi de jouer !";
                            }

                            // verification si un joueur est en echec
                            joueurEnEchec = plateau.estEchec(joueur);
                            if (joueurEnEchec) {
                                texteJoueur = texteJoueur + " " + "Vous êtes en échec !";
                            }

                            // affichage du texte des joueur
                            texteJ = new Texte(Couleur.BLEU, texteJoueur,
                                    new Font(Font.SANS_SERIF, Font.BOLD, 20), new Point(300, 625));
                            f.ajouter(texteJ);
                            f.rafraichir();
                        } 
                        // si le roi est en echec
                        else {
                            // empeche le deplacement au joueur, affiche un message d'alerte
                            String texteEchec = "";
                            if (joueurEnEchec) {
                                texteEchec = "Ce choix laissera votre roi toujours en échec !!!";
                            } else {
                                texteEchec = "Ce choix mettra votre roi en échec !!!";
                            }

                            texteJ = new Texte(Couleur.ROUGE, texteEchec,
                                    new Font(Font.SANS_SERIF, Font.BOLD, 12), new Point(300, 610));
                            f.ajouter(texteJ);
                            f.rafraichir();
                        }
                    }
                }
            }
        }
    }
}
