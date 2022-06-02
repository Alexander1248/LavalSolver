package ru.ingarion.laval.solver;

import ru.ingarion.laval.bezier.BezierCurve;
import ru.ingarion.laval.bezier.Point;
import ru.ingarion.laval.bezier.Vector2;

public class Laval {
    private BezierCurve curve = new BezierCurve();
    private double criticalRadius;
    private double criticalX;

    public void addPoint(Point point) {
        curve.addPoint(point);
        if (curve.getSize() > 1) {
            criticalRadius = curve.getPosition(0).y;
            for (double i = 0; i < 1; i += 0.01) {
                Vector2 pos = curve.getPosition(i);
                if (pos.y < criticalRadius) {
                    criticalRadius = pos.y;
                    criticalX = pos.x;
                }
            }
        }
    }

    public double radiusInX(double x) {
        double t = 0;
        double step = 0.25;
        Vector2 pos = curve.getPosition(t);

        while (Math.abs(pos.x - x) > 0.01) {
            if (pos.x < x) t += step;
            else t -= step;
            Vector2 current = curve.getPosition(t);
            if (Math.signum(current.x - x) != Math.signum(pos.x - x)) step /= 2;
            pos = current;
        }
        return pos.y;
    }

    public double criticalRadiusCoefficient(double x) {
        return criticalRadius / radiusInX(x);
    }

    public double getCriticalRadius() {
        return criticalRadius;
    }

    public double getCriticalX() {
        return criticalX;
    }
}
