package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.MAX_PRIORITY;

public class Main {
    public static final int CARS_COUNT = 4;
    public static CountDownLatch latch = new CountDownLatch(CARS_COUNT);
    public static ReentrantLock locker = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        locker.lock();
        ExecutorService service = Executors.newFixedThreadPool(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            service.execute(cars[i]);
        }
        while (true) {
            if (latch.getCount() == 0) break;
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        locker.unlock();
        service.shutdown();
        service.awaitTermination(MAX_PRIORITY, TimeUnit.HOURS);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

class Car implements Runnable {
    private static AtomicInteger atomic = new AtomicInteger(0);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            Main.latch.countDown();
            Main.latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            if (!Main.locker.isLocked()) break;
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (atomic.get() < 1) {
            atomic.incrementAndGet();
            System.out.println(name + " WIN!!!");
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    private Semaphore semaphore = new Semaphore(Main.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}

