package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.dto.AccountDTO;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    CheckingService checkingService;
    @Autowired
    CreditCardService creditCardService;
    @Autowired
    SavingsService savingsService;
    @Autowired
    AccountRepository accountRepository;

    public void createTypeAccount (String accountType, AccountDTO accountDTO){

        switch (accountType){
            case "checking" -> {
                checkingService.createCheckingAccount(accountDTO);
                break;
            }
            case "credit" -> {
                creditCardService.createCreditCardAccount(accountDTO);
                break;
            }
            case "savings" -> {
                savingsService.createSavingsAccount(accountDTO);
                break;
            }
            default -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The 3 types of accounts to choose are: Checking, CreditCard or Savings");
            }
        }
    }

    public void deleteAccount(Long id) {
        if (accountRepository.findById(id).isPresent()){
            accountRepository.delete(accountRepository.findById(id).get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This id does not exist");
        }
    }

    public List<Account> findAllAccounts (){
        return accountRepository.findAll();
    }
}
