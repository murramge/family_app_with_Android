package com.example.honeybee.story;

public class storyItem {
    int no;
    String name;
    String msg;
    String imgPath;
    String date;
    String familyCode;

    public storyItem(int no, String name, String msg, String imgPath, String date, String familyCode) {
        this.no = no;
        this.name = name;
        this.msg = msg;
        this.imgPath = imgPath;
        this.date = date;
        this.familyCode = familyCode;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no){
        this.no = no;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFamilyCode() {return familyCode;}

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }
}


