//package edu.mum.cs490.shoppingcart.aop;
//
//import edu.mum.cs490.shoppingcart.utility.EmailUtility;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Properties;
//
///**
// * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
// * Innocent Kateba, shuling he, Wenxin He, Tram Ly
// * Date April 20, 2019
// **/
//@Aspect
//@Component
//public class EmailAOPService {
////
////    @Autowired
////    private EmailUtility emailUtil;
////
////    private static Properties propertiesOfMail = new Properties();
////    private static int delay = 0;
////
//////    static{
//////        try {
//////            //load a properties file from class path, inside static method
//////            propertiesOfMail.load(EmailUtil.class.getClassLoader().getResourceAsStream("mail.properties"));
//////        }
//////        catch (IOException ex) {
//////            ex.printStackTrace();
//////        }
//////    }
////
////    @AfterThrowing("execution(* edu.mum.cs490.shoppingcart.utility.EmailUtility.sendEmail(..)) && args(toEmail, subject, body)")
////    public void aopResendEmail(String toEmail, String subject, String body){
////        try {
////            if(delay == 60)
////            {
////                delay = 0;
////                return;
////            }
////            delay = delay == 0 ? 5 : delay == 5 ? 30 : 60;
////            Thread.sleep(delay * 60000);
////            emailUtil.sendEmail(toEmail, subject, body);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
////
////    @AfterThrowing("execution(* edu.mum.cs490.shoppingcart.utility.EmailUtility.sendEmailWithAttachment(..)) && args(toEmail, subject, body, attachmentFile, nameOfAttachment)")
////    public void aopResendEmailWithAttachment(String toEmail, String subject, String body, byte[] attachmentFile, String nameOfAttachment){
////        try {
////            if(delay == 60)
////            {
////                delay = 0;
////                return;
////            }
////            delay = delay == 0 ? 5 : delay == 5 ? 30 : 60;
////            Thread.sleep(delay * 60000);
////            emailUtil.sendEmailWithAttachment(toEmail, subject, body, attachmentFile, nameOfAttachment);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//}
