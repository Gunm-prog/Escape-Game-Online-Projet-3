package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Challenger extends GameControllerException implements Mode {

    public void launchMode(HumanRole humanRole, ComputerRole computerRole) {

        humanRole.setProposition();
        computerRole.setClue( humanRole.getProposition());
        humanRole.setIsItVictory(isItVictory(humanRole.getProposition(), computerRole.getSecretKey()) );

        computerRole.printRound("secretKey");
        computerRole.printRound("__");
        humanRole.printRound("proposition");
        computerRole.printRound("clue");





    }
}