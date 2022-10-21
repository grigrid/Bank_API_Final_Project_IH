package ProjectIH.BankingSystemOnline.repositories.users;

import ProjectIH.BankingSystemOnline.models.accounts.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository <ThirdParty, Long> {
}
