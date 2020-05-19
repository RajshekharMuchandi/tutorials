package patterns.factory;

public class FactoryMain {

    public static void main(String[] args) {
       display(CarFactory.getCar("Ford"));
       display(CarFactory.getCar("Ferrari"));
       display(CarFactory.getCar("Kia"));
    }

    public static void display(Car car){
        System.out.println(car.display()); // dynamic call
    }
}
