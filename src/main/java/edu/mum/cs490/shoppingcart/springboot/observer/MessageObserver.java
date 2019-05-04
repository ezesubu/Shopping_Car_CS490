package edu.mum.cs490.shoppingcart.springboot.observer;

import java.util.Observable;
import java.util.Observer;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class MessageObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Send a message to the receivers");
    }
}
