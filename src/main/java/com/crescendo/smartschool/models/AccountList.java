package com.crescendo.smartschool.models;


import java.util.ArrayList;

/** AccountList POJO
 * @author Groep 5
 */
public class AccountList {

    private ArrayList<Account> account;

    public ArrayList<Account> getAccount() { return this.account; }

    public void setAccount(ArrayList<Account> account) { this.account = account; }

    public AccountList() {
    }

    public AccountList(ArrayList<Account> account) {

        this.account = account;
    }


}
