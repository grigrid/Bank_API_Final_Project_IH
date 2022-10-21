package ProjectIH.BankingSystemOnline.models.accounts;

import ProjectIH.BankingSystemOnline.Enums.Status;
import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class StudentChecking extends Account{
    private String secretKey;

    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate creationDate= LocalDate.now();;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;



    public StudentChecking() {}


    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        this.secretKey = secretKey;
    }

    public void setStatus(Status status) {this.status = status;}
}
