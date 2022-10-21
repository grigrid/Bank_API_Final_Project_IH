package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.dto.AccountDTO;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.models.accounts.Checking;
import ProjectIH.BankingSystemOnline.models.accounts.StudentChecking;
import ProjectIH.BankingSystemOnline.repositories.StudentCheckingRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.repositories.CheckingRepository;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    public Account createCheckingAccount(AccountDTO accountDTO){
        if(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()){


            Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
            String secretKey = accountDTO.getSecretKey();
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;
            if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
                secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();

            }

            if(Period.between(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get().getDateOfBirth(), LocalDate.now()).getYears() < 24){

                StudentChecking studentChecking = new StudentChecking(balance, primaryOwner, secondaryOwner, secretKey);

                return studentCheckingRepository.save(studentChecking);


            } else {
                Checking checking = new Checking(balance, primaryOwner, secondaryOwner, secretKey);
                return checkingRepository.save(checking);

            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This ID from the AccountHolder does not exist");
    }
}