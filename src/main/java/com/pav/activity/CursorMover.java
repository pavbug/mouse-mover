package com.pav.activity;

import java.awt.*;

public class CursorMover {
    private final Robot robot;

    public CursorMover() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveFluentlyToDestinationAndBack(int distanceX, int distanceY, int sleepDuration) {
        Point currentPoint = getCurrentPoint();
        moveFluentlyToDestination(distanceX, distanceY, sleepDuration);
        moveFluentlyTo(currentPoint, sleepDuration);
    }

    public void moveFluentlyToDestination(int distanceX, int distanceY, int sleepDuration) {
        moveFluentlyTo(getDestinationPoint(distanceX, distanceY), sleepDuration);
    }

    public void moveFluentlyTo(Point destinationPoint, int sleepDuration) {
        Point currentPoint = getCurrentPoint();
        if (currentPoint.equals(destinationPoint)) {
            return;
        }
        int distance = (int) getDistance(currentPoint, destinationPoint);
        for (int i = 1; i <= distance; i++) {
            Point tmpPoint = new Point((int)(currentPoint.x + (destinationPoint.x - currentPoint.x) * ((double)i / distance)),
                    (int)(currentPoint.y + (destinationPoint.y - currentPoint.y) * ((double)i / distance)));
            moveTo(tmpPoint);
            sleep(sleepDuration);
        }
    }

    public void moveTo(Point destinationPoint) {
        robot.mouseMove(destinationPoint.x, destinationPoint.y);
    }

    private static double getDistance(Point p1, Point p2) {
        return p1.distance(p2.x, p2.y);
    }

    private static Point getCurrentPoint() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        return pointerInfo.getLocation();
    }

    private static Point getDestinationPoint(int distanceX, int distanceY) {
        Point currentPoint = getCurrentPoint();
        Point maxPoint = getMaxPoint();
        if(distanceX> maxPoint.x){
            throw new IllegalArgumentException("Too long distance x");
        }
        if(distanceY> maxPoint.y){
            throw new IllegalArgumentException("Too long distance y");
        }
        int destinationX = distanceX<= currentPoint.x? currentPoint.x-distanceX : currentPoint.x+distanceX;
        int destinationY = distanceY<= currentPoint.y? currentPoint.y-distanceY : currentPoint.y+distanceY;

        return new Point(destinationX, destinationY);
    }

    private static Point getMaxPoint() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return new Point(screenSize.width, screenSize.height);
    }

    private static void sleep(int sleepDuration) {
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CursorMover().moveFluentlyToDestinationAndBack(10, 30, 10);
    }
}
