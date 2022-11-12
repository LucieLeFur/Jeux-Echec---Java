/****************************************************/
/* Titre : Pion.java                                */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;


// La classe Pion est une sous classe de la classe Piece
abstract class Pion extends Piece {

    public Pion(){
        super();
    }

    public Pion(Position position, char couleur){
        super(couleur , position);
    }
    
    // retourne le type : " pion "
    @Override
    public String getType() {
        return "pion";
    }    
    @Override
    public ArrayList<Position> getDeplacementPossible(Plateau p) {
        return null;
    }
}
