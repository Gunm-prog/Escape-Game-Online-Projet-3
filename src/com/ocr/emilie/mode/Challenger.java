package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Challenger extends GameControllerException implements Mode {

    public void launchMode(HumanRole humanRole, ComputerRole computerRole) {

        System.out.println("secrete Key du computer = "+ computerRole.getSecretKey());
        humanRole.setProposition();
        humanRole.printProposition();
        humanRole.setIsItVictory(isItVictory(humanRole.getProposition(), computerRole.getSecretKey()) );
        computerRole.setClue( humanRole.getProposition());
        computerRole.printClue();


    }
}