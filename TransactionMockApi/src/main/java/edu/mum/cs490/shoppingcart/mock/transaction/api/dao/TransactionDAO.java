package edu.mum.cs490.shoppingcart.mock.transaction.api.dao;

import edu.mum.cs490.shoppingcart.mock.transaction.api.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface TransactionDAO extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE (t.dstCardNo = ?1 AND t.payCash = true) or (t.srcCardNo = ?1 AND t.result = 1) ORDER BY t.id DESC")
    List<Transaction> getLastActiveTransaction(String srcCardNo);

    static <T> T getSingleResultOrNull(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
