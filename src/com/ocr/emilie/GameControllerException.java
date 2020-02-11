package com.ocr.emilie;

import java.util.Scanner;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

import java.util.*;

import com.ocr.emilie.SaisieErroneeException;

    public abstract class GameControllerException {

        public static int charToInt(char c) {
            String strC=Character.toString( c );
            return Integer.parseInt( strC );
        }

        public static void controlClueValidity(String inputClue, String secretKey, String proposition) throws SaisieErroneeException {
            boolean test=true;
            for (int i=0; i < secretKey.length(); i++) {//méthode vérifiant si l'indice donné par le joueur n'est pas erroné
                int charKey=charToInt( secretKey.charAt( i ) );//extraction et convertion du char à index i
                int charProp=charToInt( proposition.charAt( i ) );//idem
                char plus='+';
                char moins='-';
                char egale='=';
                if (charKey > charProp) {
                    System.out.println( "charKey > charProp " + charKey + " > " + charProp );
                    if (inputClue.charAt( i ) != '+') {
                        System.out.println( "Bon char : +" );
                        test=false;
                    }
                } else if (charKey < charProp && inputClue.charAt( i ) != '-') {
                    System.out.println( "charKey < charProp " + charKey + " < " + charProp );
                    test=false;
                    System.out.println( "Bon char : -" );
                } else if (charKey == charProp && inputClue.charAt( i ) != '=') {
                    System.out.println( "charKey = charProp " + charKey + " = " + charProp );
                    test=false;
                    System.out.println( "Bon char : =" );
                }
            }
            if (test == false) {
                throw new SaisieErroneeException( "clue is not valid: " );
            }
        }

        public static void controlStringLength(String str, int length) throws SaisieErroneeException {
            if (str.length() != length) {
                throw new SaisieErroneeException( "String length must be equals to " + length );
            }
        }

        public static void controlMinSmallerThanMax(int min, int max) throws SaisieErroneeException {
            if (min > max) {
            /*
            test  de la cohérence du min max imposé dans le test:
            si je veux vérifier que l'utilisateur entre bien une valeur dans l'intervale proposé.
            exemple: le menu choix de 1 à 4, éviter un mauvais choix utilisateur de 0 ou 5 ou +

            ici je vérifie juste que mon intervalle donné à la méthode testIntInputUser, est cohérent pour les test qui suiveront
            */
                throw new SaisieErroneeException( "max: " + max + "must be greater than min:" + min );
            }
        }

        public static void controlInterval(int inputVal, int min, int max) throws SaisieErroneeException {
            if (inputVal < min || inputVal > max) { // test sur le int
            /*
                ici test effectif de la valeur donnée par l'utilisateur avec l'intervale des possibles transmis en arguments de la méthode.
                */
                throw new SaisieErroneeException( "Please, respect number interval [" + min + "/" + max + "]" );
            }
        }

        public boolean isIntValid(String inputUser) {
            boolean test=true;
            try {
            /*
            pour une chaine de 1 à 20
            on veut savoir si cette chaine est entierement numerique
            probleme: le type int en java ne peut pas dépasser la taille de 9 caractères
            on ne peut pas utiliser le type long parce qu'il est possible que notre chaine soit petite
            donc on va devoir chercher combien de fois la chaine de caractere contient de séquences de 9 chiffres
            pour cela on fait taille de la chaine(keylentgh) / par 9
            le resultat de ce calcul nous donnera le nombre de fois où on pourra traiter 9 caracteres dans la chaine
            nous ferons donc une boucle qui tournera travaillera autant de fois que ce resultat
            dans cette boucle on traitera la vérification numérique sur 9 caractères
            int nbSubstring =  inputUser.length / 9
            reste une variable (int resteSubstring = inputUser.lenght % modulo * 9)
            */


                if (inputUser.length() > 9) {
                    int nbSubstring=inputUser.length() / 9;
                    int resteSubstring=inputUser.length() % 9;
                    for (int i=0; i < nbSubstring; i++) {
                        String substr=inputUser.substring( 9 * i, 8 + 9 * i );
                        Integer.valueOf( substr );
                    }
                    if (resteSubstring != 0) {
                        String substr=inputUser.substring( nbSubstring * 9, inputUser.length() );
                        Integer.valueOf( substr );
                    }
                } else {
                    Integer.valueOf( inputUser );
                }
                //tentative de conversion de la valeur donnée par l'utilisateur, ne fonctionnera que si c'est une valeur numérique
                // exemple, "grut", "a" ect... ne passeront pas le test.

            } catch (NumberFormatException e) {
                System.err.println( "This is not a number: " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

        public boolean isIntValidInInterval(String inputUser, int min, int max) {
            boolean test=true;
            int input=Integer.parseInt( inputUser ); // convertion du String en Int
            try {
                controlMinSmallerThanMax( min, max );
                controlInterval( input, min, max );
            } catch (SaisieErroneeException e) {
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

        public boolean isIntEqualsKeyLength(String inputProposition, int keyLength) {//Int de isIntEqualsKeyLength peut correspondre
            //à la proposition ou la secretKey(à n'importe quelle valeur numérique)
            boolean test=true;
            try {
                controlStringLength( inputProposition, keyLength );
            } catch (SaisieErroneeException e) {
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

        public boolean isInputClueValid(String inputClue, String secretKey, String proposition) {
            boolean test=true;
            try {
                controlClueValidity( inputClue, secretKey, proposition );
            } catch (SaisieErroneeException e) {
                System.out.println( "Saisie erronée : " + e );
                System.out.println( "Please enter again" );
                test=false;
            }
            return test;
        }

        public boolean isItVictory(String proposition, String secretKey) {
            boolean test=false;
            if (proposition.equals( secretKey )) {
                System.out.println( "Bravo! Vous avez gagné!" );
                test=true;
            } else if (proposition != secretKey) {//tranformer en else
                System.out.println( "Oops! La proposition est fausse!" );//dommage votre prop est fausse
            }
            return test;
        }

        public boolean endGame(ComputerRole computerRole, HumanRole humanRole) {
            boolean test=false;
            if (!computerRole.getIsItVictory() && humanRole.getIsItVictory()) {
                System.out.println( "Human player " + humanRole.getName() + " wins!!!" );
                test=true;
            } else if (computerRole.getIsItVictory() && !humanRole.getIsItVictory()) {
                System.out.println( "AI wins!!!" );
                test=true;
            } else if (computerRole.getIsItVictory() && humanRole.getIsItVictory()) {
                System.out.println( "Oh my Gosh! Both players win!!!" );
                test=true;
            }
            return test;
        }


        public boolean controlStringValue(String inputUser, String value) throws SaisieErroneeException {
            boolean test=true;
            Scanner sc=new Scanner( System.in );
            if (!inputUser.equals( value )) {
                System.out.println( "Not a valid answer! Please enter again: oui or non" );
                test=false;
                try {
                    throw new SaisieErroneeException( "Bad value for String" );
                } catch (SaisieErroneeException e) {

                }
            } else {
                test=true;
            }
            return test;
        }

        public boolean controlYesNo(String inputUser)throws SaisieErroneeException {
            boolean test=true;
            try {
                switch (inputUser) {
                    case ("oui"):
                        controlStringValue( inputUser, "oui" );
                        break;
                    case ("non"):
                        controlStringValue( inputUser, "non" );
                        break;
                    default:
                        throw new SaisieErroneeException( "Bad value for String" );
                }
            } catch (SaisieErroneeException e) {
                test=false;
                System.out.println( "Just put oui or non dammit!!!" );
            }
            return test;
        }
    }



