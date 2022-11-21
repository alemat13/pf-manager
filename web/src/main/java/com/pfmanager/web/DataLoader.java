package com.pfmanager.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pfmanager.core.entity.Account;
import com.pfmanager.core.entity.Bank;
import com.pfmanager.core.entity.Currency;
import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.TransactionLabel;
import com.pfmanager.core.entity.User;
import com.pfmanager.core.entity.transaction.AccountTransaction;
import com.pfmanager.core.entity.transaction.TransactionGroup;
import com.pfmanager.core.service.AccountService;
import com.pfmanager.core.service.TransactionService;
import com.pfmanager.core.service.UserService;

@Component
public class DataLoader {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    public @Autowired DataLoader() {
        System.out.println("passe-t-on par là ?");
    }

    @PostConstruct
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
        this.accountService.saveAllAcounts(Arrays.asList(pierreAccount, paulAccount, jointAccount));

        pierreAccount.setOwners(Arrays.asList(pierre));
        this.accountService.saveAccount(pierreAccount);
        paulAccount.setOwners(Arrays.asList(paul));
        this.accountService.saveAccount(paulAccount);
        jointAccount.setOwners(Arrays.asList(pierre, paul));
        this.accountService.saveAccount(jointAccount);

        TransactionLabel tbcLabel = this.transactionService.save(
            new TransactionLabel("TBC", "transactions to be checked")
        );

        TransactionCategory groceries = new TransactionCategory("Groceries");
        groceries.setParent(new TransactionCategory("Every day expenses"));
        this.transactionService.save(groceries);

        AccountTransaction carrefourJoint = new AccountTransaction(new Date(), 33.56, "Carrefour", jointAccount);
        carrefourJoint.setCategory(groceries);
        carrefourJoint.setLabels(Arrays.asList(tbcLabel));
        this.transactionService.saveTransaction(carrefourJoint);

        AccountTransaction carrefourPierre = new AccountTransaction(new Date(), 25, "Carrefour", pierreAccount);
        carrefourPierre.setCategory(groceries);
        carrefourPierre.setLabels(Arrays.asList(tbcLabel));
        this.transactionService.saveTransaction(carrefourPierre);

        TransactionGroup carrefour = this.transactionService.groupTransactions(Arrays.asList(carrefourJoint, carrefourPierre));

        HashMap<User, BigDecimal> carrefourMapping = new HashMap<>();
        carrefourMapping.put(paul, new BigDecimal(2));
        carrefourMapping.put(pierre, new BigDecimal(3.0));

        this.transactionService.shareTransaction(
            carrefour,
            carrefourMapping,
            paul
        );
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
