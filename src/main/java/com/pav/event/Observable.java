package com.pav.event;

import java.util.EventObject;

public interface Observable<T extends EventObject> {
    void addObserver(Observer<T> observer);

    void handle(T event);
}
