package com.ocr.emilie.game;

import java.util.Scanner;
import com.ocr.emilie.mode.Mode;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;
import org.apache.log4j.Logger;

public abstract class GameParametre extends GameControllerException {
        /*
            stockera toutes les infos et methodes communes a chaque mode de jeu
         */
        Scanner sc=new Scanner( System.in );
        protected HumanRole humanRole; //stockage par agrégation d'une instance de joueur Humain
        protected ComputerRole computerRole;    // stockage par agrégation d'une instance de joueur IA
        protected static Mode gamingMode;   // stockage par agrégation via interface d'une instance de mode de Jeu
        /*
            l'agrégation signifie que la classe qui agrège des éléments, ne peut pas exister sans la création des éléments agrégé.
            exemple: notre jeu, ne peut pas exister sans joueurs et mode de jeu.
            L'"agrégation" se différencie de la "composition", par son coté obligatoire.
            En effet une classe stockant des éléments par composition, pourra être instancié sans ces éléments qui ne seront pas obligatoire à son fonctionnement.
         */
        protected static int chosenMode;    // stockage de l'identifiant numérique du mode de jeu, afin de pouvoir le charger et garder une trace.
        protected static int keyLength; // stockage de la taille de la clef secrete
        protected static int maxRound;  // stockage du nombre de tours/ essais maximum
        protected static int currentRound;  //stockage du numéro de tour actuel
        protected static boolean devMode;   // stockage de l'état du mode dev, true/false.

        /* Pourquoi STATIC?
            Les attributs sont en static parce que nous instancions plusieurs fois la class GameParameter :
                Une fois dans l'instance de Game (par héritage)
                et dans chaque instance de class Player (par héritage)
            Il est donc nécessaire que les multiples instance accèdent aux paramètres du jeu, Et que ceux-ci soit les même pour Toutes les instances.
            D'où l'usage du mot clé static, pour retenir les paramètres en tant qu' attribut de CLASS, plutot qu'en attribut d'objet. (instance de class)

            exemple:
                si les attributs de GameParameter n'étaient pas en static.
                Lorsque je créer mon instance de Game, et donc de GameParameter par héritage. (Game < GameParamter < GameController)
                et que je dit à Game que ma KeyLength vaut 8;
                Lorsque que je vais créer un Player, et donc instancier GameParameter par héritage (Player < GameParameter)
                cette instance de GameParameter n'aura pas 8 mais 0 dans son KeyLength.

                Grace au mot clé static et la création d'attribut de classe. L'attribut aura la même valeur, quelque soit ces instance.
                Sans le mot clé static, les attributs sont des attributs d'objet. (liés à leurs instances respectives uniquement)

                Class Voiture {
                    String couleur;         // je ne met pas le mot clé static
                        on imagine les getter et setter pour l'attribut couleur
                }

                voiture1 = new Voiture();
                voiture2 = new Voiture();
                voiture1.setCouleur("jaune");
                voiture2.setCouleur("rouge");

                donc ici pour chaque instance de Voiture, nous avons une couleur différentes dans leur attribut d'OBJET (ou d'instance)

                Si le mot clé static avait été utilisé pour l'attribut couleur.
                nous n'aurions pas une voiture jaune et une voiture rouge. Mais uniquement DEUX voiture rouge !!
                parce que leur attribut couleur, mit en Static, serait partagé pour les deux.
         */

        private static final Logger logger = Logger.getLogger(GameControllerException.class);

        /**
         * setKeyLength va demander à l'utilisateur et stocker, une taille de clé secrète à utiliser dans le jeu.
         * @deprecated depuis que la taille de clé secrète est soumise par l'usage du fichier defaut properties.
         */
        public void setKeyLength() {
            // la méthode setKeyLength(), sans paramettre , va permettre de donnée une valeur à la taille de la clef via une requête d'entrée utilisateur.
            // elle effectuera des tests unitaires pour valider ou non l'entrée utilisateur
            boolean doLeave;
            do {
                System.out.println( "Veuillez entrer une taille de clé de 1 à 4" );// affiche la demande de taille clée
                String inputKeyLength=sc.nextLine();
                doLeave=isIntValid( inputKeyLength ) && isIntValidInInterval( inputKeyLength, 1, 4 );
                // on test si l'entrée est bien un chiffre et si il est bien dans l'intervalle prévue.
                if (doLeave) {
                    // si les test sont bons, on retient l'entrée dans l'attribut keyLength
                    // avec convertion du String en int
                    keyLength=Integer.parseInt( inputKeyLength );
                    logger.info("Parametre Key length = " + this.keyLength);
                }
            } while (!doLeave);
        }

        /**
         * surcharge de la méthode précédente pour l'adapter à l'usage d'un fichier de configuration par defaut.
         * passera la valeur en parametre à l'attribut keyLength
         * @param keyLength, int, correspond à la valeur que prendra l'attribut keyLength
         */
        public void setKeyLength(String keyLength) {
            // Surcharge de la methode setKeyLength, avec en parametre la valeur à utiliser en taille clef.
            // cette méthode nous permet d'utilisé des parametres par defaut stockés dans le fichier defaut.properties.
            this.keyLength=Integer.valueOf( keyLength ); // convertion du String en Int et retenu dans l'attribut keyLength.
            logger.info("Parametre Key length = " + this.keyLength);
        }

        /**
         * retourne la valeur de la taille de clée utilisé pour la partie
         * @return keyLength , int.
         */
        public int getKeyLength() {
            // le getter de keyLength, nous permet d'utiliser au besoin la valeur du keyLength utilisé pour la partie.
            return this.keyLength;
        }

