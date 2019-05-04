package edu.mum.cs490.shoppingcart.springboot.observer;

import java.util.Observable;
/**
 * Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
 * Innocent Kateba, shuling he, Wenxin He, Tram Ly
 * Date April 20, 2019
 **/
public class TransferSubject extends Observable {
    public void doNotify() {
        setChanged();
        notifyObservers();
    }
}
