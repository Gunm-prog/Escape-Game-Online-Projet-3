package com.ocr.emilie.player;

public interface PlayerType {

    /*
        Il s'agit de l'interface PlayerType, qui obligera les class concernant des player à implémenter les méthode suivante.
     */
    void setProposition();
    void setSecretKey();
    void setClue(String proposition);
    void setName();
}
