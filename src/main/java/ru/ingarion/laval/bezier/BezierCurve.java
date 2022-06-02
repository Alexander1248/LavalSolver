package ru.ingarion.laval.bezier;

import java.util.ArrayList;
import java.util.List;

public class BezierCurve {
    private List<Point> points = new ArrayList<>();

    public Vector2 getPosition(double t) {
        double c = t * (points.size() - 1.000001);
        int i = (int) c;
        return Point.curve(points.get(i), points.get(i + 1), c - i);
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public int getSize() {
        return points.size();
    }
}
