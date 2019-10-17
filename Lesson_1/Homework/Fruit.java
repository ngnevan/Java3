package Lesson_1.Homework;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Fruit {
    String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract float getWeight();
}

class Apple extends Fruit {
    final float WEIGHT = 1.0f;

    public Apple(String name) {
        super(name);
    }
    public float getWeight() {
        return WEIGHT;
    }
}

class Orange extends Fruit {
    final float WEIGHT = 1.5f;

    public Orange(String name) {
        super(name);
    }

    public float getWeight() {
        return WEIGHT;
    }
}

class Box<T extends Fruit> {
    protected ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float result = fruits.size() * fruits.get(0).getWeight();
        return result;
    }

    public boolean compare(Box<?> another) {
        if (this.getWeight() == another.getWeight())
            return true;
        return false;
    }

    public void intersperse(Box<T> another) {
        Iterator<T> it = fruits.iterator();
        while (it.hasNext()) {
            another.add(it.next());
            it.remove();
        }
    }
}


class FruitBoxMain {
    public static void main(String[] args) {
        Apple a1 = new Apple("Яблоко1");
        Apple a2 = new Apple("Яблоко2");
        Apple a3 = new Apple("Яблоко3");
        Orange o1 = new Orange("Апельсин1");
        Orange o2 = new Orange("Апельсин2");
        Orange o3 = new Orange("Апельсин3");
        Orange o4 = new Orange("Апельсин4");
        Box<Apple> boxA = new Box<>();
        Box<Orange> boxO = new Box<>();
        boxA.add(a1);
        boxA.add(a2);
        boxA.add(a3);
        boxO.add(o1);
        boxO.add(o2);
        System.out.println(boxA.compare(boxO));
        boxO.add(o3);
        System.out.println(boxA.compare(boxO));
        Box<Orange> boxO2 = new Box<>();
        boxO2.add(o4);
        boxO.intersperse(boxO2);
        for (Orange o : boxO2.fruits) {
            System.out.print(o.getName() + " ");
        }
        System.out.println();
        System.out.println(boxA.getWeight());
        System.out.println(boxO2.getWeight());


    }
}