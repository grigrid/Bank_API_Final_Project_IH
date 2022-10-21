package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.dto.AccountDTO;
import ProjectIH.BankingSystemOnline.models.accounts.Savings;
import ProjectIH.BankingSystemOnline.repositories.SavingsRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;

@Service
public class SavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Savings createSavingsAccount (AccountDTO accountDTO){
        if(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()){

            Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;

            if(accountDTO.getSecondaryOwnerId() !=null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
            }
            String secretKey = accountDTO.getSecretKey();
            BigDecimal minimumBalance = new BigDecimal(accountDTO.getMinimumBalance());
            BigDecimal interestRate = new BigDecimal(accountDTO.getInterestRate());

            Savings savings = new Savings(balance, primaryOwner, secondaryOwner, secretKey, minimumBalance, interestRate);
            return savingsRepository.save(savings);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This ID from the AccountHolder does not exist");

    }
}
