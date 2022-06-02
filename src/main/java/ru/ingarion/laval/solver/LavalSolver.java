package ru.ingarion.laval.solver;

public class LavalSolver {
    private final Laval laval = new Laval();

    public double crc = 1;

    private final double k = 1.4;
    private final double R = 287;

    private final double T = 1273;
    private final double p = 1.5;

    private final EquationSolver lambda = new EquationSolver(-10, 10, 5) {
        @Override
        public double function(double x) {
            return x * (Math.pow(1 - (1.0 / 6) *Math.pow(x, 2),2.5)) / (Math.pow(1 - (1.0/6), 2.5)) - crc;
        }
    };



    public Laval getLaval() {
        return laval;
    }

    public double getVelocity(double x) {
        crc = laval.criticalRadiusCoefficient(x);
        lambda.solve(100);
        double l;
        if (x >= laval.getCriticalX()) l = lambda.getRoots().get(1);
        else l = lambda.getRoots().get(0);
        return  l * Math.sqrt(2 * k * R * T / (k + 1));
    }

    public double getMachNumber(double x) {
        crc = laval.criticalRadiusCoefficient(x);
        lambda.solve(100);
        double l;
        if (x >= laval.getCriticalX()) l = lambda.getRoots().get(1);
        else l = lambda.getRoots().get(0);
        return l * Math.sqrt(2 / (k + 1 - (k - 1) * l * l));
    }

    public double getTemperature(double x) {
        crc = laval.criticalRadiusCoefficient(x);
        lambda.solve(100);
        double l;
        if (x >= laval.getCriticalX()) l = lambda.getRoots().get(1);
        else l = lambda.getRoots().get(0);
        return (1 - (k - 1) * l * l / (k + 1)) * T;
    }

    public double getPressure(double x) {
        crc = laval.criticalRadiusCoefficient(x);
        lambda.solve(100);
        double l;
        if (x >= laval.getCriticalX()) l = lambda.getRoots().get(1);
        else l = lambda.getRoots().get(0);
        return Math.pow(1 - (k - 1) * l * l / (k + 1) , k / (k - 1)) * p;
    }
}
