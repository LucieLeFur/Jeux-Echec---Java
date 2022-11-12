/****************************************************/
/* Titre : Main.java                                */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

// classe permetant de tester les autre classe 
public class Main {
public static void main(String[] args) {

    Plateau plateau = new Plateau();
    System.out.println(plateau);
    System.out.println(plateau.getPiecesBlanches());
}
}

