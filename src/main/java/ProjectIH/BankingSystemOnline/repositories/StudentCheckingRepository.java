package ProjectIH.BankingSystemOnline.repositories;

import ProjectIH.BankingSystemOnline.models.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository <StudentChecking, Long> {
}
