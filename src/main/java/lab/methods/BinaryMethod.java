package lab.methods;
import lab.models.IFunc;
import lab.models.Point;
import lab.models.Result;

public class BinaryMethod {
    //Функция поиска корня: методом половинного деления
    public static Result solve(IFunc f, double a, double b, double eps){
        Result result = new Result();
        result.addHeader("№", "  a  ", "   b  ", "   x  ", "   f(a)  ", "   f(b)  ", "   f(x)  ", "   |a-b|  ");
        double x = 0;
        int iter = 1;
        while(Math.abs(b-a)>eps){
            x = (a+b)/2;
            if((f.solve(a)*f.solve(x))>0) a=x;
            else b=x;
            result.addCol(String.format("%.5f",a), String.format("%.5f",b), String.format("%.5f",x), String.format("%.5f", f.solve(a)), String.format("%.5f",f.solve(b)), String.format("%.5f",f.solve(x)), String.format("%.5f",Math.abs(b-a)));
            iter++;
            if(iter > 100){
                break;
            }
        }
        Point p = new Point(x, f.solve(x));
        result.setPoint(p);
        return result;
    }
    public static boolean check(IFunc f, double a, double b, double eps) {
        boolean sign = (f.solve(a)>0);
        for(double i = a; i < b; i+= eps) {
            boolean chk = f.solve(i)>0;
            if(sign != chk) return true;
        }
        return false;
    }
}
