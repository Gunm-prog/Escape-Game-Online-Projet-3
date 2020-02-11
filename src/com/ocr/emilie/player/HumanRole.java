package com.ocr.emilie.player;

import java.util.Scanner;

public class HumanRole extends Player implements PlayerType{
    private Scanner sc = new Scanner(System.in);

    public HumanRole(){
        super();
        this.type="Human";
    }
    @Override
    public void setProposition(){
        String newProposition;
        boolean doLeave;
        do{
            System.out.println("nom du joueur = "+this.name);
            System.out.println( "Veuillez saisir une proposition: " );
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
    @Override
    public void setSecretKey(){
        String inputSecretKey;
        boolean doLeave = false;
        do {
            System.out.println( "Saisissez la secretKey: " );
            inputSecretKey=sc.nextLine();
            doLeave = isIntValid(inputSecretKey) && isIntEqualsKeyLength(inputSecretKey,this.getKeyLength());
        }while(!doLeave);
        this.secretKey = inputSecretKey;
    }
    @Override
    public void setClue(String proposition) {
        String inputClue;
        do {
            System.out.println( "Computer's proposition : " + proposition + " Compare your secret key and Print the clue: " + this.secretKey );
            inputClue=sc.nextLine();
        }while(!isInputClueValid(inputClue, this.secretKey, proposition));
        this.clue =  inputClue;
    }
    @Override
    public void setName() {
        String inputName;
        System.out.println("Saisissez un pseudo: ");
        inputName=sc.nextLine();
        this.name=inputName;

    }

    public void setName(String name){
        this.name=name;
    }


}
