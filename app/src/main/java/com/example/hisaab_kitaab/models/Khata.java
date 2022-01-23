package com.example.hisaab_kitaab.models;

public class Khata {
    private String recipient;
    private String amount;
    private String date;
    private String type;

    //setters------------------------------------
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setType(String type) {
        this.type = type;
    }

    //getters-------------------------------------------


    public String getRecipient() {
        return recipient;
    }

    public String getDate() {
        return date;
    }


    public String getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }



    public Khata(String recipient, String date, String amount, String type){
        setRecipient(recipient);
        setAmount(amount);
        setDate(date);
        setType(type);
    }


}
