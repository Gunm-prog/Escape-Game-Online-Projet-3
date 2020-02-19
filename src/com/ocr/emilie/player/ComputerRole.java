package com.ocr.emilie.player;

import javax.management.ConstructorParameters;
import java.util.*;

public class ComputerRole extends Player implements PlayerType, ComputerMethods {

    protected String tentative;

    /*
        remise à zero de proposition
        (puisqu'elle contient (sauf au premier round), proposition du round précédent)
    */
    public ComputerRole()//constructeur;
    {
        super();
        this.type="Computer";
        this.setName();
    }
    @Override
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
    @Override
    public void setSecretKey() {
        String newSecretKey="";
        for (int i=0; i < this.getKeyLength(); i++) {//génération clé secrete aléatoire  de l'ordinteur
            newSecretKey = newSecretKey + getRandomNumberInRange( 0, 9 );
        }
        this.secretKey = newSecretKey;
    }
    @Override
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

    @Override
    public void setName(){
        this.name= "AI";
    }

    public void setTentative(){
        String newTentative="";
        for (int i=0; i < this.getKeyLength(); i++) {
            newTentative = newTentative + "09"; //tentative = 09090909
        }
        this.tentative = newTentative;
    }
    @Override
    public String getTentative(){
        return this.tentative;
    }

    @Override
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

    @Override
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

    @Override
    public void printPlayer(){
        super.printPlayer();
        System.out.println("tentative : "+tentative);
    }

    @Override
    public void printAllParametre(){
        super.printAllParametre();
        this.printPlayer();
    }
}