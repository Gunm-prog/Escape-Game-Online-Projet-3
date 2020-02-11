package com.ocr.emilie.mode;
import com.ocr.emilie.player.ComputerRole;
import com.ocr.emilie.player.HumanRole;

public interface Mode{
    void launchMode(HumanRole humanRole, ComputerRole computerRole);
}
