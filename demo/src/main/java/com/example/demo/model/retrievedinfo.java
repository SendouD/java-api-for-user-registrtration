package com.example.demo.model;

public class retrievedinfo {
    private int id;
    private String username;

    private String roles;
    private String phoneno;

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    private int level;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public retrievedinfo(int id, String username, String roles, String phoneno, int level) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.phoneno = phoneno;
        this.level = level;
    }
}
