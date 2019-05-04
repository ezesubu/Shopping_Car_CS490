package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.CardDetail;
import edu.mum.cs490.shoppingcart.domain.Status;
import edu.mum.cs490.shoppingcart.domain.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface IVendorService extends IUserService<Vendor> {

    List<Vendor> find(String username, String companyName, Status status);

    Page<Vendor> findPage(String username, String companyName, Status status, Pageable pageable);

    Vendor getByCompanyName(String companyName);

    @Transactional
    Integer transferFee(CardDetail vendorCardDetail, Vendor vendor);
}
