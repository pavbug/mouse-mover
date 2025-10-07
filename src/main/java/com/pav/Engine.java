package com.pav;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.pav.activity.CursorMover;
import com.pav.event.EventInformation;
import com.pav.util.NumberUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Engine {
    private static final Duration CHECKING_PERIOD = Duration.of(1, ChronoUnit.SECONDS);
    private static final int DEFAULT_PERIOD_IN_SECONDS = 60;

    private final EventInformation eventInformation = new EventInformation();
    private final CursorMover cursorMover = new CursorMover();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.registerListeners();
        engine.runEventChecker(getDefaultPeriodInSeconds(args));
        Runtime.getRuntime().addShutdownHook(new Thread(engine::shutdown));
    }

    private static int getDefaultPeriodInSeconds(String[] args) {
        if (args == null || args.length == 0) {
            return DEFAULT_PERIOD_IN_SECONDS;
        }
        return NumberUtils.parseIntOrDefault(args[0], DEFAULT_PERIOD_IN_SECONDS);
    }

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

    public void runEventChecker(int periodInSeconds) {
        scheduler.scheduleAtFixedRate(() -> {
//            System.out.println("Last Event: " + eventInformation.getLastEventDateTime());
            if (eventInformation.isIdleLongerThan(CHECKING_PERIOD)) {
                cursorMover.moveFluentlyToDestinationAndBack(1, 1, 10);
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();  // Properly shuts down the executor
    }
}
