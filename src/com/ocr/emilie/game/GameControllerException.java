/**
 * @author Emilie BALSEN
 */
package com.ocr.emilie.game;

import com.ocr.emilie.Main;
import org.apache.log4j.Logger;

import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

/**
 * La class GameController nous permet de réaliser des tests unitaires sur la cohérence des entrées ou génération de parametres du jeu.
 *  elle représente la class final dans l'héritage Game<-GameParameter<-GameController
 */
public abstract class GameControllerException {
        private static final Logger logger = Logger.getLogger( Main.class);

    /**
     * methode de convertion rapide de char en int, afin de réaliser des comparaison numérique.
     * @param c, char
     * @return int valeur numérique du char.
     */
    public static int charToInt(char c) {
            // methode pour convertir rapidement des char en Int, pour comparasion numérique.
            String strC=Character.toString( c );
            return Integer.parseInt( strC );
        }

    /**
     *  méthode de vérification de la cohérence de l'indice entré par un utilisateur.
     * @param inputClue, String indice donné par l'utilisateur
     * @param secretKey, String secretKey de l'utilisateur
     * @param proposition, String la proposition faite par l'adversaire
     * @throws SaisieErroneeException, levée d'exception si l'entrée n'est pas bonne.
     */
    public static void controlClueValidity(String inputClue, String secretKey, String proposition) throws SaisieErroneeException {
        boolean test=true;
        for (int i=0; i < secretKey.length(); i++) {//méthode vérifiant si l'indice donné par le joueur n'est pas erroné
            int charKey=charToInt( secretKey.charAt( i ) );//extraction et convertion du char à index i
            int charProp=charToInt( proposition.charAt( i ) );//idem
            if (charKey > charProp) {
                // ici j'ai fait un if dans un if au lieu d'utiliser l'opérateur logique &&.
                // je voulais pratiquer différente manière de faire dans mon apprentissage pour bien intégrer la logique.
                if (inputClue.charAt( i ) != '+') {
                    // resultat false si l'indice donné n'est pas "+" dans le cas ou il doit l'être
                    test=false;
                }
            } else if (charKey < charProp && inputClue.charAt( i ) != '-') {
                // resultat false si l'indice donné n'est pas "-" dans le cas ou il doit l'être
                test=false;
            } else if (charKey == charProp && inputClue.charAt( i ) != '=') {
                // resultat false si l'indice donné n'est pas "=" dans le cas ou il doit l'être
                test=false;
            }
        }
        if (test == false) {
            // si le test de vérification est false, on créer l'exception
            logger.error( "Unvalid input clue! : " + inputClue );
            throw new SaisieErroneeException( "clue is not valid: " );
        } else {
            logger.info( "Valid input clue : " + inputClue );
        }

    }


    /**
     * la méthode est générique, elle peut être utilisée avec n'importe quel chaine de caractère et de longueur passé en parametre.
     * controle de la taille d'un string, que ce soit pour la clef secrète, les indices ou les propositions
     * @param str String, la chaine a tester
     * @param length, la longueur attendue
     * @throws SaisieErroneeException, levée d'exception si le test est mauvais.
     */
        public static void controlStringLength(String str, int length) throws SaisieErroneeException {
            if (str.length() != length) {
                // création de l'exception si la taille ne correspond pas.
                throw new SaisieErroneeException( "String length must be equals to " + length );
            }
        }

    /**
     * test  de la cohérence du min max passé en parametre:
     * si je veux vérifier que l'utilisateur entre bien une valeur dans l'intervale proposé.
     * exemple: l'intervale de recherche de l'ordinateur ou le choix du menu
     * @param min , valeur minimale à tester
     * @param max, valeur maximale à tester
     * @throws SaisieErroneeException, leévé d'exception si le test est mauvais.
     */
        public static void controlMinSmallerThanMax(int min, int max) throws SaisieErroneeException {
            if (min > max) {
            // une exception est levée si le test est false.
                throw new SaisieErroneeException( "max: " + max + "must be greater than min:" + min );
            }
        }

