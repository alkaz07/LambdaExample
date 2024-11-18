package example.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
     //   example1();
     //   example2();
        example3();
    }

    private static void example3() {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(3, 4));
        rectangles.add(new Rectangle(13, 2));
        rectangles.add(new Rectangle(8, 9));
        rectangles.add(new Rectangle(1, 9));
        System.out.println("rectangles = " + rectangles);
        rectangles.sort((r1, r2)-> {
                    if (r1.getW() > r2.getW()) return 1;
                    if(r1.getW()==r2.getW()) return 0;
                    else return -1;
                }
            );
        System.out.println("rectangles = " + rectangles);
        rectangles.sort(Comparator.comparingDouble(r->r.getH()));
        System.out.println("rectangles = " + rectangles);
        rectangles.sort(Comparator.comparingDouble(r-> r.getH()* r.getW()));
        System.out.println("rectangles = " + rectangles);
        rectangles.sort(Comparator.comparingDouble(Rectangle::getPerimeter));
        System.out.println("rectangles = " + rectangles);
        rectangles.sort(Comparator.comparingDouble(r-> -r.getH()* r.getW()));
        System.out.println("rectangles = " + rectangles);
        rectangles.sort(Comparator.comparingDouble(Rectangle::getPerimeter).reversed());
        System.out.println("rectangles = " + rectangles);
        //отсортировать по строковому представлению
        rectangles.sort(Comparator.comparing(Rectangle::toString));
        System.out.println("По строковому виду : " + rectangles);
    }

    private static void example2() {
        double a = -12.3, b=-13.5;
        printOperation(a, b, (x, y) -> {return x+y;});
        printOperation(a, b, ((x, y) -> x-y));
        printOperation(a, b, ((x, y) -> {
            double z=1;
            for (int i = 0; i < y; i++) {
                z*=x;
            }
            return z;
        }));
        printOperation(a, b, Math::pow);
    }

    private static void example1() {
        doSomething(new ExA(123));
        doSomething(new ExA(456));
        doSomething(new Executable() {
            @Override
            public void execute() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Ку");
                }
            }
        });

        doSomething(()->{
            for (int i = 0; i < 4; i++) {
                System.out.print("Ням");
            }
            System.out.println('*');
        });
    }

    public static void doSomething(Executable ex){
        System.out.println("выводим сообщение о том что мы что-то делаем");
        System.out.println(ex);
        ex.execute();
    }

    public static void printOperation(double a, double b, ICalculator calc){
        System.out.println("--------------------------------------");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("Результат = " + calc.calculate(a, b));
    }
}

interface Executable{
    void execute();
}

class ExA implements Executable{
    int id;

    public ExA(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        System.out.println("выполняется метод объекта класса ExA id = "+id);
    }

    @Override
    public String toString() {
        return "ExA{" +
                "id=" + id +
                '}';
    }
}

interface ICalculator{
    double calculate(double x, double y);
}