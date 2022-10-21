package ProjectIH.BankingSystemOnline.models.accounts.users;

import javax.persistence.Entity;
import java.util.Set;

@Entity

public class Admins extends User{

    public Admins() {}

    public Admins(String username, String password) {
        super(username, password);
    }


}
