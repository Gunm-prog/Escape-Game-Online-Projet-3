package com.ocr.emilie.mode;

import com.ocr.emilie.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Duel extends GameControllerException implements Mode {

    /**
     * launchMode du mode duel, va utiliser les comportement des deux précédents mode.
     * De ce fait, il instenciera ces mode pour en faire usage.
     *
     * @param devMode, boolean, etat du mode dev
     * @param humanRole, instance d'humanRole représentant le joueur humain
     * @param computerRole, instance de computerRole représentant le joueur IA
     */
    public void launchMode( int currentRound, boolean devMode, HumanRole humanRole, ComputerRole computerRole) {
        Challenger challanger = new Challenger();
        challanger.launchMode(currentRound, devMode,  humanRole, computerRole );
        computerRole.printRound("__");
        Defenser defenser = new Defenser();
        defenser.launchMode(currentRound, devMode, humanRole, computerRole );
    }
}
