package edu.mum.cs490.shoppingcart.mock.transaction.api.util;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public interface AES {

    public String encrypt(String strToEncrypt);

    public String decrypt(String strToDecrypt);

    public String getSecretKeyWord();
}
