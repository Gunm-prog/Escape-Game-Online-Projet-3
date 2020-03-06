package com.ocr.emilie.player;

import java.util.Scanner;

public class HumanRole extends Player implements PlayerType{
    private Scanner sc = new Scanner(System.in);

    /**
     * constructeur de l'humanRole
     */
    public HumanRole(){
        super();
        this.type="Human";

    }

    /**
     * permet de récupérer l'entrée d'une proposition du joueur et de la stocker dans l'attribut proposition
     * la méthode est soumise à des test unitaire, et redemandera la requète à l'utilisateur dans les test ne seront validés.
     */
    public void setProposition(){
        String newProposition;
        boolean doLeave;
        do{
            //System.out.println("nom du joueur = "+this.name);
            System.out.println( "Veuillez saisir une proposition de "+this.getKeyLength()+" chiffres : " );
            newProposition = sc.nextLine();
            // L'opérateur ! n'est utilisable que sur des variables de type boolean ou sur des expressions produisant un type boolean(comparaison)
            // l'opérateur ! a une action de négation
            // tant que ! n'est pas true, etc.
            //           System.out.println("dans setProposition, keyLength = "+ this.getKeyLength());
            doLeave = isIntValid( newProposition ) && isIntEqualsKeyLength(newProposition, this.getKeyLength());
        }while(!doLeave);
        //si la proposition passe les tests, alors on la retient;
        this.proposition = newProposition;
    }

    /**
     * permet de récupérer l'entrée de la clée secrète de l'utilisateur et de la stocker dans l'attribut secretKey
     * la méthode soumet des tests unitaire et redemandera la requête tant que les tests ne seront pas validés.
     */
    public void setSecretKey(){
        String inputSecretKey;
        boolean doLeave = false;
        do {
            System.out.println( "Saisissez une secretKey de "+this.getKeyLength()+" chiffres : " );
            inputSecretKey=sc.nextLine();
            doLeave = isIntValid(inputSecretKey) && isIntEqualsKeyLength(inputSecretKey,this.getKeyLength());
        }while(!doLeave);
        this.secretKey = inputSecretKey;
    }

    /**
     * permet de récupérer l'entrée de l'indice de l'utilisateur
     * pour lui faciliter la comparaison on lui transmet la proposition de l'adversaire et sa propre clée secrete.
     * la méthode est soumise à des tests unitaires avec redemande si tests non validée.
     * @param proposition, String, la proposition de l'adversaire.
     */
    public void setClue(String proposition) {
        String inputClue;
        do {
            System.out.println( "Print the clue:" );
            inputClue=sc.nextLine();
        }while(!isInputClueValid(inputClue, this.secretKey, proposition));
        this.clue =  inputClue;
    }

    /**
     * récupère et retient le nom de l'utilisateur dans l'attribut name
     */
    public void setName() {
        String inputName;
        System.out.println("Saisissez un pseudo: ");
        inputName=sc.nextLine();
        this.setName(inputName);

    }

    /**
     * stock le parmetre dans l'attribut name
     * @param name, String
     */
    public void setName(String name){
        this.name=name;
    }


}
