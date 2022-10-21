package ProjectIH.BankingSystemOnline.models.accounts;

import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="currency", column=@Column (name="balance_currency")),
            @AttributeOverride(name="amount", column=@Column (name="balance_amount"))})
    private Money balance;

    @ManyToOne
    @JoinColumn (name = "primaryOwner")
    @NotNull
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn (name = "secondaryOwner")
    private AccountHolder secondaryOwner;
    private String secretKey;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name="currency", column=@Column (name="penaltyFee_currency")),
            @AttributeOverride(name="amount", column=@Column (name="penaltyFee_amount"))})
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40.00));

    private LocalDate lastChecked = LocalDate.now();


               //constructors, getters & setters:

    public Account() {}

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.secretKey = secretKey;

    }

    public Long getId() {return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getPenaltyFee() {return penaltyFee;}

    public LocalDate getLastChecked() {return lastChecked;}

    public void setLastChecked(LocalDate lastChecked) {this.lastChecked = lastChecked;}

    public String getSecretKey() {return secretKey;}

    public void setSecretKey(String secretKey) {this.secretKey = secretKey;}
}
