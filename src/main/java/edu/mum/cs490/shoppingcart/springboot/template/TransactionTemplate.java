package edu.mum.cs490.shoppingcart.springboot.template;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public abstract class TransactionTemplate {


    public final Integer process() {

        Integer resultCode = doTransaction();

        if (resultCode == 1) {
            notifyPurchase();

            transfer();
        }

        additionalAction();

        return resultCode;
    }

    protected abstract Integer doTransaction();

    protected abstract void notifyPurchase();

    protected abstract void transfer();

    protected void additionalAction() {
    }
}
