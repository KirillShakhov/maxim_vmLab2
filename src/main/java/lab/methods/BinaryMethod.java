package lab.methods;
import lab.models.IFunc;
import lab.models.Point;
import lab.models.Result;

public class BinaryMethod {
    //Функция поиска корня: методом половинного деления
    public static Result solve(IFunc f, double a, double b, double eps){
        Result result = new Result();
        result.addHeader("№", "a", "b", "x", "f(a)", "f(b)", "f(x)", "|a-b|");
        double x = 0;
        int iter = 1;
        while(Math.abs((Math.abs(b)-Math.abs(a))/2)>eps){
            x = (a+b)/2;
            if((f.solve(a)*f.solve(x))>0) a=x;
            else b=x;
            result.addCol(String.valueOf(a), String.valueOf(b), String.valueOf(x), f.solve(a).toString(), f.solve(b).toString(), f.solve(x).toString(), String.valueOf(Math.abs(b-a)));
            iter++;
            if(iter > 100){
                break;
            }
        }
        Point p = new Point(x, f.solve(x));
        result.setPoint(p);
        return result;
    }
}
