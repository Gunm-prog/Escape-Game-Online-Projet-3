package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Defenser extends GameControllerException implements Mode{

    public void launchMode(HumanRole humanRole, ComputerRole computerRole) {

        computerRole.setProposition();
        computerRole.printProposition();
        computerRole.isItVictory( computerRole.getProposition(), humanRole.getSecretKey() );
        humanRole.setClue( computerRole.getProposition() );
        humanRole.printClue();
        computerRole.updateTentative( humanRole.getClue() );

    }
}