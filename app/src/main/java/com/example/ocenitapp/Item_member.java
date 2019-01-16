package com.example.ocenitapp;

public class Item_member {
    int image;
    String name;
    String division;
    String number;
    String mail;

    int getImage() {
        return this.image;
    }

    String getName() {
        return this.name;
    }

    String getDivision() {
        return this.division;
    }

    String getNumber() {
        return this.number;
    }

    String getMail() {
        return this.mail;
    }

    Item_member(int image, String name, String division, String number, String mail ) {
        this.image = image;
        this.name = name;
        this.division = division;
        this.number = number;
        this.mail = mail;

    }
}