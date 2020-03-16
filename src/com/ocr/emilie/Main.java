/**
 * @author Emilie BALSEN
 */
package com.ocr.emilie;

import com.ocr.emilie.game.Game;
import com.ocr.emilie.game.GameControllerException;
import com.ocr.emilie.game.SaisieErroneeException;
import org.apache.log4j.Logger;

//import org.apache.logging.log4j.Logger;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.apache.logging.log4j.LogManager;


public class Main {

    //static Logger logger = LogManager.getRootLogger();
     private static final Logger logger = Logger.getLogger( GameControllerException.class);

    public static void main(String[] args) throws SaisieErroneeException {
        // write your code here
       // DOMConfigurator.configure("log4j.xml");

        logger.debug("Log4j appender configuration is successful !!");
        Game myGame = new Game();// instanciation par defaut de Game


        //chargement de parametres par defaut contenue dans le fichier defautl.properties.
        DefaultProperties myDefaultProperties = new DefaultProperties();
        myDefaultProperties.readProperties(myGame);



        myGame.launchMenu();// ici, launchMenu lancera le menu, qui a terme change le mode de jeu en fonction du choix de l'utiisateur
        myGame.launchGame(); // lancement du jeu.

    }
}
