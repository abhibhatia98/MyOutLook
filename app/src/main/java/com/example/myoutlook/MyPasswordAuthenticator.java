package com.example.myoutlook;

import java.util.Objects;

public class MyPasswordAuthenticator {
    private String email,password;
    static String otp;


    MyPasswordAuthenticator(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public static String generateOtp() {
        int randomPin   =(int)(Math.random()*9000)+1000;
        otp = String.valueOf(randomPin);
        return otp;
    }

    public void sendOTP(){
        generateOtp();
        email = email.trim();





    }
    public boolean readOTP(){
        /*MailReader mailRead = new MailReader();
        new Thread(mailRead).start();
        while(mailRead.readOTP == null){
            if(Objects.equals(mailRead.readOTP, otp)){
                return true;
            }
        }*/
        return false;


    }



}
