package lab.methods;
import lab.models.IFunc;
import lab.models.Point;
import lab.models.Result;

public class NewtonsMethod {
    //Функция поиска корня: методом Ньютона
    public static Result solve(IFunc f, IFunc df, double x, double eps) {
        Result result = new Result();
        result.addHeader("№", "xk", "f(xk)", "f'(xk)", "xk+1", "|xk - xk+1|");
        double dx;
        int i = 1;
        while (eps < Math.abs(f.solve(x))) {
            if(df.solve(x) == 0)break;//Важный момент(!)
            //ведь если df(x) == 0, то будет деление на ноль x - f(x)/df(x)
            dx = f.solve(x)/df.solve(x);
            result.addCol(String.valueOf(x), String.valueOf(f.solve(x)), String.valueOf(df.solve(x)), String.valueOf(x - dx), String.valueOf(Math.abs(x - (x - dx))));
            x = x - dx;
            i++;
            if(i > 100)break;
        }
        result.setPoint(new Point(x, f.solve(x)));
        return result;
    }
}
