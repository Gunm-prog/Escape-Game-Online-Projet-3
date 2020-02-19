package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Duel extends GameControllerException implements Mode {

    public void launchMode( boolean devMode, HumanRole humanRole, ComputerRole computerRole) {
        Challenger challanger = new Challenger();
        challanger.launchMode(devMode,  humanRole, computerRole );
        Defenser defenser = new Defenser();
        defenser.launchMode(devMode, humanRole, computerRole );
    }
}
