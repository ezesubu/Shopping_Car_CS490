package edu.mum.cs490.shoppingcart.utility;

import java.text.DecimalFormat;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class DoubleRoundUpUtility {

    private static DecimalFormat df = new DecimalFormat("#.##");

    public static Double roundUp(Double d) {
        if (d != null) {
            return Double.valueOf(df.format(d));
        } else {
            return d;
        }
    }
}
