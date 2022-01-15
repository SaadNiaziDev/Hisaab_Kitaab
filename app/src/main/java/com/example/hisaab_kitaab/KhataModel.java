package com.example.hisaab_kitaab;

public class KhataModel {
    private String name;
    private String email;
    private String password;
    private String recipient;
    private String amount;
    private String date;
    private String image;
    private String type;

    //setters------------------------------------
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setType(String type) {
        this.type = type;
    }

    //getters-------------------------------------------

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public KhataModel(String email, String password, String name){
        setEmail(email);
        setPassword(password);
        setName(name);
    }

    public KhataModel(String recipient,  String date, String image, String type,String amount){
        setRecipient(recipient);
        setAmount(amount);
        setDate(date);
        setImage(image);
        setType(type);
    }


}
