package ProjectIH.BankingSystemOnline.controllers;

import ProjectIH.BankingSystemOnline.services.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;



//In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request.
// They also must provide the amount, the Account id and the account secret key.

    @GetMapping("/sendMoneyTP")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMoneyTP (@RequestHeader String hashedKey, @RequestParam BigDecimal amount,
                             @RequestParam Long accountId, @RequestParam String secretKey){

        thirdPartyService.sendMoneyTP(hashedKey, amount, accountId, secretKey);
    }

    @GetMapping("/receiveMoneyTP")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void receiveMoneyTP (@RequestHeader String hashedKey, @RequestParam BigDecimal amount,
                             @RequestParam Long accountId, @RequestParam String secretKey){
        thirdPartyService.receiveMoneyTP(hashedKey, amount, accountId, secretKey);
    }

}
