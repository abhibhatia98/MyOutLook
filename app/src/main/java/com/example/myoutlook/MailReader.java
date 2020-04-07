package com.example.myoutlook;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;


public class MailReader implements Runnable {

    static  boolean check = false;

    static StringBuilder readOTP = new StringBuilder();

    @Override
    public void run() {

        try{
            Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "outlook.office365.com");
        properties.put("mail.smtp.port", "587");
        Session emailSession = Session.getDefaultInstance(properties);

        Session session = Session.getDefaultInstance(properties, null);
        String protocol = "imaps";
        Store store = session.getStore(protocol);
        store.connect("imap-mail.outlook.com","abhishek.bhatia@nagarro.com","INDIA@#$masti1234");
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
      /*  Message[] messages = emailFolder.getMessages();*/
        Message[] messages = emailFolder.search(
                    new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        for (int i =messages.length-1; i >0; i--) {
            Message message = messages[i];
            if(message.getSubject().trim().equals("Verify OTP")){
                Log.e("mailReader","From "+message.getFrom()[0].toString().trim());
                char[] temp = stringToCharArray(message.getContent().toString());
                Log.e("mailReader","OTP IS  "+ temp[63]+temp[64]+temp[65]+temp[66]);
                readOTP.append(temp[63]);
                readOTP.append(temp[64]);
                readOTP.append(temp[65]);
                readOTP.append(temp[66]);
                Log.e("mailReader","read OTP IS  "+ readOTP);
                Log.e("mailReader","class vala OTP is "+VerifyCredentials.otp);
                break;
            }
        }
        String temp = readOTP.toString().trim();
        if(temp.equals(VerifyCredentials.otp)){

            check = true;
            Log.e("mailReader","done check");
            readOTP = new StringBuilder();

          /*  Handler mainHandler = new Handler(activity.getMainLooper());

            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity,"done",Toast.LENGTH_SHORT).show();

                }
            };
            mainHandler.post(myRunnable);*/



        }


        emailFolder.close(false);
        store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private char[] stringToCharArray(String msg) {

            // Creating array of string length
            char[] ch = new char[msg.length()];

            // Copy character by character into array
            for (int i = 0; i < msg.length(); i++) {
                ch[i] = msg.charAt(i);
            }

            return ch;

        }



}
