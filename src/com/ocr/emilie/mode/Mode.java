package com.ocr.emilie.mode;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public interface Mode{
    void launchMode( boolean devMode, HumanRole humanRole, ComputerRole computerRole);
}
