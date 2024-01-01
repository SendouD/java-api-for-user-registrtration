package com.example.demo.src;

import com.example.demo.model.otppassword;
import com.example.demo.model.retrievedinfo;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import static com.example.demo.Main.connection;
import static com.example.demo.Main.idphnfind;


public class forgot_password {

    private static PreparedStatement preparedStatementfpas;
    private static ResultSet resultSetfpas;

    public static void signupbegintime( int id) throws SQLException {
        String s = "UPDATE sessions SET session_begin = CURRENT_TIMESTAMP WHERE id = ? AND signin_time = (SELECT MAX(signin_time) FROM sessions WHERE id = ?)";

        preparedStatementfpas = connection.prepareStatement(s);
        preparedStatementfpas.setInt(1, id);    
        preparedStatementfpas.setInt(2, id);

        preparedStatementfpas.executeUpdate();

    }

   public static String  sessionTimer(int op,String username) {
        try {
            retrievedinfo uspa=new retrievedinfo(0,username,null,null,0);

            uspa=idphnfind(uspa);
            int id=uspa.getId();
            String phn=uspa.getPhoneno();

            long start = System.currentTimeMillis();

            if(op==1){
                return smsSender(start,id,phn);


            } else if (op==2) {
                return mailhosting("mis@cdot.in", id,start);

            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }

    public static String mailhosting(String receivermailid, int id, long sessionStartTime) {
        String sendermailid = "mis@cdot.in";
        String host = "49.128.111.121";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            String otp = generateOTP();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendermailid));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivermailid));
            message.setSubject("OTP for Password Reset");
            String emailContent = "Your OTP for password reset is: " + otp;
            message.setText(emailContent);
            Transport.send(message);
            System.out.println("Email sent successfully with OTP.");



            otppassword otpp=new otppassword(id,otp,sessionStartTime);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(new File("otpp.json"),otpp);



        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String smsSender( long sessionStartTime,int id,String phn) throws IOException {
        String apiURL = "http://192.168.46.233:13013/cgi-bin/sendsms";
        String username = "tester";
        String password = "foobar";
        String otpc = generateOTP();
        String otp = "otp :" + otpc;
        String text = URLEncoder.encode("'" + otp + "'", StandardCharsets.UTF_8.toString());
        String to = phn;
        System.out.println(phn);


        try {
            String urlString = String.format("%s?username=%s&password=%s&coding=0&text=%s&to=%s",
                    apiURL, username, password, text, to);
            System.out.println(otp);
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            otppassword otpp=new otppassword(id,otpc,sessionStartTime);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(new File("otpp.json"),otpp);




            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.printf("Response: %s%n", response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   return "done";
    }
    public static String smsverify( String username1) throws IOException, SQLException {
        String apiURL = "http://192.168.46.233:13013/cgi-bin/sendsms";
        String username = "tester";
        String password = "foobar";
        String otpc = generateOTP();
        String otp = "otp :" + otpc;
        String text = URLEncoder.encode("'" + otp + "'", StandardCharsets.UTF_8.toString());
        retrievedinfo uspa=new retrievedinfo(0,username1,null,null,0);
        uspa=idphnfind(uspa);
        String phn = uspa.getPhoneno();
        int id=uspa.getId();

        String to = phn;
        System.out.println(phn);


        try {
            String urlString = String.format("%s?username=%s&password=%s&coding=0&text=%s&to=%s",
                    apiURL, username, password, text, to);
            System.out.println(otp);
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            otppassword otpp=new otppassword(id,otpc,0);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.writeValue(new File("otpc.json"),otpp);




            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.printf("Response: %s%n", response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }


    private static String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }

    private static void checkotp(String otp, int id, long sessionstarttime) {
        System.out.println("Enter otp");
        Scanner sc = new Scanner(System.in);
        String ckotp = sc.next();
        if (otp.equals(ckotp)) {
            System.out.println("Enter password");
            String password = sc.next();
            long currentTime = System.currentTimeMillis();
            String nwpass = xorencryptpassword(password);
            if (currentTime - sessionstarttime< 60000) {
            String query = "UPDATE users SET password = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nwpass);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
                System.out.println("Password updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }}
            else {
                System.out.println("session time out, cannot upadate password");
            }
        } else {
            System.out.println("Invalid otp");
        }
    }

    public static String xorencryptpassword(String password) {
        String key = ".|cdot";
        int keyindex = 0;
        int i = 0;
        StringBuilder passhash = new StringBuilder();
        while (i < password.length()) {
            char encryptedChar = (char) (~(password.charAt(i) ^ key.charAt(keyindex)));
            passhash.append(encryptedChar);
            i++;
            keyindex++;
            if (keyindex == key.length()) {
                keyindex = 0;
            }
        }
        return passhash.toString();
    }
}
