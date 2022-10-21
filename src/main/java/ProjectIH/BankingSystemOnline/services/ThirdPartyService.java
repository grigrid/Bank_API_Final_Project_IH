package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;

@Service
public class ThirdPartyService {

    @Autowired
    AccountRepository accountRepository;


    public void sendMoneyTP (String hashedKey, BigDecimal amount, Long accountId, String secretKey) {
        if (accountRepository.findById(accountId).isPresent()) {
                if (accountRepository.findById(accountId).get().getSecretKey().equals(secretKey)) {

                    Account account = accountRepository.findById(accountId).get();

                    account.getBalance().setAmount(account.getBalance().getAmount().add(amount));
                    accountRepository.save(account);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The secretKey does not match");}
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id provided does not exist");
        }
    }

    public void receiveMoneyTP (String hashedKey, BigDecimal amount, Long accountId, String secretKey) {
        if(accountRepository.findById(accountId).isPresent()) {
            if (accountRepository.findById(accountId).get().getSecretKey().equals(secretKey)) {

                Account account = accountRepository.findById(accountId).get();

                account.getBalance().setAmount(account.getBalance().decreaseAmount(amount));
                accountRepository.save(account);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The secretKey does not match");}

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id provided does not exist");}
    }

}
