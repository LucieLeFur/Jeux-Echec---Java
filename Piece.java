/****************************************************/
/* Titre : Piece.java                               */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

import java.util.ArrayList;

public abstract class Piece {
    
    // prend en atribut une couleur et une postion
    private char couleur;
    private Position position;

    // par defaut la piece sera blache et positionner en A1
    public Piece (){
        this.couleur ='B';
        this.position = new Position("A1");
    }

    public abstract String getType();
    public abstract ArrayList<Position> getDeplacementPossible(Plateau p);

     // contructeur prenent une couleur (char) et une position
    public Piece (char couleur, Position position){

        // verifie que la couleur est la bonne
        if (couleur == 'B' || couleur == 'N'){
            this.couleur = couleur;
        }
        // quitte le programme si la couleur est pas bonne
        else{
            System.out.println(" La couleur est pas bonne");
            System.exit(-1);
        }


        this.position = new Position(position);
    }

     // contructeur prenent une couleur (char) et deux entiers
    public Piece (char couleur, int x, int y){

        // verifie que la couleur est la bonne
        if (couleur == 'B' || couleur == 'N'){
            this.couleur = couleur;
        }
        // quitte le programme si la couleur est pas bonne
        else{
            System.out.println(" La couleur est pas bonne");
            System.exit(-1);
        }

        this.position = new Position(x,y);
    }

    // contructeur prenent une couleur (char) et une chaine de caractère
    public Piece (char couleur, String xy){

        // verifie que la couleur est la bonne
        if (couleur == 'B' || couleur == 'N'){
            this.couleur = couleur;
        }
        // quitte le programme si la couleur est pas bonne
        else{
            System.out.println(" La couleur est pas bonne");
            System.exit(-1);
        }


        this.position = new Position(xy);
    }

    // getteur et setteur
    public char getCouleur(){
        return this.couleur;
    }

    public Position getPosition(){
        return this.position;
    }
    public void setCouleur(char c){
        this.couleur = c;
    }

    public void setPosition(Position p){
        this.position = new Position(p);
    }

    // contructeur par copie
    public Piece (Piece p){

        this.couleur = p.getCouleur();
        this.position = p.getPosition();
    }

    // methode qui permet de cree le nom de la piece en racourcie
    public String getNomCourt(){
        
        // recupere les deux première caractère du type de la piece
        char l1 = this.getType().charAt(0);
        char l2 = this.getType().charAt(1);
        
        // construit de la chaine de caractère
        return Character.toString( Character.toUpperCase(l1)) + Character.toString(Character.toLowerCase(l2)) + Character.toString(couleur);
    }
    
    // methode qui permet de cree le nom de la piece de facon longue
    public String getNomLong(){

        return this.getType().toLowerCase() + "_" + Character.toString(couleur);
    }

    // methode equals
    @Override
    public boolean equals(Object p){ 

        if (p instanceof Piece == false) return false;
        Piece p2 = (Piece)p;
        return 
        this.getCouleur() == p2.getCouleur() 
        && this.getType() == p2.getType()
        && this.getPosition() == p2.getPosition();
    }

    // affiche la piece
    public String toString(){
        String c;
        if (this.couleur == 'N')
            c = "noir";
        else 
            c = "blanc";
        return this.getType() +" " +c +" "+ "en" + " " + this.position;
    }

}
