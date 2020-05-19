package patterns.singleton;

public class Singleton {

    private static Singleton instance;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if(instance == null) {
            return new Singleton();
        }
          return instance;
    }

}
