package com.example.yasser.everyday;

public class Contact {
    private String name;
    private  int num;
    private  int id;
    private  byte[] image;

    public Contact(int id,String name, int num,byte[] image) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.image = image;
    }
    public Contact(int id,String name, int num) {
        this.id = id;
        this.name = name;
        this.num = num;
    }
    public Contact(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Contact(String name, int num, byte[] image) {
        this.name = name;
        this.num = num;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
