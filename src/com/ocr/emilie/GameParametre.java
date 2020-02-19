package com.ocr.emilie;

import java.util.Scanner;
import com.ocr.emilie.mode.Mode;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

import java.util.Scanner;

    public abstract class GameParametre extends GameControllerException {
        /*
            stockera toutes les infos et methodes communes a chaque mode de jeu
         */
        Scanner sc=new Scanner( System.in );
        protected HumanRole humanRole;
        protected ComputerRole computerRole;
        protected static Mode gamingMode;
        protected static int chosenMode;
        protected static int keyLength;
        protected static int maxRound;
        protected static int currentRound;

        public void setKeyLength() {
            boolean doLeave;
            do {
                System.out.println( "Veuillez entrer une taille de clé de 1 à 4" );// affiche la demande de taille clée
                String inputKeyLength=sc.nextLine();
                //         System.out.println("Dans setKeyLength, inputKeyLength = "+ inputKeyLength);
                doLeave=isIntValid( inputKeyLength ) && isIntValidInInterval( inputKeyLength, 1, 4 );
                // on test si l'entrée est bien un chiffre et si il est bien dans l'intervalle prévue.
                //convertion du String en int
                if (doLeave) {
                    keyLength=Integer.parseInt( inputKeyLength );  // si les test sont bons, on retient l'entrée dans l'attribut keyLength
                    //              System.out.println("inputKeyLength après parseInt = "+keyLength);
                }
            } while (!doLeave);
        }

        public void setKeyLength(String keyLength) {
            this.keyLength=Integer.valueOf( keyLength );
        }

        public int getKeyLength() {
            return this.keyLength;
        }

        public void setMaxRound(int round) {
            this.isIntValid( String.valueOf( round ) );
            this.maxRound=round;
        }

        public int getMaxRound() {
            return this.maxRound;
        }

        public void setCurrentRound(int newRound) {
            this.isIntValid( String.valueOf( newRound ) );
            this.currentRound+=newRound;//ce qui revient à faire currentRound= currentRound + newRound
        }

        public int getCurrentRound() {
            return this.currentRound;
        }

        public void reInitialiseMode() {//remet le mode à 0 (currentRound) pour démarrer une nouvelle partie

        }

        //Redéfinir le comportement de gamingMode
        public void setGamingMode(Mode mode) {
            this.gamingMode=mode;
        }

        public void setHumanRole() {
            this.humanRole=new HumanRole();
        }

        public HumanRole getHumanRole() {
            return this.humanRole;
        }

        public void setComputerRole() {
            this.computerRole=new ComputerRole();
        }

        public void initializeMode() {

            switch (chosenMode) {
                case (1):
                    computerRole.setSecretKey();
                    break;
                case (2):
                    humanRole.setSecretKey();
                    computerRole.setTentative();
                    break;
                case (3):
                    computerRole.setSecretKey();
                    humanRole.setSecretKey();
                    computerRole.setTentative();
                    break;
            }
            currentRound=0;
            computerRole.setIsItVictory( false );
            humanRole.setIsItVictory( false );

        }


        public void printAllParametre() {
            System.out.println( "" );
            System.out.println( "************/_ Parametres du jeu _/************" );
            System.out.println( "chosenMode : " + chosenMode );
            System.out.println( "keyLength : " + keyLength );
            System.out.println( "MaxRound : " + maxRound );
            System.out.println( "currentRound : " + currentRound );
            this.humanRole.printPlayer();
            this.computerRole.printPlayer();
        }


    }


