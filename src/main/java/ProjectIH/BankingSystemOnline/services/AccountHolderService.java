package ProjectIH.BankingSystemOnline.services;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import ProjectIH.BankingSystemOnline.repositories.*;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class AccountHolderService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransferenceRepository transferenceRepository;

    public AccountHolder createNewAccountHolder(AccountHolder accountHolder){
        return accountHolderRepository.save(accountHolder);
    }

    public Money accessBalance (Long accountHolderId, Long accountId){
        if(accountRepository.findById(accountId).isPresent()){
            Account account = accountRepository.findById(accountId).get();
            if(account.getPrimaryOwner().getId().equals(accountHolderId) || account.getSecondaryOwner().getId().equals(accountHolderId)){
                return account.getBalance();
            } throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This id does not match with the Owner");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This id does not match with the account");
        }

    }

    // Clarification in Transference: In order to simplify the project, we suppose that all the transaction are in EUR

    public void transferAmount (Money transactionAmount, Long senderId, Long receiverId, String username){

        if(accountRepository.findById(receiverId).isPresent()){
           Account receiverAccount = accountRepository.findById(receiverId).get();
            if(receiverAccount.getPrimaryOwner().getUsername().matches(username)|| receiverAccount.getSecondaryOwner().getUsername().matches(username)) {
                Account senderAccount;
                if (accountRepository.findById(senderId).isPresent()) {
                    senderAccount = accountRepository.findById(senderId).get();
                    if (senderAccount.getBalance().getAmount().compareTo(transactionAmount.getAmount()) > 0) {
                        receiverAccount.getBalance().setAmount(receiverAccount.getBalance().increaseAmount(transactionAmount));
                        accountRepository.save(receiverAccount);
                        senderAccount.getBalance().setAmount(senderAccount.getBalance().decreaseAmount(transactionAmount));
                        accountRepository.save(senderAccount);
                    }else {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are not enough funds");}
                } else {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The id from sender is not correct");}
            } else {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This is username does not exist");}
        } else {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This is id from the Owner does not exist");}
    }
}
