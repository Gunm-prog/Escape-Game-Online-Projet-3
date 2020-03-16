package com.ocr.emilie.mode;

import com.ocr.emilie.game.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Challenger extends GameControllerException implements Mode {

    /**
     * launchMode du mode challanger va gérer le comportement des différents joueurs dans ce mode.
     * a savoir le joueur IA va générer sa clée secrete
     * le joueur humain va faire des proposition pour la trouver
     * le joueur IA donnera des indices par rapport à ces proposition pour aider le joueur humain.
     * si le mode dev est activé, la solution de la clée secrete du joueur IA sera affiché.
     * @param devMode
     * @param humanRole
     * @param computerRole
     */
    public void launchMode(int currentRound, boolean devMode, HumanRole humanRole, ComputerRole computerRole) {

        if(currentRound!=1) {
            humanRole.printRound("proposition");
            computerRole.printRound("clue");
        }
        humanRole.setProposition();
        computerRole.setClue( humanRole.getProposition());
        System.out.print("For "+humanRole.getName()+" : ");
        humanRole.setIsItVictory(isItVictory(humanRole.getProposition(), computerRole.getSecretKey()) );

        if(devMode==true){
            computerRole.devMode();
        }
        //computerRole.printRound("secretKey");
        computerRole.printRound("__");
        humanRole.printRound("proposition");
        computerRole.printRound("clue");





    }
}