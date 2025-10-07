package com.pav;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.pav.activity.CursorMover;
import com.pav.event.EventInformation;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Engine {
    private static final Duration CHECKING_PERIOD = Duration.of(3, ChronoUnit.SECONDS);

    private final EventInformation eventInformation = new EventInformation();
    private final CursorMover cursorMover = new CursorMover();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void registerListeners() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(eventInformation);
        GlobalScreen.addNativeMouseListener(eventInformation);
        GlobalScreen.addNativeMouseMotionListener(eventInformation);
    }

    public void runEventChecker() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Last Event: " + eventInformation.getLastEventDateTime());
            if (eventInformation.isIdleLongerThan(CHECKING_PERIOD)) {
                cursorMover.moveFluentlyToDestinationAndBack(1, 1, 10);
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();  // Properly shuts down the executor
    }

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.registerListeners();
        engine.runEventChecker();
        Runtime.getRuntime().addShutdownHook(new Thread(engine::shutdown));
    }
}
