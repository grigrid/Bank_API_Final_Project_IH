package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.Enums.Status;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.models.accounts.Checking;
import ProjectIH.BankingSystemOnline.models.accounts.Savings;
import ProjectIH.BankingSystemOnline.models.accounts.StudentChecking;
import ProjectIH.BankingSystemOnline.models.accounts.users.ThirdParty;
import ProjectIH.BankingSystemOnline.repositories.AccountRepository;
import ProjectIH.BankingSystemOnline.repositories.CheckingRepository;
import ProjectIH.BankingSystemOnline.repositories.SavingsRepository;
import ProjectIH.BankingSystemOnline.repositories.StudentCheckingRepository;
import ProjectIH.BankingSystemOnline.repositories.users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;



    public void modifyBalance(Long id, BigDecimal balance) {

        if (accountRepository.findById(id).isPresent()) {
            Account account = accountRepository.findById(id).get();
            account.getBalance().setAmount(balance);
            accountRepository.save(account);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "It does not exist an account with this id");
        }
    }
    public void freezeAccount (Long id) {
        if(accountRepository.findById(id).isPresent()){
            Optional<Checking> checking = checkingRepository.findById(id);
            Optional<StudentChecking> studentChecking = studentCheckingRepository.findById(id);
            Optional<Savings> savings = savingsRepository.findById(id);
            if(checking.isPresent()) {
                checking.get().setStatus(Status.FROZEN);
                checkingRepository.save(checking.get());
            } else if (studentChecking.isPresent()){
                studentChecking.get().setStatus(Status.FROZEN);
                studentCheckingRepository.save(studentChecking.get());
            } else if (savings.isPresent()){
                savings.get().setStatus(Status.FROZEN);
                savingsRepository.save(savings.get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "It does not exist an account with this id");
        }
    }

    public void createThirdParty (ThirdParty thirdParty){

        thirdPartyRepository.save(thirdParty);
    }
}
