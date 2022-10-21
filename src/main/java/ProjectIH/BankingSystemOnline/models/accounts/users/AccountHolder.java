package ProjectIH.BankingSystemOnline.models.accounts.users;

import ProjectIH.BankingSystemOnline.classes.Address;
import ProjectIH.BankingSystemOnline.classes.Transference;
import ProjectIH.BankingSystemOnline.models.accounts.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity

public class AccountHolder extends User {

    @NotNull
    //@JsonFormat(pattern= "YYYY-MM-DD")
    private LocalDate dateOfBirth;

    @NotNull
    @Embedded
    private Address primaryAddress;

    @OneToMany (mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccountList = new ArrayList<>();

    @OneToMany (mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccountList =new ArrayList<>();

    @OneToMany (mappedBy = "moneySender")
    @JsonIgnore
    private List<Transference> sendTransferenceList = new ArrayList<>();

    @OneToMany (mappedBy = "moneyReceiver")
    @JsonIgnore
    private List<Transference> receiveTransferenceList = new ArrayList<>();



                                //constructors, getters & setters:


    public AccountHolder() {}

    public AccountHolder(String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;

    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public List<Account> getSecondaryAccountList() {
        return secondaryAccountList;
    }

    public void setSecondaryAccountList(List<Account> secondaryAccountList) {
        this.secondaryAccountList = secondaryAccountList;
    }

    public List<Transference> getSendTransferenceList() {
        return sendTransferenceList;
    }

    public void setSendTransferenceList(Transference transference) {
        this.sendTransferenceList.add(transference);
    }

    public List<Transference> getReceiveTransferenceList() {
        return receiveTransferenceList;
    }

    public void setReceiveTransferenceList(List<Transference> receiveTransferenceList) {
        this.receiveTransferenceList = receiveTransferenceList;
    }
}
