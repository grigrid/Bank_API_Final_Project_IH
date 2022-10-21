package ProjectIH.BankingSystemOnline.models.accounts;

import ProjectIH.BankingSystemOnline.Enums.Status;
import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Savings extends Account{

    private String secretKey;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="currency", column=@Column (name="minimumBalance_currency")),
            @AttributeOverride(name="amount", column=@Column (name="minimumBalance_amount"))})
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000.00));

    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate creationDate= LocalDate.now();;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private BigDecimal interestRate = new BigDecimal(0.0025, new MathContext(4, RoundingMode.HALF_UP));


                //constructors, getters & setters:

    public Savings() {}

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        this.interestRate = interestRate;

    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0) {
            this.interestRate = BigDecimal.valueOf(0.5);
        } else{
            this.interestRate = interestRate;
        }
    }

    public Money getMinimumBalance() {return minimumBalance;}

    public void setMinimumBalance(BigDecimal minimumBalance){
        if(minimumBalance != null && minimumBalance.compareTo(BigDecimal.valueOf(100.00))>0){
            this.minimumBalance.setAmount(minimumBalance);
        }
    }



    public void addInterestRate() {
        LocalDate lastchecked = this.getLastChecked();

        int timePassed = Period.between(lastchecked, LocalDate.now()).getYears();

        if(timePassed >= 1){
            this.setBalance(new Money(this.getBalance().increaseAmount(this.getBalance().getAmount().multiply(interestRate.multiply(new BigDecimal(timePassed))))));
            lastchecked = LocalDate.now();
        }
    }
    public void checkingMinimumBalance (Money balance){
        if(balance.getAmount().doubleValue() < minimumBalance.getAmount().doubleValue()) {
            System.err.println("The minimum balance can not be less than 1000. A penalty fee of 40 will be deducted automatically");
            balance.decreaseAmount(super.getPenaltyFee());
        }
        super.setBalance(balance);
    }

    public void setStatus(Status status) {this.status = status;}
}
