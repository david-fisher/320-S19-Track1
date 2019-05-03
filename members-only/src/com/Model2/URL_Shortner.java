/*package com.Model2;

//import db.DBAdapter;
//import db.Database;

import java.util.Random;

public class URLShortner {

    DBAdapter DB;

    public void URLShortner() {
        DB = new DBAdapter();
    }

    public static String shorten(String origLink) {
        String alphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        while(code.length()<5) {
            int index = (int)(rnd.nextFloat() * alphaNum.length());
            code.append(alphaNum.charAt(index));
        }

        String shortLink = "membersonly.com/l/" + code.toString();

        //  createURL in DBAdapter
        boolean creation = Database.adapter.createURL(origLink,shortLink);
        if (!creation) {
            return shorten(origLink);
        }

        return shortLink;
    }
} */