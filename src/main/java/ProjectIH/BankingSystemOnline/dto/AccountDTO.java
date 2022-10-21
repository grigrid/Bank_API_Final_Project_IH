package ProjectIH.BankingSystemOnline.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccountDTO {

    @NotNull
    private String balance;
    private String minimumBalance;
    private String interestRate;
    private String creditLimit;
    private String secretKey;


    private Long primaryOwnerId;
    private Long secondaryOwnerId;

    public AccountDTO(String balance, String minimumBalance, String interestRate, String creditLimit, String secretKey, LocalDate creationDate, Long primaryOwnerId, Long secondaryOwnerId) {
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }



    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(String minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }
}
