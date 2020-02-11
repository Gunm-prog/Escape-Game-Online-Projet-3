package com.ocr.emilie;

import com.ocr.emilie.player.*;


public class Main {

    public static void main(String[] args) throws SaisieErroneeException {
        // write your code here
        Game myGame = new Game();// instanciation par defaut de Game sans aucun mode.


        DefaultProperties myDefaultProperties = new DefaultProperties();
        myDefaultProperties.readProperties(myGame);



        myGame.launchMenu();// ici, launchMenu lancera le menu, qui a terme change le mode de jeu en fonction du choix de l'utiisateur
        myGame.launchGame();

        // myDefaultProperties.writeProperties( myGame );
/*
HumanRole hum = new HumanRole();
ComputerRole comp = new ComputerRole();
     hum.setSecretKey();
     comp.setProposition();
     hum.setClue(comp.getProposition());
     //String inputClue, String secretKey, String proposition
     System.out.println("Test sur variable3 vaut : "+myGame.isInputClueValid(hum.getClue(), hum.getSecretKey(), comp.getProposition()));
     */
        //    System.out.println("Test sur variable1 vaut : "+myGame.isInputCLueValid(variable1,1,4));
        //  System.out.println("Test sur variable1 vaut : "+myGame.isInputCLueValid(variable1,4,1));
        //System.out.println("Test sur variable2 vaut : "+myGame.isInputCLueValid(variable2,1,4));

        //   myGame.printAllParametre();
/*
     HumanRole hum = new HumanRole();
     ComputerRole comp = new ComputerRole();
     Game game = new Game();
     game.setKeyLength();

     game.printAllParametre();
     for(int i=0; i<5;i++){

        comp.setSecretKey();
        System.out.println("secretKey dans instance computer : "+ comp.getSecretKey());
     }
     System.out.println("innfos dans playerComputer");
     comp.printPlayer();
     System.out.println();
     System.out.println("infos dans gameParametre");
     game.printAllParametre();
*/
     /*
     System.out.println();
     System.out.println("DEBUT");
     System.out.println( "Affiche Param avant human.setSecretKey() :" );

     game.printAllParametre();
     hum.setSecretKey();

     System.out.println();
     System.out.println( "human.setSecretKey, EFFECTUE" );

     System.out.println();
     System.out.println( "FIN Affiche Param FIN" );

     System.out.println();
     System.out.println("DEBUT");
     System.out.println( "Affiche Param après human.setSecretKey() :" );

     game.printAllParametre();

     System.out.println();
     System.out.println( "FIN Affiche Param FIN" );
*/
        //    hum.setProposition();
        //    comp.setClue(hum.getProposition());

//     game.printAllParametre();
    }
}
