# Bank-API

This final project tries to build a banking system following these requirements below.

<h3> Requirements: </h3>

The system must have 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard and 3 types of Users: Admins, AccountHolders and ThirdParty. 
Each of them have the following attributes:

![](../Scheme of Type Users and Accounts.png)

Considerations that has been taken in function of the account are:

* StudentChecking account will be only for young people until 24 years old.
* Savings account have a default interest rate of 0.0025, should have a default minimumBalance of 1000 and may be instantiated with a minimum balance of less than 1000 but no lower than 100.
* CreditCard account have a default creditLimit of 100, may be instantiated with a creditLimit higher than 100 but not higher than 100000, have a default interestRate of 0.2 and may be instantiated with an interestRate less than 0.2 but not lower than 0.1.
* Checking account should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12.
* if any account drops below the minimumBalance, the penaltyFee (40) should be deducted from the balance automatically.
* I would also like to point out that we have considered the example as a European Bank and all the transactions are done in EUR.

<h3> Use Case Diagram: </h3>

![img_1.png](img_1.png)


<h3> Functionalities according to each role: </h3>

Admins:
    
    Admins can create new accounts and they should be able to access the balance for any account and to modify it.
        
        "/all_accounts"
    
        "/createTypeAccount/{accountType}"
    
        "/new_checking"
    
        "/new_creditCard"
    
        "/new_savings"
    
        "/modifyBalance"
    
        "/FreezeAccount"
    
        "/deleteAccount"
    
        And third-party users must be added to the database by an admin.
    
        "/newThirdParty"


AccountHolders:

    AccountHolders should be able to access their own account balance and they should be able to transfer money from any of their accounts to any other account (regardless of owner). 
    The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
    
        "/create-account-holder"
    
        "/access_balance/{accountHolderId}/{accountId}"
    
        "/transferMoneyAccountHolder"

Third-Party:

    There must be a way for third-party users to receive and send money to other accounts.
    In order to do it, they must provide their hashed key in the header of the HTTP request. And they also must provide the amount, 
    the Account Id and the account secret key.

        "/sendMoneyTP"
    
        "/receiveMoneyTP"


Created on the 22nd October 2022