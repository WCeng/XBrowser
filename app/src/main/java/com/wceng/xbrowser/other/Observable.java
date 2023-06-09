package com.wceng.xbrowser.other;

import com.wceng.xbrowser.widget.Window;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer){
        observers.add(observer);
    }

    default void notifyAllObservers(Window window){
        for (Observer observer : observers){
            observer.onWindowUpdate(window);
        }
    }

    default void removeObserver(Observer observer){
        observers.remove(observer);
    }

}
