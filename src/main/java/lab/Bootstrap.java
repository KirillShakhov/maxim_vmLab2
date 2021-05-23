package lab;
import lab.methods.IterationMethod;
import lab.methods.NewtonsMethod;
import lab.models.IFunc;
import lab.models.IndividualFunc;
import lab.models.Point;
import lab.models.Result;
import lab.methods.BinaryMethod;
import lab.libs.GraphModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нахождение интегралов методом прямоугольников(левых, правых, средних) и методом Симпсона");
        System.out.println("Выберите уравнение:");
        // для названий функций
        ArrayList<String> names = new ArrayList<>();
        // сами функции
        ArrayList<IFunc> funcs = new ArrayList<>();
        // производные для метода Ньютона
        ArrayList<IFunc> dfuncs = new ArrayList<>();


        // 1
        names.add("2,74x^3-1,93x^2-15,28x-3,72");
        funcs.add(x -> 2.74*Math.pow(x, 3)-1.93*Math.pow(x, 2)-15.28*x-3.72);
        dfuncs.add(x -> ((411*Math.pow(x, 2))/50) - ((193*x)/50) -  (382/25));
        //

        /*
        Вывод и обработка ввода. Не трогать.
        */
        int i = 1;
        for (String name : names) {
            System.out.println((i++) + ". " + name);
        }
        System.out.println(funcs.size()+1 + ". Ввести своё уравнение");
        String str = scanner.nextLine();
        IFunc func1 = null;
        IFunc dfunc1 = null;
        try {
            func1 = funcs.get(Integer.parseInt(str) - 1);
            dfunc1 = dfuncs.get(Integer.parseInt(str) - 1);
        } catch (Exception e) {
            IndividualFunc individualFunc = new IndividualFunc();
            individualFunc.execute();
            func1 = individualFunc;
        }
        // Ввод данных
        double a, b, eps;
        while (true) {
            try {
                System.out.println("Введите a");
                a = Double.parseDouble(scanner.nextLine());
                System.out.println("Введите b");
                b = Double.parseDouble(scanner.nextLine());
                if (a > b) {
                    double t = a;
                    a = b;
                    b = a;
                }
                System.out.println("Введите eps");
                eps = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception ignored) { }
        }
        Map<String, ArrayList<IFunc>> map_func = new HashMap<>();
        Map<String, ArrayList<Point>> point_func = new HashMap<>();
        // Рисуем график
        ArrayList<IFunc> arrayListFuncs = new ArrayList<>();
        arrayListFuncs.add(func1);
        map_func.put("Функция", arrayListFuncs);
        // Решение с помощью метода половинного деления
        Result result1 = BinaryMethod.solve(func1, a, b, eps);
        result1.printTable();
        ArrayList<Point> p1 = new ArrayList<>();
        p1.add(result1.getPoint());
        point_func.put("Решение методом половинного деления", p1);
        // ввод для метода Ньютона
        double x0;
        if (dfunc1 != null) {
            while (true) {
                try {
                    System.out.println("Методом простой итерации:");
                    System.out.println("Введите начальный x для метода простой итерации");
                    x0 = Double.parseDouble(scanner.nextLine());
                    if (a > b) {
                        double t = a;
                        a = b;
                        b = a;
                    }
                    System.out.println("Введите eps");
                    eps = Double.parseDouble(scanner.nextLine());
                    break;
                } catch (Exception ignored) {
                }
            }
            // Решение с помощью метода Ньютона
            Result result2 = NewtonsMethod.solve(func1, dfunc1, x0, eps);
            result2.printTable();
            ArrayList<Point> p2 = new ArrayList<>();
            p2.add(result2.getPoint());
            point_func.put("Решение методом Ньютона", p2);
        }else{
            System.out.println("Метод Ньютона не поддерживается для индивидуальных функций");
        }
        // ввод для метода простой итерации
        while (true) {
            try {
                System.out.println("Методом простой итерации:");
                System.out.println("Введите начальный x для метода простой итерации");
                x0 = Double.parseDouble(scanner.nextLine());
                if (a > b) {
                    double t = a;
                    a = b;
                    b = a;
                }
                System.out.println("Введите eps");
                eps = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception ignored) { }
        }
        // Решение с помощью метода простой итерации
        Result result3 = IterationMethod.solve(func1, x0, eps);
        result3.printTable();
        ArrayList<Point> p3 = new ArrayList<>();
        p3.add(result3.getPoint());
        point_func.put("Решение методом простой итерации", p3);
        // Отрисовка всех графиков
        new GraphModule(map_func, point_func);
    }
}
