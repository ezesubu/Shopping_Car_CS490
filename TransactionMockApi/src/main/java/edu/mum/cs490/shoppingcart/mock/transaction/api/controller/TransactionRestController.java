package edu.mum.cs490.shoppingcart.mock.transaction.api.controller;

import edu.mum.cs490.shoppingcart.mock.transaction.api.service.DepositService;
import edu.mum.cs490.shoppingcart.mock.transaction.api.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@RestController
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private DepositService depositService;

    private final Logger logger = LogManager.getLogger(TransactionRestController.class);

    public TransactionRestController() {
        System.out.println("\n TransactionRestController has been initialized.\n");
    }

    @GetMapping("/")
    String home() {
        return "Welcome MOCK TRANSACTION API";
    }

    @PostMapping(value = "/mock/transaction/api")
    public ResponseEntity<String> doTransaction(@RequestBody String transactionRequestStr) {
        System.out.printf("### TransactionRestController %s() has been called ###\n", "doTransaction");
        System.out.println("TR - " + transactionRequestStr);
        return new ResponseEntity(transactionService.doTransaction(transactionRequestStr), HttpStatus.OK);
    }

    @PostMapping(value = "/mock/deposit/api")
    public ResponseEntity<String> doDeposit(@RequestBody String depositRequestStr) {
        System.out.printf("### TransactionRestController %s() has been called ###\n", "doDeposit");
        System.out.println("DR - " + depositRequestStr);
        return new ResponseEntity(depositService.doDeposit(depositRequestStr), HttpStatus.OK);
    }
}
