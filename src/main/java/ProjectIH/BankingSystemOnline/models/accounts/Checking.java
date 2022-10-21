package ProjectIH.BankingSystemOnline.models.accounts;

import ProjectIH.BankingSystemOnline.Enums.Status;
import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account {

    @Embedded
    @AttributeOverrides({@AttributeOverride(name="currency", column=@Column (name="minimumBalance_currency")),
            @AttributeOverride(name="amount", column=@Column (name="minimumBalance_amount"))})
    private final Money minimumBalance = new Money(BigDecimal.valueOf(250.00));
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="currency", column=@Column (name="monthlyMaintenanceFee_currency")),
            @AttributeOverride(name="amount", column=@Column (name="monthlyMaintenanceFee_amount"))})
    private final Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12.00));
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate creationDate= LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;


                        //constructors, getters & setters:


    public Checking() {}

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,  String secretKey) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
    }

    public Money getMinimumBalance() {return minimumBalance;}

    public void setStatus(Status status) {this.status = status;}

    public void checkingMinimumBalance (Money balance){
        if(balance.getAmount().doubleValue() < minimumBalance.getAmount().doubleValue()) {
            System.err.println("The minimum balance can not be less than 250. A penalty fee of 40 will be deducted automatically");
            balance.decreaseAmount(super.getPenaltyFee());
        }
        super.setBalance(balance);
    }


}
