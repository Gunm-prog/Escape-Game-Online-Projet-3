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
                myGame.setDevMode(Boolean.parseBoolean(propertiesDevMode));//conversion qui peut se faire only si chaine carac comprend false/true
                // get the property value and print it out
                String propertiesKeyLength=prop.getProperty( "GameParametre.keyLength" );
                myGame.setKeyLength( propertiesKeyLength );
                if(myGame.keyLength<=4){
                    int maxRound=4;
                }else {
                    int maxRound=myGame.getKeyLength() / 2 + 2; // permet de définir le nombre d'essais par rapport à la taille de la clé
                    //  String propertiesMaxRound=prop.getProperty("GameParametre.maxRound");
                    myGame.setMaxRound( maxRound );
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
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

