package com.pav.event;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

public class EventInformation implements NativeKeyListener, NativeMouseInputListener {
    private AtomicReference<LocalDateTime> lastEventDateTime = new AtomicReference<>(LocalDateTime.now());

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
        updateLastEventDateTime();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
        updateLastEventDateTime();
    }

    public boolean isIdleLongerThan(Duration duration) {
        return Duration.between(getLastEventDateTime(), LocalDateTime.now()).compareTo(duration) > 0;
    }

    public LocalDateTime getLastEventDateTime() {
        return lastEventDateTime.get();
    }

    private void updateLastEventDateTime() {
        lastEventDateTime.set(LocalDateTime.now());
    }
}
