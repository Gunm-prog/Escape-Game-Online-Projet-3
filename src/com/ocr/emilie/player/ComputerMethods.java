package com.ocr.emilie.player;

public interface ComputerMethods {
    void setTentative();
    String getTentative();
    void updateTentative(String HumanClue);
    int getRandomNumberInRange ( int min, int max);
}
