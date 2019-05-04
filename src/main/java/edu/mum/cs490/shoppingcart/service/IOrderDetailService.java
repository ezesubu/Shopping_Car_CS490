package edu.mum.cs490.shoppingcart.service;

import java.util.*;

import edu.mum.cs490.shoppingcart.domain.*;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Transactional(readOnly = true)
public interface IOrderDetailService {

	@Transactional
	OrderDetail getOne(Integer id);

	@Transactional
	List<OrderDetail> findByDate(Date begin_Date, Date end_Date);

	@Transactional
	List<OrderDetail> findByVendor_Id(List<Integer> vendor_Ids, Date begin_Date, Date end_Date);

	@Transactional
	List<OrderDetail> findByCategory_Id(List<Integer> category_Ids, Date begin_Date, Date end_Date);

	@Transactional
	List<OrderDetail> findByVendor_IdAndCategory_Id(List<Integer> vendor_Ids, List<Integer> category_Ids, Date begin_Date, Date end_Date);
}
