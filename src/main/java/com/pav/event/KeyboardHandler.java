package com.pav.event;

import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.keyboard.event.GlobalKeyListener;

public class KeyboardHandler extends EventHandler<GlobalKeyEvent> implements GlobalKeyListener {
    public KeyboardHandler(Observer<GlobalKeyEvent> observer) {
        super(observer);
    }

    @Override
    public void keyPressed(GlobalKeyEvent event) {
        handle(event);
    }

    @Override
    public void keyReleased(GlobalKeyEvent event) {
        handle(event);
    }
}
