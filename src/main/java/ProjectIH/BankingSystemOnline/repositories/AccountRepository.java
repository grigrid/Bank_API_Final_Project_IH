package ProjectIH.BankingSystemOnline.repositories;

import ProjectIH.BankingSystemOnline.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
