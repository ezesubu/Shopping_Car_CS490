package edu.mum.cs490.shoppingcart.mock.transaction.api.dao;

import edu.mum.cs490.shoppingcart.mock.transaction.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface AccountDAO extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE "
            + "a.cardNo = ?1 and "
            + "a.name = ?2 and "
            + "a.zipCode = ?3 and "
            + "a.CVV = ?4 and "
            + "a.expirationDate = ?5 and "
            + "a.cardType = ?6")
    Account findByCardNoAndNameAndZipCodeAndCVVAndExpirationDateAndCardType(String cardNo, String name, String zipCode, String CVV, String expirationDate, String cardType);

    Account findByCardNo(String cardNo);

}
