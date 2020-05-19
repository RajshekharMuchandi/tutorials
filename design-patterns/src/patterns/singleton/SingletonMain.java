package patterns.singleton;

public class SingletonMain {

    public static void main(String[] args) {

        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);

    }
}
