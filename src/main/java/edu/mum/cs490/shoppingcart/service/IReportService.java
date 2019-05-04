package edu.mum.cs490.shoppingcart.service;

import edu.mum.cs490.shoppingcart.domain.OrderDetail;
import edu.mum.cs490.shoppingcart.utility.jpreport.DataSourceReport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/

@Transactional(readOnly = true)
public interface IReportService {
	public List<OrderDetail> getReportResultList(List<Integer> lstVendorId, List<Integer> lstCategoryId, Date begin_Date, Date end_Date);

	public void jasperReportFill(String jrName, DataSourceReport dataSourceReport);

	public byte[] exportToPdf(JasperPrint jasperPrint) throws JRException;
}
