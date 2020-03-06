package com.ocr.emilie;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import java.io.*;
import java.util.Properties;

    public class DefaultProperties {
        public static void readProperties(Game myGame) {


            try (InputStream input=new FileInputStream( "src/com/ocr/emilie/config.properties" )) {
                Properties prop=new Properties();

                // load a properties file
                prop.load( input );

                String propertiesDevMode=prop.getProperty("GameParametre.devMode");
                myGame.setDevMode(Boolean.parseBoolean(propertiesDevMode));//conversion qui peut se faire only si chaine carac comprend "false" ou "true"

                // get the property value and print it out
                String propertiesKeyLength=prop.getProperty( "GameParametre.keyLength" );
                myGame.setKeyLength( propertiesKeyLength ); // retient la keyLength mise par defaut dans le fichier properties.

                // on calcul le nombre d'essaie maximum en fonction de la keyLength.
                if(myGame.keyLength<=4){ // 4 essaie minimum
                    int maxRound=4;
                }else {
                    int maxRound=myGame.getKeyLength() / 2 + 2; // permet de définir le nombre d'essais par rapport à la taille de la clé
                    // sinon nombre d'essai = tailleClée / 2 + 2
                    myGame.setMaxRound( maxRound );
                }

            } catch (IOException ex) { // exception si la lecture du fichier properties est impossible.
                ex.printStackTrace();
            }


        }

        //méthode pour écrire dans le fichier properties, non nécessaire.
        // j'ai voulu le faire pour m'entrainer et bien comprendre le fonctionnement.

        //todo retirer?

  /*  public static void writeProperties(Game myGame){
        try(
                OutputStream output=new FileOutputStream( "src/com/ocr/emilie/config.properties" ))

        {

            Properties prop=new Properties();

            // set the properties value
            prop.setProperty( "GameParametre.keyLength", "4");
            prop.setProperty( "GameParametre.maxRound", "6"  );
            prop.setProperty( "humanRole.name", myGame.humanRole.getName() );

            // save properties to project root folder
            prop.store( output, null );

            System.out.println( prop );

        } catch(
                IOException io)

        {
            io.printStackTrace();
    }

    }*/
    }

