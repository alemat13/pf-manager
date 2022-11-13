package com.pfmanager;

import java.util.Date;

import com.pfmanager.core.entity.Account;
import com.pfmanager.core.entity.Bank;
import com.pfmanager.core.entity.Currency;
import com.pfmanager.core.entity.Transaction;
import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.User;
import com.pfmanager.core.entity.enums.TransactionType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User pierre = new User("Pierre", "pierre@gmail.com");
        User paul = new User("Paul", "paul@gmail.com");

        Currency euro = new Currency("Euro", "EUR");

        Account pierreAccount = new Account("Compte perso Pierre", euro, new Bank("Boursorama"));
        Account paulAccount = new Account("Compte perso Paul", euro, new Bank("Société Générale"));
        Account jointAccount = new Account("Compte joint", euro, new Bank("HSBC"));

        pierreAccount.getOwners().add(pierre);
        paulAccount.getOwners().add(paul);
        jointAccount.getOwners().add(pierre);
        jointAccount.getOwners().add(paul);

        TransactionCategory groceries = new TransactionCategory("Groceries");
        groceries.setParent(new TransactionCategory("Every day expenses"));

        Transaction carrefour = new Transaction(new Date(), 33.56, "Carrefour");
        carrefour.setCategory(groceries);
        carrefour.setSourceAccount(jointAccount);
        carrefour.setType(TransactionType.TRANSFER);
    }
}

