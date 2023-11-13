package edu.storeservice.core.ch3;

import lombok.Data;

@Data
public class Account {

    private Integer id;
    private String name;
    private Integer balance;

    public Account() {

    }

    public Account(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }
}