        /**
         * parce que les mode de jeu sont d'abord identifié par un numéro,
         * une methode à été faite pour pouvoir retourner leur nom respectif pour l'utilisateur
         * @return String, le nom du mode qui à été selectionné par l'utilisateur
         */
        public String printChosenMode(){
            // la méthode printChosenMode(), sans parametre, va nous permettre d'afficher le nom du mode de jeu choisi par l'utilisateur.
            // elle ne fait pas de SystemOut, mais retourne une chaine de caractère, que l'on pourra alors placer dans un SystemOut
            String str="";
            switch (chosenMode){
                case(1):
                    str = "Challenger";
                    break;
                case(2):
                    str="Defenser";
                    break;
                case(3):
                    str="Duel";
                    break;
                case(4):
                    str ="Quitter";
            }
            return str;
        }

        /**
         * retient le nombre d'essai maximum passé en parametre dans l'attribut maxRound
         * @param round, int nombre d'essai maximum
         */
        public void setMaxRound(int round) {
            // retient la valeur d'essai maximum passé en paramettre.
            // test unitaire, on vérifie si le parametre et bien une valeur numérique
            this.isIntValid( String.valueOf( round ) );
            this.maxRound=round;
            logger.info("Parametre Max round: " + this.maxRound);
        }

        /**
         * retourne la valeur du maxRound dans les parametre du jeu
         * @return maxRound, int
         */
        public int getMaxRound() {
            // retourne la valeur d'essai maximum contenu dans maxRound.
            return this.maxRound;
        }

        /**
         * increment la valeur de l'attribut currentRound, qui correspond au numéro de d'essai actuel, par la valeur passé en parametre.
         * @param newRound, int la valeur d'incrementation. (nous utilisons 1 dans notre cas)
         */
        public void setCurrentRound(int newRound) {
            // retient et modifie la valeur du numéro de tour actuel avec test unitaire.
            this.isIntValid( String.valueOf( newRound ) );
            this.currentRound+=newRound;//ce qui revient à faire currentRound= currentRound + newRound
            logger.info("Parametre Current round: " + this.currentRound);
        }

        /**
         * retourne la valeur contenu dans curentRound des parametre du jeu
         * @return currentRound, int
         */
        public int getCurrentRound() {
            // retourne la valeur du numéro de tour actuel.
            return this.currentRound;
        }

        /**
         * stock par agrégation une instance de Mode via interface en fonction du mode de jeu selectionné
         * @param mode , instance du mode de jeu selectioné (Challenger, Defenser, ou Duel)
         */
        public void setGamingMode(Mode mode) {
            this.gamingMode=mode;
            logger.info("Parameter Gaming mode: successfully loaded");
        }

        /**
         * stock par agrégation une instance de joueur Humain représentant le joueur Humain dans l'attribut humanRole
         */
        public void setHumanRole() {
            // retient et modifie, une instance de joueurHumain, dans le humanRole.
            this.humanRole=new HumanRole();
            logger.info("Human role: successfully loaded");
        }

        /**
         * retourne l'instance de HumanRole représentant le joueur humain stocké dans l'attribut HumanRole
         * @return instance de HumanRole
         */
        public HumanRole getHumanRole() {
            // retourne l'instance (l'adresse de la variable en vérité)
            return this.humanRole;
        }

        /**
         * stock et modifie par agrégation une instance de joueur IA représentant le joueur humain dans l'attribut computerRole
         */
        public void setComputerRole() {
            // retient et modifie, une instance de joueurIA, dans le computerRole.
            this.computerRole=new ComputerRole();
            logger.info("Computer role: successfully loaded");
        }

        /**
         * stock et modifie l'identifiant numérique du mode choisi par l'utilisateur.
         * @param userChosenMode
         */
        public void setChosenMode( int userChosenMode){
            // retient et modifie, la valeur de chosenMode passée en parametre dans chosenMode
            chosenMode= userChosenMode;
            logger.info("Parameter gamingMode : " + this.printChosenMode() +" loaded.");


        }

        /**
         * cette méthode en fonction du mode de jeu choisi, va initialiser les variables nécessaires à leur fonctionnement
         * et remettre à zéro les condition de fin de partie
         */
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

            //remise à zero les condtions de fin de partie.
            currentRound=0;
            computerRole.setIsItVictory( false );
            humanRole.setIsItVictory( false );
            logger.info("gamingMode successfully Initialized ");

        }

        /**
         * méthode permettant d'afficher dans la console l'ensemble des parametres de eju, pour debugage
         * @deprecated depuis la mise en place du logging, potentiellement modifiable pour usage en logging
         */
        public void printAllParametre() {
            System.out.println( "" );
            System.out.println( "************/_ Parametres du jeu _/************" );
            System.out.println( "chosenMode : " + chosenMode );
            System.out.println( "keyLength : " + keyLength );
            System.out.println( "MaxRound : " + maxRound );
            System.out.println( "currentRound : " + currentRound );
            this.humanRole.printPlayer();
            this.computerRole.printPlayer();
            logger.info("All parameters printed");
        }

        /**
         * change l'état d'activation du mode dev
         * @param etat, boolean
         */
        public void setDevMode(boolean etat){
            // méthode qui change l'etat d'activation du mode dev.
            this.devMode=etat;
            logger.info("The Developper mode is passed to " + this.devMode);//permet d'avoir le mode on et off
        }

    }


