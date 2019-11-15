package com.ocr.emilie;

/**
 *
 */
public class Main {


    private static String nomUtilisateur = "Saisir un nom d'utilisateur";

    public static void main(String[] args) {
        System.out.println("Bienvenue");
        System.out.println("Saisir un nom d'utilisateur");
        System.out.println("Choix du mode de jeu");
        System.out.println("1 - Challenger");
        System.out.println("2 - Défenseur");
        System.out.println("3 - Duel");
        System.out.println("4 - Quitter");
        System.out.println("Veuillez choisir votre mode de jeu");
        // write your code here
    }

    public static void setNomUtilisateur(String nomUtilisateur) {
        Main.nomUtilisateur = "Saisir un nom d'utilisateur";
    }

    /**
     * Display a selected Gaming Mode
     * @param nbGamingMode The Selected Gaming Mode
     */
    public void displaySelectedGamingMode (int nbGamingMode){
        System.out.println("Vous avez choisi le mode de jeu" + nbGamingMode);
    }
}
