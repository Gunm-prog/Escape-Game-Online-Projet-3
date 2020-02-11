package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Duel extends GameControllerException implements Mode {

    public void launchMode(HumanRole humanRole, ComputerRole computerRole) {
        Challenger challanger = new Challenger();
        challanger.launchMode( humanRole, computerRole );
        Defenser defenser = new Defenser();
        defenser.launchMode( humanRole, computerRole );
    }
}
