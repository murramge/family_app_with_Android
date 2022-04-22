package com.example.honeybee.quest;

public class questItem {


    String quest;
    String userEmail;
    String familyCode;

    public questItem(  String quest,String userEmail,String familyCode) {

        this.quest = quest;
        this.userEmail = userEmail;
        this.familyCode = familyCode;
    }




    public String getquest() {
        return quest;
    }

    public void setquest(String quest) {
        this.quest = quest;
    }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail;}

    public String getFamilyCode() { return familyCode; }

    public void setFamilyCode(String familyCode) { this.familyCode = familyCode;}



}


