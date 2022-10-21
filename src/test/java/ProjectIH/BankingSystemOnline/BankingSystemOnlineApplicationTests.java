package ProjectIH.BankingSystemOnline;

import ProjectIH.BankingSystemOnline.classes.Address;
import ProjectIH.BankingSystemOnline.classes.Money;
import ProjectIH.BankingSystemOnline.models.accounts.Checking;
import ProjectIH.BankingSystemOnline.models.accounts.users.AccountHolder;
import ProjectIH.BankingSystemOnline.models.accounts.users.Admins;
import ProjectIH.BankingSystemOnline.models.accounts.users.Role;
import ProjectIH.BankingSystemOnline.models.accounts.users.ThirdParty;
import ProjectIH.BankingSystemOnline.repositories.AccountRepository;
import ProjectIH.BankingSystemOnline.repositories.CheckingRepository;
import ProjectIH.BankingSystemOnline.repositories.RoleRepository;
import ProjectIH.BankingSystemOnline.repositories.users.AccountHolderRepository;
import ProjectIH.BankingSystemOnline.repositories.users.ThirdPartyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BankingSystemOnlineApplicationTests {

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CheckingRepository checkingRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ThirdPartyRepository thirdPartyRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;



	AccountHolder accountHolder1;
	Checking checking;


	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@BeforeEach
	public void setUp() {mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		Address address = new Address("C/ Gras 25", "Barcelona", 8006, "Spain");
		accountHolder1 = new AccountHolder("Laia", "a5#", LocalDate.of(2000,11,21), address);
		checking = new Checking(new Money(BigDecimal.valueOf(5000)), accountHolder1, accountHolder1, "aaa");


		accountHolderRepository.save(accountHolder1);
		accountRepository.save(checking);
		}

	@Test
	void get_accessBalance_OK() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/access_balance/1/1")).andExpect(status().isOk()).andReturn();
		assertTrue(mvcResult.getResponse().getContentAsString().contains("5000"));

		System.out.println(mvcResult.getResponse().getContentAsString());

	}





	@Test
	void post_createAccountHolder_CREATED() throws Exception {

		Address address = new Address("Calle Marina 25", "Cambrils", 44006, "Spain");
		accountHolder1 = new AccountHolder("Elena", "a5#", LocalDate.of(1980,11,21), address);
		String body =objectMapper.writeValueAsString(accountHolder1);

		MvcResult mvcResult = mockMvc.perform(post("/create-account-holder").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
		assertTrue(mvcResult.getResponse().getContentAsString().contains("Cambrils"));



	}




}
