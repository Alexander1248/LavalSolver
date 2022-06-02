package ru.ingarion.laval;


import ru.ingarion.laval.bezier.Point;
import ru.ingarion.laval.bezier.Vector2;
import ru.ingarion.laval.solver.Laval;
import ru.ingarion.laval.solver.LavalSolver;

public class App {
    public static void main(String[] args) {
        LavalSolver solver = new LavalSolver();
        Laval laval = solver.getLaval();
        laval.addPoint(new Point(new Vector2(-5, 4), Vector2.zero, 0));
        laval.addPoint(new Point(new Vector2(-1, 4), Vector2.zero, 0));
        laval.addPoint(new Point(new Vector2(0, 1), Vector2.zero, 0));
        laval.addPoint(new Point(new Vector2(10, 2.4), Vector2.zero, 0));

        for (double i = -5; i <= 10; i += 0.1) {
            System.out.println(i + " " + solver.getPressure(i));
        }
    }
}
