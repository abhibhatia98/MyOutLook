package com.example.myoutlook;
import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender implements  Runnable{
    private String username,password,OTP;


    public MailSender(String username, String password, String OTP) {
        this.username = username;
        this.password = password;
        this.OTP = OTP;
    }


    @Override
    public void run() {

        Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "outlook.office365.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
        try {
            Log.e("password","come here"+username+password+OTP);

            Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("abhishek.bhatia@nagarro.com"));
              /*  message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(
                        "abhishek.bhatia@nagarro.com"));*/
                message.setSubject("Verify OTP");
                String msg = "Hi, \n\nYou are just one step behind to Heaven \nYour OTP is : " +OTP +"\n\nRegards \nTeam";

            { String html="<html><head>"
                        + "<title>"+"helllo"+"</title>"
                        + "</head>"+"<LINK REL='stylesheet' HREF='stylesheet/fac_css.css' TYPE='text/css'>"
                        + "<body>"
                        +"<table width='900' cellpadding='0' cellspacing='0' border='0'>"
                        +"<tr><td class ='text12' width='100%'><br>Hi \n Please find my task details below \n </td></tr><tr>"
                        +"<td height='5'></td></tr>"
                        +"<tr><td></td></tr>"
                        +"<tr><td height='5'></td></tr>"
                        +"<tr><td><table border='1' width='800' cellpadding='2' cellspacing='1' bgColor='#B6AFA9' style='border-collapse: collapse' bordercolor='#EBDA2A' align='left'>"
                        +"<tr bgColor=#CD919E class='centerheading' align='left'>"
                        +"<td width='1' style='color: #FFFFFF;'><b>S.No.</b></td>"
                        +"<td width='5' style='color: #FFFFFF;'><b>Task</b></td>"
                        +"<td width='3' style='color: #FFFFFF;'><b>Efforts</b></td>"
                        + "</tr>"
                        +"<tr>"
                        +"<td width='1' style='color: #EEE9E9;'>"+"01"+"</b></td>"
                        +"<td width='3' style='color: #EEE9E9;'>"+"Testing world"+"</b></td>"
                        +"<td width='5' style='color: #EEE9E9;'>"+"4 hrs"+"</b></td>"
                        + "</tr>"
                        +"<tr>"
                        +"<td width='1' style='color: #EEE9E9;'>"+"02"+"</b></td>"
                        +"<td width='3' style='color: #EEE9E9;'>"+"Again Testing"+"</b></td>"
                        +"<td width='5' style='color: #EEE9E9;'>"+"4 hrs"+"</b></td>"


                        +"</table>"
                        +"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td height='6'></td>"
                        +"</tr>"
                        +"<tr>"
                        +"</tr>"
                        +"<tr>"
                        +"<td height='15'></td>"
                        +"</tr>"
                        +"<tr><td  class ='text12' width='100%'>"+"Regards"+"</td></tr>"
                        +"</table>"
                        +"</body></html>";}
                message.setContent(msg, "text/plain; charset=utf-8");

            Transport.send(message);
            MailReader mailRead = new MailReader();
            new Thread(mailRead).start();


            } catch (MessagingException e) {
            Log.e("password",e.toString());

        }
        }

}
