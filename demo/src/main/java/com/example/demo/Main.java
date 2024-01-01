package com.example.demo;

import com.example.demo.model.retrievedinfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.Calendar;

import static com.example.demo.src.forgot_password.signupbegintime;
import static com.example.demo.src.forgot_password.xorencryptpassword;

@SpringBootApplication
public class Main {
    private static String allroles="hr//intern//assist//dean//fac";

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/twofactor";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public  static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static int passtrycount=0;
     public static void insertUser(String username, String password, String roles, int level, int id,String phoneno) throws SQLException {
        String passhash=xorencryptpassword(password);

        String insertQuery = "INSERT INTO Users (id,username, password, roles,level,phoneno) VALUES (?, ?, ?,?,?,?   )";
        preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, passhash);
        preparedStatement.setString(4, roles);
        preparedStatement.setInt(5,level);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(6,phoneno);
        preparedStatement.executeUpdate();
    }

    public static retrievedinfo getUserInformation(String username, String password, retrievedinfo userdetails) throws SQLException {
        String selectQuery = "SELECT * FROM Users WHERE username = ?";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username);


        try (ResultSet rs = preparedStatement.executeQuery()) {
            String passhash = xorencryptpassword(password);
            String inserttime = "INSERT INTO sessions(id,signin_time) VALUES (?,CURRENT_TIMESTAMP)";

            while (rs.next()) {
                int userId = rs.getInt("id");
                String retrievedUsername = rs.getString("username");
                String retrievedPassword = rs.getString("password");
                int flag = ckflag(userId);
                if (flag == 1) {
                    if (!incorrectpassword(userId)) {
                        return null;
                    }
                }

                if (!passhash.equals(retrievedPassword)) {
                    ip();


                    passtrycount++;
                    preparedStatement = connection.prepareStatement(inserttime);
                    preparedStatement.setInt(1, userId);
                    preparedStatement.executeUpdate();
                    return null;
                }
                else {
                    passtrycount=0;
                }

                preparedStatement = connection.prepareStatement(inserttime);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();

                String retrievedRoles = rs.getString("roles");
                int level = rs.getInt("level");
                String phn=rs.getString("phoneno");


                System.out.println("User ID: " + userId);

                System.out.println("Username: " + retrievedUsername);


                String lowerRetrievedRoles = retrievedRoles.toLowerCase();
                if (roles(lowerRetrievedRoles)) {
                    System.out.println("Roles: " + retrievedRoles);
                    userdetails.setRoles(retrievedRoles);
                } else {
                    System.out.println("Roles: null");
                    userdetails.setRoles(null);
                }

                System.out.println("Level: " + level);
                userdetails.setUsername(retrievedUsername);
                userdetails.setId(userId);
                userdetails.setLevel(level);
                userdetails.setPhoneno(phn);

            }

        }
        return userdetails;
    }
    public static int wrongpassword(String username) throws SQLException {
        int userId=idfind(username);
        if (passtrycount > 4) {
            setflag(1, userId);
            return incorrectpassword(userId)?1:2;
        }
        passtrycount++;
                signupbegintime(userId);

                return 0;
    }
    private static boolean roles(String roles){
        int index=0;
        String[] allrol=allroles.split("//");
        String[] rol=roles.split("//");
        int count=0;
        for(int i=0;i<allrol.length;i++){
            for (int j=0;j<rol.length;j++){
                if(allrol[i].equals(rol[j])){
                    count++;
                }

            }}
        return count==rol.length;
    }

    private static boolean incorrectpassword(int id) throws SQLException {
        String selectQuery = "SELECT MAX(signin_time) AS max_signin_time FROM sessions WHERE id=?";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Timestamp maxSignInTime = resultSet.getTimestamp("max_signin_time");



            Timestamp currentTime = new Timestamp(System.currentTimeMillis());


            Calendar cal = Calendar.getInstance();
            cal.setTime(maxSignInTime);
            cal.add(Calendar.MINUTE, 1);
            Timestamp oneHourAfterMaxSignInTime = new Timestamp(cal.getTimeInMillis());



            if (currentTime.before(oneHourAfterMaxSignInTime)) {
                System.out.println("many attempts ");
                System.out.println("Try after-"+oneHourAfterMaxSignInTime);
                return false;

            }
            else{
                setflag(0,id);
            }



        }
        return true;
    }
    private static int ckflag(int id) throws SQLException {
        String selectQuery = "SELECT flag FROM sessions WHERE id = ? AND signin_time = (SELECT MAX(signin_time) FROM sessions WHERE id = ?)";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, id);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("flag");
        } else {
            return -1;
        }
    }
    public static String ip(){
         return "incorrect password";

    }
    public static int idfind(String username) throws SQLException {
        String selectQuery = "SELECT * FROM Users WHERE username = ?";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
        int userId = rs.getInt("id");
        return userId;}
        else {
            return 0;
        }

    }
    public static retrievedinfo idphnfind(retrievedinfo uspa) throws SQLException {
        String selectQuery = "SELECT * FROM Users WHERE username = ?";
        preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, uspa.getUsername());
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            int userId = rs.getInt("id");
            uspa.setId(userId);
            String phn = rs.getString("phoneno");
            uspa.setPhoneno(phn);
            return uspa;}
        else {
            return null;
        }

    }

    private static void setflag(int flag, int userId) throws SQLException {
        String selectQuery1 = "UPDATE sessions SET flag = ? WHERE id = ? AND signin_time = (SELECT MAX(signin_time) FROM sessions WHERE id = ?)";
        preparedStatement = connection.prepareStatement(selectQuery1);
        preparedStatement.setInt(1, flag);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, userId);
        preparedStatement.executeUpdate();
    }
    public static void main(String[] args) throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        SpringApplication.run(Main.class, args);
    }
}
