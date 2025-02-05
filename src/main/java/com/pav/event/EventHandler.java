package com.pav.event;

import java.util.EventObject;

public abstract class EventHandler<T extends EventObject> implements Observable<T>{
    protected Observer<T> observer;
    
    public EventHandler(final Observer<T> observer) {
        addObserver(observer);
    }

    @Override
    public void addObserver(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void handle(T event) {
        observer.update(event);
    }
}
