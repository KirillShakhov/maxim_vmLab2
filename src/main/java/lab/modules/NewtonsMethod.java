package lab.modules;
import lab.interfaces.IFunc;

public class NewtonsMethod {
    static Double solve(IFunc f, IFunc df, IFunc g, double x, double eps) {
        for(double iter = 1; eps < Math.abs(f.solve(x)); iter++) {
            if(df.solve(x) == 0)//Чёртовский важный момент(!)
                break;//ведь если df(x) == 0, то будет деление на ноль x - f(x)/df(x)
            x = g.solve(x);
        }
        return x;
    }
}
