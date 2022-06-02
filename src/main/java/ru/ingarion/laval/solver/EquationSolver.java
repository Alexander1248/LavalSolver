package ru.ingarion.laval.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EquationSolver {
    private List<Double> roots;
    private double min;
    private double max;
    private double quality;

    public EquationSolver(double minX, double maxX, int quality) {
        min = minX;
        max = maxX;
        this.quality = Math.pow(2, -quality);
    }

    private void solve(boolean right, int limit, double position) {
        int k = 0;
        double step = quality;
        double x = position;
        boolean state = right;
        while (k < limit) {
            if (state) {
                x += step;
                double sgn1 = Math.signum(function(x));
                double sgn2 = Math.signum(function(x - step));
                if (sgn1 != sgn2) {
                    step /= 2;
                    state = false;
                } else if (k % 5 == 4) step *= 2;
            }
            else {
                x -= step;
                double sgn1 = Math.signum(function(x));
                double sgn2 = Math.signum(function(x + step));
                if (sgn1 != sgn2) {
                    step /= 2;
                    state = true;
                } else if (k % 5 == 4) step *= 2;

            }
            k++;
        }
        double function = function(x);
        if (function > -10.0 / limit && function < 10.0 / limit) {
            if (x > min && x < max) {
                roots.add(x);
                if (right) solve(true, limit, x + quality);
                else solve(false, limit, x - quality);
            }
        }
    }

    public void solve(int limit) {
        roots = new ArrayList<>();
        double avg = (min + max) / 2;
        double function = function(avg);
        if (function > -0.001 / limit && function < 0.001) roots.add(avg);

        solve(false, limit, avg - 0.001);
        solve(true, limit, avg + 0.001);

        Collections.sort(roots);
    }

    public List<Double> getRoots() {
        return roots;
    }

    public abstract double function(double x);
}
