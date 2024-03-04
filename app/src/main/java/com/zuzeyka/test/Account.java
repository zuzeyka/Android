package com.zuzeyka.test;

public class Account {
    private String name;
    private String cardNumber;
    private double balance;

    public Account(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.balance = 1000;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

