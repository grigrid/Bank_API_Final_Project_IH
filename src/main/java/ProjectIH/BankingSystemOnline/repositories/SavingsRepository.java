package ProjectIH.BankingSystemOnline.repositories;

import ProjectIH.BankingSystemOnline.models.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository <Savings, Long> {
}
