package com.pav.event;

import lc.kra.system.mouse.event.GlobalMouseEvent;
import lc.kra.system.mouse.event.GlobalMouseListener;

public class MouseHandler extends EventHandler<GlobalMouseEvent> implements GlobalMouseListener {
    public MouseHandler(final MouseObserver mouseObserver) {
        super(mouseObserver);
    }

    @Override
    public void mousePressed(GlobalMouseEvent event) {
        handle(event);
    }

    @Override
    public void mouseReleased(GlobalMouseEvent event) {
        handle(event);
    }

    @Override
    public void mouseMoved(GlobalMouseEvent event) {
        handle(event);
    }

    @Override
    public void mouseWheel(GlobalMouseEvent event) {
        handle(event);
    }
}
