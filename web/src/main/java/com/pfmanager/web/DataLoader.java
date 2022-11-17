package com.pfmanager.web;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.pfmanager.core.entity.Account;
import com.pfmanager.core.entity.Bank;
import com.pfmanager.core.entity.Currency;
import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.User;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.service.AccountService;
import com.pfmanager.core.service.TransactionService;
import com.pfmanager.core.service.UserService;

@Component
@ComponentScan
public class DataLoader {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public @Autowired DataLoader() {
        System.out.println("passe-t-on par là ?");
        loadData();
    }

    private void loadData()
    {
        User pierre = this.userService.saveUser(new User("Pierre", "pierre@gmail.com"));
        User paul = this.userService.saveUser(new User("Paul", "paul@gmail.com"));

        Currency euro = this.accountService.saveCurrency(new Currency("Euro", "EUR"));

        Account pierreAccount = new Account(
            "Compte perso Pierre",
            euro,
            this.accountService.saveBank(new Bank("Boursorama"))
        );
        Account paulAccount = new Account(
            "Compte perso Paul",
            euro,
            this.accountService.saveBank(new Bank("Société Générale"))
        );
        Account jointAccount = new Account(
            "Compte joint",
            euro,
            this.accountService.saveBank(new Bank("HSBC"))
        );

        pierreAccount.getOwners().add(pierre);
        paulAccount.getOwners().add(paul);
        jointAccount.getOwners().addAll(Arrays.asList(pierre, paul));
        this.accountService.saveAllAcounts(Arrays.asList(pierreAccount, paulAccount, jointAccount));

        TransactionCategory groceries = new TransactionCategory("Groceries");
        groceries.setParent(this.transactionService.save(new TransactionCategory("Every day expenses")));
        this.transactionService.save(groceries);

        Transaction carrefour = new Transaction(new Date(), 33.56, "Carrefour");
        carrefour.setCategory(groceries);
        carrefour.setAccount(jointAccount);
        this.transactionService.createTransaction(carrefour);
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
