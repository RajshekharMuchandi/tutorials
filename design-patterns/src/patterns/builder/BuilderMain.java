package patterns.builder;

public class BuilderMain {

    public static void main(String[] args) {

       User user = new User.UserBuilder("Shyam","Santhosh").address("Bengaluru").build();
       System.out.println(user);
    }
}
