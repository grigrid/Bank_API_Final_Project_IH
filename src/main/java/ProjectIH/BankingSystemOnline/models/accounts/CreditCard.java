package ProjectIH.BankingSystemOnline.models.accounts;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity

public class CreditCard extends Account{

    private BigDecimal interestRate = new BigDecimal(0.2, new MathContext(4, RoundingMode.HALF_UP));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_currency"))})
    private Money creditLimit= new Money(BigDecimal.valueOf(100.00));


              //constructors, getters & setters:

    public CreditCard() {}


    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner, null);
        this.interestRate = interestRate;
        this.setCreditLimit(creditLimit);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.valueOf(0.1))<0){
            this.interestRate = BigDecimal.valueOf(0.1);
        } else if (interestRate.compareTo(BigDecimal.valueOf(0.2))>0){
            this.interestRate = BigDecimal.valueOf(0.2);
        } else {
            this.interestRate = interestRate;
        }
    }

    public Money getCreditLimit() { return creditLimit;}

    public void setCreditLimit(Money creditLimit) {
        if(creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000))>0){
            this.creditLimit.setAmount(BigDecimal.valueOf(100000.00));
        } else {
            this.creditLimit = creditLimit;
        }
    }

    public void addInterestRate() {
        LocalDate lastchecked = this.getLastChecked();

        int timePassed = Period.between(lastchecked, LocalDate.now()).getMonths();

        if(timePassed >= 1){
            this.setBalance(new Money(this.getBalance().increaseAmount(this.getBalance().getAmount().multiply(interestRate.multiply(new BigDecimal(timePassed))))));
            lastchecked = LocalDate.now();
        }
    }

}
