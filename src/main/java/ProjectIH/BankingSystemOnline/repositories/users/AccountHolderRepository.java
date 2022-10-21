package ProjectIH.BankingSystemOnline.repositories.users;

import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    Optional<Object> findById(String id);
}
