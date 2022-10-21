package ProjectIH.BankingSystemOnline.classes;

import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name= "sendTransferenceList")
    AccountHolder moneySender;

    @ManyToOne
    @JoinColumn(name= "receiveTransferenceList")
    AccountHolder moneyReceiver;

    private final LocalDate transactionDate = LocalDate.now();

    public Transference() {}

    public Transference(AccountHolder moneySender, AccountHolder moneyReceiver) {
        this.moneySender = moneySender;
        this.moneyReceiver = moneyReceiver;
    }

    public Long getId() {
        return id;
    }

    public AccountHolder getMoneySender() {
        return moneySender;
    }

    public void setMoneySender(AccountHolder moneySender) {
        this.moneySender = moneySender;
    }

    public AccountHolder getMoneyReceiver() {
        return moneyReceiver;
    }

    public void setMoneyReceiver(AccountHolder moneyReceiver) {
        this.moneyReceiver = moneyReceiver;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
