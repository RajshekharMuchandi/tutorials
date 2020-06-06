package patterns.singleton;

public class Singleton {

    private static Singleton instance = new Singleton();

    /**
     * Restrict object creation
     */
    private Singleton(){

    }

    /**
     * Create new object if instance is null, Otherwise returns existing object
     * @return instance
     */
    public static Singleton getInstance(){
          return instance;
    }

}
