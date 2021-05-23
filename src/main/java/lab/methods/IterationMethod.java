package lab.methods;
import lab.models.IFunc;
import lab.models.Point;
import lab.models.Result;

public class IterationMethod {
    //Функция поиска корня: методом простой итерации
    private static double g(IFunc f, double x) {
        // b - произвольное число, исходя из метода простой итерации.
        double b = 0.5;
        return x + b*f.solve(x);
    }
    public static Result solve(IFunc f, double x, double eps){
        Result result = new Result();
        result.addHeader("№", "xk", "f(xk)", "xk-1", "F(xk)", "|xk - xk+1|");
        for(double iter = 1; eps < Math.abs(f.solve(x)); iter++) {
            result.addCol(String.valueOf(x), String.valueOf(f.solve(x)), String.valueOf(g(f, x)), "       ", String.valueOf(Math.abs(x-g(f, x))));
            x = g(f, x);
        }
        Point p = new Point(x, f.solve(x));
        System.out.println(x + " | " + f.solve(x));
        result.setPoint(p);
        return result;
    }
}
