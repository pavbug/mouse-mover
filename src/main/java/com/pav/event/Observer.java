package com.pav.event;

import java.util.EventObject;

public interface Observer<T extends EventObject> {
    void update(T event);
}
