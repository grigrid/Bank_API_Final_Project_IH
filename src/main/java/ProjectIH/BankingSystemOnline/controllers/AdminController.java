package ProjectIH.BankingSystemOnline.controllers;

import ProjectIH.BankingSystemOnline.dto.AccountDTO;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.models.accounts.CreditCard;
import ProjectIH.BankingSystemOnline.models.accounts.Savings;
import ProjectIH.BankingSystemOnline.models.accounts.users.ThirdParty;
import ProjectIH.BankingSystemOnline.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    CheckingService checkingService;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    SavingsService savingsService;
    @Autowired
    AdminService adminService;
    @Autowired
    AccountService accountService;
    @Autowired
    ThirdPartyService thirdPartyService;

    @GetMapping("/all_accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts(){
        return accountService.findAllAccounts();
    }

    @PostMapping("/createTypeAccount/{accountType}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTypeAccount(@PathVariable String accountType, @RequestBody AccountDTO accountDTO){
        accountService.createTypeAccount(accountType, accountDTO);
    }

      // Admin can create a checking account. But it will create a student checking account in case that the holder is 24 years old or lower
    @PostMapping("/new_checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody AccountDTO accountDTO){
        return checkingService.createCheckingAccount(accountDTO);
    }

    @PostMapping("/new_creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount (@RequestBody AccountDTO accountDTO){
        return creditCardService.createCreditCardAccount(accountDTO);
    }

    @PostMapping("/new_savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createSavingsAccount (@RequestBody AccountDTO accountDTO){
        return savingsService.createSavingsAccount(accountDTO);
    }

    @PatchMapping("/modifyBalance")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBalance (@RequestParam BigDecimal balance, @RequestParam Long id){
        adminService.modifyBalance(id, balance);
    }
    @PatchMapping("/FreezeAccount")
    @ResponseStatus(HttpStatus.OK)
    public void freezeAccount(@RequestParam Long id){
        adminService.freezeAccount(id);
    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount (@RequestParam Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/newThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public void createThirdParty (@RequestBody ThirdParty thirdParty){
        adminService.createThirdParty(thirdParty);
    }


}
