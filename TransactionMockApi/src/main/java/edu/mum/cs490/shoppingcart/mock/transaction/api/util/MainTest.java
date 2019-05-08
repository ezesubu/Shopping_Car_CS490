package edu.mum.cs490.shoppingcart.mock.transaction.api.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mum.cs490.shoppingcart.mock.transaction.api.model.TransactionRequest;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class MainTest {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        //For testing
        TransactionRequest tr = new TransactionRequest();
        tr.setTxnId("" + System.currentTimeMillis());
        tr.setSrcCardNo("4929127657563699");
        tr.setExpirationDate("05/2020");
        tr.setNameOnCard("Thomas Tibebu");
        tr.setCVV("123");
        tr.setZipCode("52557");
        tr.setCardType("VISA");
        tr.setAmount(20000.0);
        tr.setDstCardNo("4000300020001000");

        try {
            //Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(tr);
            System.out.println(jsonInString);

            //Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tr);
            System.out.println(jsonInString);

            double dd = 1205;
            double bb = Math.round(dd*100.0)/100.0;
            System.out.println(dd);
            DecimalFormat df = new DecimalFormat("#.##");
            bb = Double.valueOf(df.format(dd));
            System.out.println(bb);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
