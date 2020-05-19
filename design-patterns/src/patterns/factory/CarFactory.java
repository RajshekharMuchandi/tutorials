package patterns.factory;

public class CarFactory {

    // creational design patterns
    public static Car getCar(String name) {
        if("Ford".equalsIgnoreCase(name)){
            return new Ford(); // ford specialization
        }
        if("Ferrari".equalsIgnoreCase(name)){
            return new Ferrari();
        }
        if("Kia".equalsIgnoreCase(name)){
            return new Kia();
        }
        return null;
    }
}
