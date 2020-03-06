package com.ocr.emilie;


import com.ocr.emilie.mode.*;
import org.apache.log4j.Logger;


public class Game extends GameParametre{

    private static final Logger logger = Logger.getLogger(Game.class);

    /**
     * constructeur par defaut de la class Game
     * celui-ci va instancier les différents role humain et IA nécessaire à son fonctionnement.
     */
    public Game() {
            System.out.println( "Welcome" ); //Affiche "Bienvenue" à l'écran
            this.setHumanRole(); // instancie le joueur humain
            humanRole.setName(); //demande et retenu du nom de l'utilisateur.
            this.setComputerRole(); // instancie le joueur IA
        }

    /**
     * méthode initiant et géréant le caractère cyclique d'une partie.
     * @throws SaisieErroneeException, l'exception est nécessaire pour la demande de restart en fin de partie
     */
    public void launchGame() throws SaisieErroneeException {
            System.out.println("Emilie presents the : " + this.printChosenMode() + " mode!");
            this.initializeMode(); // initialise le mode de jeu choisie.
            do {
                setCurrentRound( 1 ); // ajoute un tour au nombre d'essais
                System.out.println("Round: " + getCurrentRound() + "/" + getMaxRound());
                this.gamingMode.launchMode(this.currentRound, this.devMode, this.humanRole, this.computerRole );
                // lance le mode de jeu, avec transmission de l'etat du mode dev.

            } while (!endGame( this.computerRole, this.humanRole ) && (this.currentRound < this.maxRound));
            // le jeu continue tant que le nombre d'essai n'est pas épuisé, et qu'il n'y a pas de victoire.

            System.out.println("The game is over." );
            logger.info("Game is over");
            this.restartGame();// demande de restart.
        }

    /**
     * affiche et prend en compte le choix de l'utilisateur dans le menu
     */
    public void launchMenu() {
            boolean doLeave=true;
            do {
                //affichage du menu
                logger.info("Show menu");
                System.out.println( "Choix du mode de jeu: 1 - Challenger" ); // Affiche "Choix du mode de jeu:"
                System.out.println( "1 - Challenger" ); // Affiche: 1-Challenger
                System.out.println( "2 - Défenseur" ); // Affiche: 2- Défenseur
                System.out.println( "3 - Duel" ); // Affiche 3- Duel
                System.out.println( "4 - Quitter" ); // Affiche 4 - Quitter
                System.out.println( "Saisissez un mode de jeu" ); // Affiche "Saisissez un mode de jeu"

                // retenu du choix de l'utilisateur
                String numberGamingMode = sc.nextLine();

                // si l'entrée utilisateur est bonne, on retient le choix dans les parametre du jeu
                if(isIntValid(numberGamingMode) && isIntValidInInterval(numberGamingMode,1,4))
                {
                    setChosenMode(Integer.parseInt( numberGamingMode ));
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
                            logger.info("System exit");
                            System.exit(1);
                            break;
                    }
                    // tout est bon, on permet la sortie du menu.
                    doLeave=false;
                }
            }while(doLeave);
        }

    /**
     * appelée en fin de partie
     * demande si l'utilisateur veux rejouer le mode qui vient de se finir.
     * dans le cas contraire, il sera rediriger vers le menu et pourra lancer un autre mode ou quitter l'application
     * @throws SaisieErroneeException
     */
    public void restartGame() throws SaisieErroneeException {
            // methode de restart, ou non du jeu.
            // affichera le menu si la réponse est négative et permettra le lancement du jeu en fonction du nouveau choix.

            boolean test = true;
            String playAgain="";
            do {
                System.out.println( "Voulez-vous rejouer ce mode de jeu? : " + " oui/yes ou no/non" );
                playAgain=sc.nextLine();
                //test = controlStringValue("oui",playAgain ) || controlStringValue("non", playAgain);
                test=controlYesNo(playAgain);
                // System.out.println("test restart =" + test);
            }while(!test);

            if(playAgain.equals("oui") || playAgain.equals("yes")) {
                logger.info("restart Game : yes ");
                System.out.println("Nouvelle partie: ");
                this.launchGame();
            }
            else if(playAgain.equals("non") || playAgain.equals("no")) {
                logger.info("restart Game : no");
                this.launchMenu();
                this.launchGame();
            }
        }
    }







