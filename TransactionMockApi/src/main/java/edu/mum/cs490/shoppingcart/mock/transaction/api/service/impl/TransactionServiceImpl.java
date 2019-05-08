package edu.mum.cs490.shoppingcart.mock.transaction.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.shoppingcart.mock.transaction.api.dao.AccountDAO;
import edu.mum.cs490.shoppingcart.mock.transaction.api.dao.TransactionDAO;
import edu.mum.cs490.shoppingcart.mock.transaction.api.entity.Account;
import edu.mum.cs490.shoppingcart.mock.transaction.api.entity.Transaction;
import edu.mum.cs490.shoppingcart.mock.transaction.api.model.TransactionRequest;
import edu.mum.cs490.shoppingcart.mock.transaction.api.service.TransactionService;
import java.io.IOException;
import java.security.InvalidParameterException;

import edu.mum.cs490.shoppingcart.mock.transaction.api.util.AES;
import edu.mum.cs490.shoppingcart.mock.transaction.api.util.DoubleRoundUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;


/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service
@RequestScope
public class TransactionServiceImpl implements TransactionService {

    public TransactionServiceImpl() {
        System.out.println("\nTransactionServiceImpl has been initialized.\n");
    }

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    @Qualifier("AES")
    private AES aes;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    @Override
    public String doTransaction(String requestStr) {
        TransactionRequest tr;
        try {
            tr = mapper.readValue(requestStr, TransactionRequest.class);
            tr.validate();
        } catch (IOException | NullPointerException | InvalidParameterException ex) {
            logger.error("", ex);
            logger.error("Invalid request");
            return "400";
        }
        Account account = new Account(tr.getSrcCardNo(), tr.getExpirationDate(), tr.getNameOnCard().toUpperCase(), tr.getCVV(), tr.getZipCode(), tr.getCardType().toUpperCase());

        Integer resultCode = 0;
        double availableAmount = 0.0;
        double usedAmount = 0.0;

        // To get an account from db
        logger.info("Get the source account from the DB.");
        Account srcAccount = accountDAO.findByCardNoAndNameAndZipCodeAndCVVAndExpirationDateAndCardType(aes.encrypt(account.getCardNo()),
                aes.encrypt(account.getName()),
                aes.encrypt(account.getZipCode()),
                aes.encrypt(account.getCVV()),
                aes.encrypt(account.getExpirationDate()),
                aes.encrypt(account.getCardType()).toUpperCase());

        if (srcAccount == null) {
            // Not found the account
            logger.info("Not found the account.");
            resultCode = 2;
        } else {
            availableAmount = srcAccount.getAmount();
            if (srcAccount.getAmount() < tr.getAmount()) {
                // NOT enough amount
                logger.info("NOT enough amount");
                resultCode = 3;
            } else {
                // ENOUGH amount
                resultCode = 1;
            }
        }
        if (resultCode == 1 || resultCode == 3) {
            // To get last active transaction from db
            logger.info("Get last transaction from the DB");
            Transaction lastTransaction = TransactionDAO.<Transaction>getSingleResultOrNull(transactionDAO.getLastActiveTransaction(srcAccount.getCardNo()));
            if (lastTransaction != null) {
                usedAmount = lastTransaction.getUsedAmount();
//                refreshTransaction(lastTransaction);
//                transactionDAORepository.detach(lastTransaction);
            }
        }
        if (resultCode == 1) {
            // Deduction from source account
            availableAmount = srcAccount.getAmount() - tr.getAmount();
            usedAmount += tr.getAmount();
            srcAccount.setAmount(DoubleRoundUp.roundUp(availableAmount));
            accountDAO.save(srcAccount);
            logger.info("The source account has been updated.");

            // Credit to destination account
            Account dstAccount = accountDAO.findByCardNo(aes.encrypt(tr.getDstCardNo()));
            if (dstAccount == null) {
                dstAccount = new Account();
                dstAccount.setCardNo(tr.getDstCardNo());
                dstAccount.setAmount(0.0);
            }
            dstAccount.setAmount(DoubleRoundUp.roundUp(dstAccount.getAmount() + tr.getAmount()));
            logger.info("The destination account has been updated.");
            accountDAO.save(dstAccount);

        } else {
//            if (srcAccount != null)
//            srcAccount = refreshAccount(srcAccount);
        }
        Transaction newTransaction = new Transaction(
                tr.getSrcCardNo(),
                tr.getCardType(),
                tr.getDstCardNo(),
                DoubleRoundUp.roundUp(tr.getAmount()),
                DoubleRoundUp.roundUp(availableAmount),
                DoubleRoundUp.roundUp(usedAmount),
                resultCode,
                tr.getTxnId());
        refreshTransaction(newTransaction);
        transactionDAO.save(newTransaction);
        logger.info("New transaction has been inserted. Result of transaction request - " + (resultCode == 1) + "(" + resultCode + ")");
        return resultCode.toString();
    }

    @Override
    public Account refreshAccount(Account account) {
        account.setCardNo(aes.encrypt(account.getCardNo()));
        if (account.getName() != null) {
            account.setName(aes.encrypt(account.getName()));
        }
        if (account.getZipCode() != null) {
            account.setZipCode(aes.encrypt(account.getZipCode()));
        }
        if (account.getCVV() != null) {
            account.setCVV(aes.encrypt(account.getCVV()));
        }
        if (account.getExpirationDate() != null) {
            account.setExpirationDate(aes.encrypt(account.getExpirationDate()));
        }
        if (account.getCardType() != null) {
            account.setCardType(aes.encrypt(account.getCardType()));
        }
        return account;
    }


    public Transaction refreshTransaction(Transaction transaction) {
        if (transaction.getSrcCardNo() != null) {
            transaction.setSrcCardNo(aes.encrypt(transaction.getSrcCardNo()));
        }
        if (transaction.getSrcCardType() != null) {
            transaction.setSrcCardType(aes.encrypt(transaction.getSrcCardType()));
        }
        if (transaction.getDstCardNo() != null) {
            transaction.setDstCardNo(aes.encrypt(transaction.getDstCardNo()));
        }
        return transaction;
    }
}
