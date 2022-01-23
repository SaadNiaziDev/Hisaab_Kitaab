package com.example.hisaab_kitaab;

public class Users {
    private String name;
    private String email;
    private String password;

    public Users(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


}
