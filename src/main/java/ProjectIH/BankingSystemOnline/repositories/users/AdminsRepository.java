package ProjectIH.BankingSystemOnline.repositories.users;

import ProjectIH.BankingSystemOnline.models.accounts.users.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {
}
