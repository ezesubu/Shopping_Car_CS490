package edu.mum.cs490.shoppingcart.utility.jpreport;

import edu.mum.cs490.shoppingcart.controller.ReportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
@Component
public class ReportCronJob {

    public ReportCronJob() {

    }


    @Autowired
    ReportController reportController;

//    @Autowired
//    IReportService reportService;

    public Date beginDateOfMonth() {
        Date endDate = new Date();
        Date beginDate = new Date(endDate.getYear(), endDate.getMonth() == 0 ? 11 : endDate.getMonth() - 1, endDate.getDay());
        return beginDate;
    }

    public Date beginDateOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date beginDate = calendar.getTime();
        return beginDate;
    }

    @Scheduled(cron=" 0 0 23  * * ?") //59 59 23 L * ?
//    @Scheduled(cron=" 0 0/15 * * * ?") //59 59 23 L * ?
    public void monthlyReport() {
        System.out.println("Every one minute");
//        reportController.sendReportToVendor(beginDateOfMonth());
        reportController.sendReportToVendor(beginDateOfMonth());

    }

        @Scheduled(cron = "0 0 0 * * SUN")
//    @Scheduled(cron = "0 0/15 * * * ?")
    public void weeklyReport() {
        System.out.println("Every one minute");
        reportController.sendReportToVendor(beginDateOfWeek());
    }


}
