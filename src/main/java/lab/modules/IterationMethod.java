package lab.modules;

import lab.interfaces.IFunc;

public class IterationMethod {
    private static double g(IFunc f, double x) {
        // b - произвольное число, исходя из метода простой итерации.
        double b = 0.5;
        return x + b*f.solve(x);
    }
    public static double solve(IFunc f, double x, double eps){
        for(double iter = 1; eps < Math.abs(f.solve(x)); iter++) {
            x = g(f, x);
        }
        return x;
    }
}
