package com.ocr.emilie;


import com.ocr.emilie.mode.*;

    public class Game extends GameParametre{

        //constructeur par défaut
        public Game() {
            System.out.println( "Bienvenue" ); //Affiche "Bienvenue" à l'écran
            this.setHumanRole();
            humanRole.setName(); //demande et retenu du nom de l'utilisateur.
            this.setComputerRole();
        }

        //constructeur avec paramètres
    /*
    public Game (Mode mode){
        this.gamingMode = mode;
    }
*/
        public void printChosenMode(){
            switch (chosenMode){
                case(1):
                    System.out.print("Challenger");
                    break;
                case(2):
                    System.out.print("Defenser");
                    break;
                case(3):
                    System.out.print("Duel");
                    break;
                case(4):
                    System.out.print("Quitter");
            }
        }
        public void launchGame() throws SaisieErroneeException {
            System.out.print("Emilie presents the : " );
            this.printChosenMode();
            System.out.println( " mode!");
            this.initializeMode();
            setMaxRound( 6 );
            do {
                setCurrentRound( 1 );
                System.out.println("Round: " + getCurrentRound() + "/" + getMaxRound());
                this.gamingMode.launchMode( this.humanRole, this.computerRole ); // lance le mode de jeu

            } while (!endGame( this.computerRole, this.humanRole ) && (this.currentRound < this.maxRound));
            System.out.println("The game is over." );
            this.restartGame();
        }

        public void launchMenu() {
            boolean doLeave=true;
            do {
                System.out.println( "Choix du mode de jeu: 1 - Challenger" ); // Affiche "Choix du mode de jeu:"
                System.out.println( "1 - Challenger" ); // Affiche: 1-Challenger
                System.out.println( "2 - Défenseur" ); // Affiche: 2- Défenseur
                System.out.println( "3 - Duel" ); // Affiche 3- Duel
                System.out.println( "4 - Quitter" ); // Affiche 4 - Quitter
                System.out.println( "Saisissez un mode de jeu" ); // Affiche "Saisissez un mode de jeu"

                String numberGamingMode = sc.nextLine();

                if(isIntValid(numberGamingMode) && isIntValidInInterval(numberGamingMode,1,4))
                {
                    this.chosenMode = Integer.parseInt( numberGamingMode );
                    switch (numberGamingMode) {
                        case ("1"):
                            this.setGamingMode( new Challenger() );
                            break;
                        case ("2"):
                            this.setGamingMode( new Defenser() );
                            break;
                        case ("3"):
                            this.setGamingMode( new Duel() );
                            break;
                        case ("4"):
                            System.exit(1);
                            break;
                    }
                    doLeave=false;
                }
            }while(doLeave);

            // this.setKeyLength(); // demande et retient keyLength
        }

        public void restartGame() throws SaisieErroneeException {
            boolean test = true;
            String playAgain="";
            do {
                System.out.println( "Voulez-vous rejouer ce mode de jeu? : " + " Oui / Non" );
                playAgain=sc.nextLine();
                //test = controlStringValue("oui",playAgain ) || controlStringValue("non", playAgain);
                test=controlYesNo(playAgain);
                // System.out.println("test restart =" + test);
            }while(!test);

            if(playAgain.equals("oui")) {
                System.out.println("Nouvelle partie: ");
                this.launchGame();
            }
            else if(playAgain.equals("non")) {
                this.launchMenu();
                this.launchGame();
            }
        }
    }







