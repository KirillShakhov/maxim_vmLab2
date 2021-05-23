package lab;

import lab.interfaces.IFunc;
import lab.models.IndividualFunc;
import lab.modules.GraphModule;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Нахождение интегралов методом прямоугольников(левых, правых, средних) и методом Симпсона");
        System.out.println("Выберите уравнение:");
        Map<String, IFunc> funcs = new HashMap<>();
        //
        funcs.put("2,74x^3-1,93x^2-15,28x-3,72", x -> 2.74*Math.pow(x, 3)-1.93*Math.pow(x, 2)-15.28*x-3.72);
        //2,74𝑥3−1,93𝑥2−15,28𝑥−3,72
        //
        funcs.put("sin(x)/x-6", x -> Math.sin(x)/(x-6));
        //

        /*
        Вывод и обработка ввода. Не трогать.
        */
        int i = 1;
        ArrayList<String> keys = new ArrayList<>();
        for (Map.Entry<String, IFunc> entry : funcs.entrySet()) {
            System.out.println((i++) + ". " + entry.getKey());
            keys.add(entry.getKey());
        }
        System.out.println(funcs.size()+1 + ". Ввести своё уравнение");
        String str = scanner.nextLine();
        IFunc func1;
        try {
            func1 = funcs.get(keys.get(Integer.parseInt(str) - 1));
        } catch (Exception e) {
            IndividualFunc individualFunc = new IndividualFunc();
            individualFunc.execute();
            func1 = individualFunc;
        }

        Map<String, ArrayList<IFunc>> map_func = new HashMap<>();
        Map<String, ArrayList<Point>> point_func = new HashMap<>();
        ArrayList<IFunc> arrayList = new ArrayList<>();
        arrayList.add(func1);
        map_func.put("Функция", arrayList);
        // Рисуем график
        new GraphModule(map_func, point_func);

//        System.out.println("Выберите метод:\n1. Метод прямоугольников\n2. Метод Симпсона");
//        if(scanner.nextLine().equals("1")) {
//            System.out.println("Решение методом прямоугольников:");
//            RectangleIntegral.execute(func1);
//        }
//        else {
//            System.out.println("Решение методом Симпсона:");
//            SimpsonsIntegral.execute(func1);
//        }
    }
}
