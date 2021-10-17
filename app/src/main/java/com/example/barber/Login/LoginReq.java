package com.example.barber.Login;

public class LoginReq {
    private String Cus_Username;
    private String Cus_Password;

    public String getUser_name() {
        return Cus_Username;
    }

    public void setUser_name(String customer_username) {
        this.Cus_Username = customer_username;
    }

    public String getUser_pass() {
        return Cus_Password;
    }

    public void setUser_pass(String customer_pass) {
        this.Cus_Password = customer_pass;
    }
}