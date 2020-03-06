package com.ocr.emilie.player;

public interface ComputerMethods {
    /**
     * interface spécifique au computer, pour impléménter certaines de ces méthodes.
     */
    void setTentative();
    String getTentative();
    void updateTentative(String HumanClue);
    int getRandomNumberInRange ( int min, int max);
}
