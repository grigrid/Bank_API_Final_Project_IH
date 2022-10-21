package ProjectIH.BankingSystemOnline.repositories;

import ProjectIH.BankingSystemOnline.classes.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenceRepository extends JpaRepository<Transference, Long> {
}
