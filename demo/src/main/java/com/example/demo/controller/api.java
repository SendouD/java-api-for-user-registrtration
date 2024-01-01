package com.example.demo.controller;


import com.example.demo.model.otppassword;
import com.example.demo.model.retrievedinfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.example.demo.Main.*;
import static com.example.demo.src.forgot_password.sessionTimer;
import static com.example.demo.src.forgot_password.xorencryptpassword;

@RestController
@RequestMapping("/login")
public class api {

    retrievedinfo retrievedinfo;



    @GetMapping("/newuser")
    public String insertdetails(@RequestParam String username,@RequestParam String password ,@RequestParam String roles,@RequestParam  int level,@RequestParam int id,@RequestParam String  phoneno) throws SQLException {

        insertUser(username,password,roles,level ,id,phoneno);
        return "inserted successfully";

    }
    @PostMapping("/getuserinfo")
    public Object getuser(@RequestParam String username,@RequestParam String password) throws SQLException {
        this.retrievedinfo=new retrievedinfo(0,null,null,null,0);
        retrievedinfo=getUserInformation(username,password, retrievedinfo);
        if (retrievedinfo == null) {
            int i= wrongpassword(username);
            if(i==2){
                return "So many attempts Try again after some time ";
            }
            return i==1?"So many attempts Try again after some time ":"Incorrect password";
        } else {
            return retrievedinfo;
        }
    }

    @GetMapping("/resetmode")
    public RedirectView resetmode(@RequestParam int op,@RequestParam String username) {
        String ckck = sessionTimer(op, username);
        return new RedirectView("/login/cp");
    }

    @GetMapping("/cp")
    public  String cp(@RequestParam String otp,@RequestParam String password) throws IOException {

        ObjectMapper objectMapper=new ObjectMapper();
        otppassword otppass=objectMapper.readValue(new File("otpp.json"),otppassword.class);
        System.out.println("Enter otp");
        String ckotp = otppass.getOtp();
        long sessionstarttime=otppass.getSessiontime();
        int id=otppass.getId();

        if (otp.equals(ckotp)) {
            System.out.println("Enter password");

            long currentTime = System.currentTimeMillis();
            String nwpass = xorencryptpassword(password);
            if (currentTime - sessionstarttime< 60000) {
                String query = "UPDATE users SET password = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, nwpass);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    System.out.println("Password updated successfully.");
                    return "password updated";
                } catch (SQLException e) {
                    e.printStackTrace();
                }}
            else {
                System.out.println("session time out, cannot upadate password");
                return "session timeout";
            }
        } else {
            System.out.println("Invalid otp");
            return "invalid OTP";
        }
        return "ERROR";
    }

    @GetMapping("/checking")
    public String che(){
        System.out.println("doeneeee");
        return "wrking";
    }
}
