package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.dto.AccountDTO;
import ProjectIH.BankingSystemOnline.models.accounts.CreditCard;
import ProjectIH.BankingSystemOnline.repositories.CreditCardRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public CreditCard createCreditCardAccount (AccountDTO accountDTO){
        if(accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).isPresent()){

            Money balance = new Money(new BigDecimal(accountDTO.getBalance()));
            AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;
            if(accountDTO.getSecondaryOwnerId() !=null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()) {
                secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
            }
            BigDecimal interestRate = new BigDecimal(accountDTO.getInterestRate());
            Money creditLimit = new Money(new BigDecimal(accountDTO.getCreditLimit()));
            CreditCard creditCard = new CreditCard(balance, primaryOwner, secondaryOwner, interestRate, creditLimit);
            return creditCardRepository.save(creditCard);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This ID from the AccountHolder does not exist");

    }


}
