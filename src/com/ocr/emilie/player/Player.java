package com.ocr.emilie.player;

import com.ocr.emilie.GameParametre;

public  abstract class Player extends GameParametre{
    protected String name;
    protected String type;
    protected String proposition;
    protected String secretKey;
    protected String clue;
    protected int shot;
    protected boolean isItVictory;

    public String getName(){
        return this.name;
    }

    public String getProposition(){
        return this.proposition;
    }

    public void printClue(){
        System.out.println("This is the clue: " + clue);
    }

    public void printProposition(){
        System.out.println("Proposition: " + proposition);
    }

    public String getSecretKey(){
        return this.secretKey;
    }

    public String getClue(){
        return this.clue;
    }

    public void setShot(int newShot){
        this.shot = newShot;
    }

    public int getShot(){
        return this.shot;
    }

    public boolean getIsItVictory(){
        return this.isItVictory;
    }

    public void setIsItVictory(boolean newIsItVictory){
        this.isItVictory=newIsItVictory;
    }

    public void printPlayer(){
        System.out.println();
        System.out.println("************/_ Player _/************");
        System.out.println("type : "+type);
        System.out.println("name : "+name);
        System.out.println("secreKey : "+secretKey);
        System.out.println("proposition : "+proposition);
        System.out.println("clue : "+clue);
        System.out.println("shot : "+shot);
        System.out.println("isItVictory : "+isItVictory);
    }

    public void printAllParametre(){
        super.printAllParametre();
        this.printPlayer();
    }
    public void printRound(String strType){
        String str="";
        String strFinal="";
        switch(strType) {
            case "secretKey":
                str=getSecretKey();
                break;
            case "proposition":
                str=getProposition();
                break;
            case "clue":
                str=getClue();
                break;
            case"__":
                for(int i=0;i<getKeyLength();i++){
                    strFinal=strFinal + "__";
                }
                strFinal=strFinal +"__________________";
                break;
            default:
                strFinal="not a valid String";
        }
        if(strFinal==""){
            for (int i=0; i<this.getKeyLength();i++) {
                strFinal=strFinal + "|" + str.charAt( i );
            }
                strFinal=strFinal + "|                  " +this.getName() +"'s "+ strType;

        }

        System.out.println(strFinal);
    }

}