    /**
     *  test de la position d'une valeur numérique dans un intervale minimum / maximum
     * @param inputVal int valeur à tester
     * @param min, int valeur minimale de l'intervale
     * @param max, int valeur maximale de l'intervale
     * @throws SaisieErroneeException, levée d'exception si le test n'est pas bon.
     */
        public static void controlInterval(int inputVal, int min, int max) throws SaisieErroneeException {
            if (inputVal < min || inputVal > max) {
               // si la valeur n'est pas dans l'intervalle, min, max, lève une exception
                throw new SaisieErroneeException( "Please, respect number interval [" + min + "/" + max + "]" );
            }
        }

    /**
     * test du caractère strictement numérique d'une chaine de caractère.
     *  la chaine pouvant être de grande longueur, plus de 9 caractère (limite du type int)
     * il faudra dnc traiter la chaine par portion de 9, longueur maximale du format int
     * il faut donc chercher combien de fois la chaine de caractere contient de séquences de 9 chiffres
     * traiter ces portions dans une boucle autant de fois que le resultat du calcul si dessus
     *  donc nombre de traitement = chaine.keylentgh() / par 9
     *  ensuite pour les cas ou la dernière sous-chaine est inférieur à 9
     *  int resteSubstring = inputUser.lenght % modulo * 9
     *
     * @param inputUser String la chaine à traiter
     * @return boolean resultat true ou false du test
     */
    public boolean isIntValid(String inputUser) {
            boolean test=true;
            try {
                if (inputUser.length() > 9) { // si la chaine à traiter est plus grand que 9 caractères.
                    int nbSubstring=inputUser.length() / 9; //stockage du nombre de sous-chaine de 9 caractère à traiter.
                    int resteSubstring=inputUser.length() % 9; // stockage de la taille de la dernière sous-chaine qui peut être inférieur à 9
                    for (int i=0; i < nbSubstring; i++) { // nombre de tour de boucle = nombre de sous-chaine de 9 trouvée.
                        String substr=inputUser.substring( 9 * i, 8 + 9 * i ); // déplacement de sous-chaine en sous-chaine dans la boucle.
                        Integer.valueOf( substr ); // test : tentative de convertion en int de la sous-chaine.
                    }
                    if (resteSubstring != 0) { // même traitement pour la dernière sous-chaine si elle existe.
                        String substr=inputUser.substring( nbSubstring * 9, inputUser.length() );
                        Integer.valueOf( substr ); //test : tentative de convertion eau format int
                    }
                } else { // pour les cas ou la chaine à traiter ne fait pas plus de 9 caractère, traitement en une seule fois.
                    Integer.valueOf( inputUser ); //test : tentative de convertion eau format int
                }

            } catch (NumberFormatException e) { // si l'un des test ne fonctionne pas, le catch lèvera l'exception.
                logger.error("Input number not valid number :" + inputUser);
                System.err.println( "This is not a number: " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

    /**
     * méthode de test combinant les test de cohérence sur les intervale
     * controle: si le min est bien strictement plus petit que le max
     * controle: si la valeur à vérifier est bien une valeur numérique
     * controle: si la valeur donné est bien dans l'intervale donné.
     * @param inputUser, String la valeur à tester
     * @param min , int valeur minimale de l'intervale
     * @param max, int valeur maximale de l'intervale
     * @return boolean le resultat du test true ou false en levant une exception
     */
        public boolean isIntValidInInterval(String inputUser, int min, int max) {
            boolean test=true;
            int input=Integer.parseInt( inputUser ); // conversion du String en Int
            try {
                controlMinSmallerThanMax( min, max );
                controlInterval( input, min, max );
            } catch (SaisieErroneeException e) { // si l'un des test est false, retour d'exception.
                logger.error("False parameter interval : " + e);
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please try again" );
                test=false;
            }
            return test;
        }

    /**
     * vérification de la taille d'une entrée utilisateur confore à la taille attendue
     * @param inputString, Int de isIntEqualsKeyLength peut correspondre à la proposition ou la secretKey ou l'indice
     * @param keyLength int taille de la clé secrete attendue
     * @return boolean resultat du test true ou false en levant une exception
     */
        public boolean isIntEqualsKeyLength(String inputString, int keyLength) {
            boolean test=true;
            try {
                // le test est effectué par la méthode controlStringLength;
                controlStringLength( inputString, keyLength );
            } catch (SaisieErroneeException e) {
                logger.error("False input length : "+ e);
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

    /**
     * test la validitée d'un indice donné par l'utilisateur
     * @param inputClue , String indice donnée par l'utilisateur à tester
     * @param secretKey, String clée secrète de l'utilisateur
     * @param proposition, String proposition faite par l'adversaire
     * @return boolean, resultat du test true ou false en levant une exception
     */
        public boolean isInputClueValid(String inputClue, String secretKey, String proposition) {
            boolean test=true;
            try {
                controlStringLength( inputClue, secretKey.length() );// controle la longueur de la chaine
                // le test est effectué par la méthode controlClueValidity;
                controlClueValidity( inputClue, secretKey, proposition );
            } catch (SaisieErroneeException e) {
                logger.error("false input clue : "+ e);
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

    /**
     * controle de correspondance entre une propostion et une clée secrete.
     * @param proposition, String la proposition a tester
     * @param secretKey, String la clée secrète à trouver
     * @return boolean True ou false, avec affichage en console d'un message correspondant
     */
        public boolean isItVictory(String proposition, String secretKey) {
            // controle de victoire;
            boolean test=false;
            if (proposition.equals( secretKey )) {
                logger.info("We have a Winner : proposition_"+proposition+ " = secretKey_"+secretKey );
                System.out.println( "We have a Winner!" );
                test=true;
            } else {// si il n'y a pas d'égalité
                logger.info("wrong proposition : proposition_"+proposition+ " = secretKey_"+secretKey);
                System.out.println( "Oops! Wrong proposition! Try again..." );//dommage votre prop est fausse
            }
            return test;
        }

    /**
     * methode qui va vérifier l'existance et gérer les différents scénari de victoire possible
     * @param computerRole instance de computerRole
     * @param humanRole instance de humanRole
     * @return boolean true ou false, ainsi qu'un message correspondant à la situation
     */
        public boolean endGame(ComputerRole computerRole, HumanRole humanRole) {
            // gestion de fin de jeu par victoire.
            boolean test=false;
            if (!computerRole.getIsItVictory() && humanRole.getIsItVictory()) {
                logger.info("Winner : Human Player");
                System.out.println( "Human player " + humanRole.getName() + " wins!!!" );
                test=true;
            } else if (computerRole.getIsItVictory() && !humanRole.getIsItVictory()) {
                logger.info("Winner : Computer Player");
                System.out.println( "AI wins!!!" + computerRole.getName() + " wins!!!" );
                test=true;
            } else if (computerRole.getIsItVictory() && humanRole.getIsItVictory()) {
                logger.info("Winner : Human and Computer Players. Ex-aequo");
                System.out.println( "Oh my Gosh! Both players win!!!" );
                test=true;
            }
            return test;
        }

    /**
     * controle d'entré utilisateur, ou l'on attends une réponse positive ou négative
     * @param inputUser, String contenant la valeur à tester
     * @return boolean resultat du test, + un message en cas de levée d'exception
     * @throws SaisieErroneeException
     */
        public boolean controlYesNo(String inputUser)throws SaisieErroneeException {
            boolean test;
            try {
                switch(inputUser.toLowerCase()){
                    case "yes":
                    case "oui":
                    case "no":
                    case "non":
                        // si inputUser est égale à yes, oui, non, no, test OK.
                        test = true;
                        break;
                    default:
                        // pour toutes autres valeurs, levée d'exception.
                        throw new SaisieErroneeException( "Bad value for String" );
                }
            } catch (SaisieErroneeException e) {
                test=false;
                System.out.println( "Just put yes/oui or no/non" );
            }
            return test;
        }
    }



