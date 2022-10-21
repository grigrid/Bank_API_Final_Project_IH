package ProjectIH.BankingSystemOnline;

import ProjectIH.BankingSystemOnline.classes.Address;
import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.Checking;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import ProjectIH.BankingSystemOnline.models.accounts.users.Admins;
import ProjectIH.BankingSystemOnline.models.accounts.users.Role;
import ProjectIH.BankingSystemOnline.models.accounts.users.ThirdParty;
import ProjectIH.BankingSystemOnline.repositories.AccountRepository;
import ProjectIH.BankingSystemOnline.repositories.RoleRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AdminsRepository;
import ProjectIH.BankingSystemOnline.repositories.users.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BankingSystemOnlineApplication implements CommandLineRunner {

	@Autowired
	AccountHolderRepository accountHolderRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AdminsRepository adminsRepository;
	@Autowired
	ThirdPartyRepository thirdPartyRepository;
	@Autowired
	RoleRepository  roleRepository;



	public static void main(String[] args) {
		SpringApplication.run(BankingSystemOnlineApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Address address1 = new Address("Paseo de Gracia 25", "Barcelona", 8056, "Spain");

		AccountHolder accountHolder1 = new AccountHolder("Ingrid", "asd56#", LocalDate.of(2000,11,21), address1);
		AccountHolder accountHolder2 = new AccountHolder("Pepe", "a00006#", LocalDate.of(2000,10,30), address1);
		Role role1 = new Role("USER", accountHolder1);
		Role role2 = new Role("USER", accountHolder2);
		accountHolderRepository.save(accountHolder1);
		accountHolderRepository.save(accountHolder2);
		roleRepository.saveAll(List.of(role1, role2));


		Money balance = new Money(new BigDecimal(5000));
		Checking checking1 = new Checking(balance, accountHolder1, accountHolder2, "aaa");
		accountRepository.save(checking1);

		Admins admin = adminsRepository.save(new Admins("Susi", "1234"));
		Role role3 = new Role("ADMIN", admin);
		adminsRepository.save(admin);
		roleRepository.save(role3);

		ThirdParty tP = new ThirdParty("tp123", "asd", "sdd");
		thirdPartyRepository.save(tP);


	}
}
