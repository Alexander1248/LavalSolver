package ru.ingarion.laval.bezier;

public class Point {
    private final Vector2 position;
    private final Vector2 force;
    private final double angle;

    public Point(Vector2 position, Vector2 force, double angle) {
        this.position = position;
        this.force = force;
        this.angle = angle;
    }

    public static Vector2 curve(Point a, Point b, double t) {
        Vector2 af = new Vector2(a.position.x + a.force.y * Math.cos(a.angle), a.position.y + a.force.x * Math.sin(a.angle));
        Vector2 bf = new Vector2(b.position.x - b.force.y * Math.cos(b.angle), b.position.y - b.force.x * Math.sin(b.angle));

        Vector2 p1 = lerp(a.position, af, t);
        Vector2 p2 = lerp(af, bf, t);
        Vector2 p3 = lerp(bf, b.position, t);

        return lerp(lerp(p1, p2, t), lerp(p2, p3, t), t);
    }

    private static Vector2 lerp(Vector2 a, Vector2 b, double t) {
        return new Vector2(a.x + (b.x - a.x) * t, a.y + (b.y - a.y) * t);
    }
}
