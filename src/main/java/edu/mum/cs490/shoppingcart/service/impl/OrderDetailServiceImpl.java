package edu.mum.cs490.shoppingcart.service.impl;

import edu.mum.cs490.shoppingcart.domain.OrderDetail;
import edu.mum.cs490.shoppingcart.repository.OrderDetailRepository;
import edu.mum.cs490.shoppingcart.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	private final OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public List<OrderDetail> findByDate(Date begin_Date, Date end_Date) {
		return orderDetailRepository.findByDate(begin_Date, end_Date);
	}

	@Override
	public List<OrderDetail> findByVendor_Id(List<Integer> vendor_Ids, Date begin_Date, Date end_Date) {
		return orderDetailRepository.findByVendor_Id(vendor_Ids, begin_Date, end_Date);
	}

	@Override
	public List<OrderDetail> findByCategory_Id(List<Integer> category_Ids, Date begin_Date, Date end_Date) {
		return orderDetailRepository.findByCategory_Id(category_Ids, begin_Date, end_Date);
	}

	@Override
	public List<OrderDetail> findByVendor_IdAndCategory_Id(List<Integer> vendor_Ids, List<Integer> category_Ids, Date begin_Date, Date end_Date) {
		return orderDetailRepository.findByVendor_IdAndCategory_Id(vendor_Ids, category_Ids, begin_Date, end_Date);
	}
}
