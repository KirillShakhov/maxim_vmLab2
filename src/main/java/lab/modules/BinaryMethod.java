package lab.modules;

import lab.interfaces.IFunc;

public class BinaryMethod {
    //Функция поиска корня:
    public double solve(IFunc f, double a, double b, double eps){
        double c = 0;
        while((b-a)/2>eps){
            c = (a+b)/2;
            if((f.solve(a)*f.solve(c))>0) a=c;
            else b=c;
        }
        return c;
    }
}
