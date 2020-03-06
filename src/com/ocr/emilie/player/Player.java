package com.ocr.emilie.player;

import com.ocr.emilie.GameParametre;

public  abstract class Player extends GameParametre{
    protected String name; // nom du joueur
    protected String type; // type humain ou ia
    protected String proposition; // proposition du joueur
    protected String secretKey; // secretkey du joueur
    protected String clue; // indice du joueur
    protected boolean isItVictory; //etat de victoire du joueur

    /**
     *  retourne le nom du joueur
     * @return name, String
     */
    public String getName(){
        return this.name;
    } //getter du name

    /**
     *  retourne la proposition du joueur pour trouver la clée secrète de l'adversaire.
     * @return proposition, String
     */
    public String getProposition(){
        return this.proposition;
    } //getter de la proposition

    /**
     *  retourne la clée secrete du joueur.
     * @return secretKey, String
     */
    public String getSecretKey(){
        return this.secretKey;
    } // getter de la clée secrete

    /**
     * retourne l'indice que doit donner le joueur à l'adversaire.
     * @return clue, String
     */
    public String getClue(){
        return this.clue;
    } // getter de l'indice

    /**
     * retourne l'état de victoire du joueur
     * @return isItVictory, boolean
     */
    public boolean getIsItVictory(){
        return this.isItVictory;
    } // getter etat victoire

    /**
     *  Mise à jour de l'etat de victoire du joueur
     * @param newIsItVictory , boolean contenant la nouvelle valeur
     */
    public void setIsItVictory(boolean newIsItVictory){ // setter de l'etat de victoire
        this.isItVictory=newIsItVictory;
    }

    /**
     * la méthode printPlayer affiche l'ensemble des information concernant le joueur dans la console
     * @author Emilie
     * @deprecate depuis la mise en place du logging, plus vraiment utile. a voir pour la convertir si nécessaire en logging.
    */
    public void printPlayer(){ // methode d'affichage des information du joueur, pour dubbug?
        System.out.println();
        System.out.println("************/_ Player _/************");
        System.out.println("type : "+type);
        System.out.println("name : "+name);
        System.out.println("secreKey : "+secretKey);
        System.out.println("proposition : "+proposition);
        System.out.println("clue : "+clue);
        System.out.println("isItVictory : "+isItVictory);
    }

    /**
     *  affiche tout les parametre de jeu dans la console.
     * @author Emilie
     * @deprecated déprécié depuis la mise en place du logging
     */
    public void printAllParametre(){
        super.printAllParametre();
        this.printPlayer();
    }

    /**
     *  permet d'afficher de manière lisible pour l'utilisateur les chaines de caractères,
     *  proposition, clue et secretKey durant la partie.
     *  chaque caractère sera encadré par des | =>  |char|char|char|etc...
     *  la méthode permet aussi de tiret un trait de séparation _____ adapté à la longueur des Strings affichés.
     * @param strType , String,
     */
    public void printRound(String strType){
        String str="";
        String strFinal="";
        switch(strType) {
            case "secretKey":
                str=getSecretKey();
                break;
            case "proposition":
                str=getProposition();
                break;
            case "clue":
                str=getClue();
                break;
            case"__":
                for(int i=0;i<getKeyLength();i++){
                    strFinal=strFinal + "__";
                }
                strFinal=strFinal +"__________________";
                break;
            default:
                strFinal="not a valid String";
        }
        if(strFinal==""){
            for (int i=0; i<this.getKeyLength();i++) {
                strFinal=strFinal + "|" + str.charAt( i );
            }
                strFinal=strFinal + "|                  " +this.getName() +"'s "+ strType;

        }

        System.out.println(strFinal);
    }

    /**
     * permet d'affiché la clée secrète du joueur si le mode Dev est activé
     */
    public void devMode(){
        printRound("secretKey");
    }

}
