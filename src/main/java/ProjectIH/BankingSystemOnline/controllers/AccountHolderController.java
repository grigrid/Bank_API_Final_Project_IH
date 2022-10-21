package ProjectIH.BankingSystemOnline.controllers;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import ProjectIH.BankingSystemOnline.services.AccountHolderService;
import ProjectIH.BankingSystemOnline.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountHolderController {
    @Autowired
    AccountHolderService accountHolderService;

    @PostMapping("/create-account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody AccountHolder accountHolder){
        return accountHolderService.createNewAccountHolder(accountHolder);
    }


    @GetMapping("/access_balance/{accountHolderId}/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Money accessBalance (@PathVariable Long accountHolderId, @PathVariable Long accountId) {
        return accountHolderService.accessBalance(accountHolderId, accountId);
    }

    //  Account holders will be able to transfer any amount(if sufficient funds) to any account

    @PatchMapping("/transferMoneyAccountHolder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void transferMoney (@RequestBody Money transactionAmount, @RequestParam Long senderId, @RequestParam Long receiverId, @RequestParam String username){
        accountHolderService.transferAmount(transactionAmount, senderId, receiverId, username);
    }
}
