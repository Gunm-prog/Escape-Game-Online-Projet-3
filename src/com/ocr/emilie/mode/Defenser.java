package com.ocr.emilie.mode;

import com.ocr.emilie.game.GameControllerException;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public class Defenser extends GameControllerException implements Mode{

    /**
     * launchMode de mode Defenser, va gérer les comportements d'action des différent joueur deans ce mode.
     * a savoir, le joueur humain créer un clée secrete
     * le joueur IA fera des proposition pour la trouver
     * le joueur Humain lui donnera des indices
     * le joueur IA utilisera ces indices pour mettre à joueur ces intervales de recherches.
     * si le mode dev est activé, la solution de clée secrete du joueur Humain devra s'afficher.
     * @param devMode, boolean etat du mode dev
     * @param humanRole, instance de HumanRole représentant le joueur humain
     * @param computerRole, instance de computerRole representant le joueur IA
     */
    public void launchMode( int currentRound, boolean devMode, HumanRole humanRole, ComputerRole computerRole) {

        if(devMode==true){
            humanRole.devMode();
        }
        computerRole.printRound("__");
        computerRole.setProposition();
        computerRole.printRound("proposition");
        humanRole.printRound("secretKey");
        humanRole.setClue( computerRole.getProposition() );
        System.out.print("For "+computerRole.getName()+" : ");
        computerRole.setIsItVictory(isItVictory( computerRole.getProposition(),humanRole.getSecretKey()) );
        humanRole.printRound("clue");
        computerRole.updateTentative( humanRole.getClue() );




    }
}