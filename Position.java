/****************************************************/
/* Titre : Position.java                            */
/* Auteur : LE FUR Lucie                            */
/* Projet : Jeu d'echec (TP6)                       */
/* Matiere : R2.01 Développement orienté objet      */
/* TP/TD : TPA/TD1                                  */
/****************************************************/

class Position {

    private int x;
    private int y;

    public Position() {

        // par defaut x et y seront a 0
        this.x = 0;
        this.y = 0;

    }

    public Position(int x, int y) {

        // x et y doivent etre entre 7 et 0
        if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
            this.x = x;
            this.y = y;
        // sinon quitte le programme
        } else {
            System.out.println("Vos coordonnés ne sont pas bonnes");
            System.exit(-1);
        }

    }

    // constructeur par copie
    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    // constructeur qui prend une chaine de caractère
    public Position(String position) {

        String caracteresEchiquier = "ABCDEFGH";
        // recupere la premiere valeur de caractère pour x et la deuxième pour y
        char x1 = position.toUpperCase().charAt(0);
        char y1 = position.charAt(1);

        this.x = caracteresEchiquier.indexOf(x1);
        this.y = Character.getNumericValue(y1) - 1;
    }

    // guetteur et setteur
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object p) {

        if (p instanceof Position == false)
            return false;
        Position p2 = (Position) p;
        return this.x == p2.x && this.y == p2.y;
    }

    // affiche la position
    public String toString() {
        String cchh = "ABCDEFGH";
        return new String(cchh.charAt(this.x) + Integer.toString(this.y + 1));
    }


}