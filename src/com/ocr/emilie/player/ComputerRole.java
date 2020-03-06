package com.ocr.emilie.player;

import javax.management.ConstructorParameters;
import java.util.*;

public class ComputerRole extends Player implements PlayerType, ComputerMethods {

    protected String tentative;

    /*
        remise à zero de proposition
        (puisqu'elle contient (sauf au premier round), proposition du round précédent)
    */

    /**
     * constructeur du joueur IA
     * on en profite pour lui donner un nom
     */
    public ComputerRole()//constructeur;
    {
        super();
        this.type="Computer";
        this.setName();
    }

    /**
     * permet de générer et stocker une proposition pour le joueur ordinateur dans l'attribut proposition.
     */
    public void setProposition() {
        String newProposition="";
        //System.out.print(" tentative = "+tentative+" ");
        for (int i=0; i < this.getKeyLength(); i++) {
            /*
                bloc for qui va générer la proposition de l'ordinateur
                on utilise les [min-max] stockes dans tentative
                min et max iront dans la méthode random
            */
            //recup et convertion des char en int dans la chaine tentative (09090909)
            int min =charToInt(tentative.charAt( i * 2 ));
            int max =charToInt(tentative.charAt( i * 2 +1));
                /*
                    la valeur de String tentative etant sous la forme 09090909
                    pour minmaxminmaxminmaxminmax
                    on récupère les valeurs par charAt en déplaçant les'index de 2 en 2
                */
            int r=getRandomNumberInRange( Integer.valueOf( min ), Integer.valueOf( max ) );

            newProposition= newProposition + r;
            //  concaténation dans le String proposition à chaque tour de boucle
        }
        this.proposition = newProposition;
    }

    /**
     * permet de générer et stocker la clée secrete du joueur ordinateur dans l'attribut secretKey
     */
    public void setSecretKey() {
        String newSecretKey="";
        for (int i=0; i < this.getKeyLength(); i++) {//génération clé secrete aléatoire  de l'ordinteur
            newSecretKey = newSecretKey + getRandomNumberInRange( 0, 9 );
        }
        this.secretKey = newSecretKey;
    }

    /**
     * permet de générer et stocker pour le joueur ordinateur l'indice, dans l'attribut clue
     * pour se faire on lui transmet la proposition de l'adversaire
     * la méthode consiste à comparer chaque caractère de la proposition adverse et de la secretKey du joueur
     * et d'indiquer si la valeur à trouver est +,- ou = à la proposition
     * @param proposition , String proposition de l'adversaire.
     */
    public void setClue(String proposition){
        String newClue="";//génération indice
        for (int j=0; j < this.getKeyLength(); j++) {
            if ((int) this.secretKey.charAt( j ) > (int) proposition.charAt( j )) {
                newClue +="+";
            } else if ((int) secretKey.charAt( j ) < (int) proposition.charAt( j )) {
                newClue +="-";
            } else {
                newClue +="=";
            }
        }
        this.clue = newClue;
    }

    /**
     * génère un nom pour le joueur ordinateur.
     */
    public void setName(){
        this.name= "AI";
    }

    /**
     * initialise la chaine de recherche "tentative" pour le départ d'uen partie.
     * la chaine aura alors des intervale maximum de min:0 à max:9
     * elle aura la longueur de la taille clé et sera sous la forme 09090909...
     */
    public void setTentative(){
        String newTentative="";
        for (int i=0; i < this.getKeyLength(); i++) {
            newTentative = newTentative + "09"; //tentative = 09090909
        }
        this.tentative = newTentative;
    }

    /**
     *
     * @return tentantive, String, la chaine de recherche de l'ordinateur
     */
    public String getTentative(){
        return this.tentative;
    }

    /**
     * Mise à jour de la chaine tentative de l'ordinateur en fonction de l'indice du joueur adverse.
     * par comparaison des caractères, resserera les intervales de recherche.
     * exemple 09090909 pourra devenir 49056903.
     * @param humanClue , String l'indice de l'adversaire
     */
    public void updateTentative(String humanClue){
        //Traitement des indices joueur pour modifier la chaîne tentative. j!=0 ne va pas être lu au premier tour de boucle parce qu'il n'y a pas encore d'indices
        String newTentative="";
        for (int i=0; i < this.getKeyLength(); i++) {
            //Génération proposition ordinateur// k++ revient à dire k=k+1
            //modification intervalle recherche de lordi selon indices joueur

            //min et max recup et convertion en int du char indexé i correspondant dans tentative (09090909)
            int intMin =charToInt(this.tentative.charAt( i * 2 ));
            int intMax = charToInt(this.tentative.charAt( i * 2 + 1 ));

            //recup et convertion en int du char indexé i dans proposition
            int intCharPropOrdi= charToInt(this.proposition.charAt(i));

            //recup et convertion en int du char indexé i dans proposition
            char caracReponseJoueur = humanClue.charAt(i);

            switch (caracReponseJoueur) {
                case '+':
                    intCharPropOrdi=intCharPropOrdi + 1;
                    newTentative += intCharPropOrdi;
                    newTentative += intMax ;
                    break;
                case '-':
                    intCharPropOrdi=intCharPropOrdi - 1;
                    newTentative += intMin;
                    newTentative += intCharPropOrdi ;
                    break;
                case '=':
                    newTentative += intCharPropOrdi;
                    newTentative +=  intCharPropOrdi;
                    //on place la même valeur intCaracPropOrdi (car =) en min et max;
                    break;
            }
        }
        this.tentative = newTentative;
        //           System.out.println( "tentative ordi après modif : " + this.tentative );
    }

    /**
     * génération de chiffre aléatoire compris entre un minimum et un maximum
     * @param min, int la valeur minimale
     * @param max, int la valeur maximale
     * @return une valeur aléatoire comprise entre min et max.
     */
    public int getRandomNumberInRange ( int min, int max){
        if (min > max) {
            throw new IllegalArgumentException( "max must be greater than min [" + min + "/" + max + "]" );
        }
        if (min == max) {// gestion des cas ou min = max =>plus d'intervalle
            return max;
        } else {
            Random r=new Random();
            int n=min + r.nextInt( max - min + 1 );
            return n;
        }
    }

    /**
     * permet d'afficher dans la console les informations concercant le joueur
     * @deprecated depuis la mise en place des logs
     */
    public void printPlayer(){
        super.printPlayer();
        System.out.println("tentative : "+tentative);
    }

    /**
     * permet d'afficher tout les parametres de jeu
     * @deprecated depuis la mise en place des logs.
     */
    public void printAllParametre(){
        super.printAllParametre();
        this.printPlayer();
    }
}