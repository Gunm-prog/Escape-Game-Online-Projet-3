package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Defenser extends GameControllerException implements Mode{

    public void launchMode(HumanRole humanRole, ComputerRole computerRole) {

        computerRole.setProposition();
        computerRole.printRound("proposition");
        humanRole.printRound("secretKey");
        humanRole.setClue( computerRole.getProposition() );
        computerRole.setIsItVictory(isItVictory( computerRole.getProposition(),humanRole.getSecretKey()) );
        humanRole.printRound("clue");
        computerRole.updateTentative( humanRole.getClue() );




    }
}