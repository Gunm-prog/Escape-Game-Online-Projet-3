package com.ocr.emilie;

import java.util.Scanner;

/**
 *
 */
public class Main {


    private static String nomUtilisateur = "Saisir un nom d'utilisateur";

    public static void main(String[] args) {
        System.out.println("Bienvenue");
        System.out.println("Choix du mode de jeu : 1 - Challenger");
        System.out.println("1 - Challenger");
        System.out.println("2 - Défenseur");
        System.out.println("3 - Duel");
        System.out.println("4 - Quitter");
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez un nom d'utilisateur : emilie");
        String str = sc.nextLine();
        System.out.println("Pseudo choisi: " + str);
        System.out.println("Saisissez un mode de jeu: 1 - Challenger ");
        int i = sc.nextInt();
        displaySelectedGamingMode(i);



        // write your code here


    }
    /**
     * Display a selected Gaming Mode
     * @param nbGamingMode The Selected Gaming Mode
     */
    public static void displaySelectedGamingMode(int nbGamingMode) {
        if (nbGamingMode == 1) {
            System.out.println("Vous avez choisi le mode de jeu : Challenger");
        } else if (nbGamingMode == 2) {
            System.out.println("Vous avez choisi le mode de jeu : Défenseur");
        } else if (nbGamingMode == 3) {
            System.out.println("Vous avez choisi le mode de jeu: Duel");
        } else {
            System.out.println("Vous n'avez pas choisi de menu parmi les choix proposés");
        }
    }


}