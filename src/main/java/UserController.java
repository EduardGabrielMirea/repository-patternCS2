import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController {

    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    public static void printAllUsers() throws SQLException {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    public static boolean login() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nick: ");
        String nick = sc.nextLine();

        try {
        Cs2Service.user = userRepository.findByNick(nick);
        return true;
        }catch (ClassNotFoundException nfe){
            System.out.println("You are not registred yet.");
        }
        return false;
    }

    public static void logout(){
    }

    public static void addUser() throws SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nick: ");
        String nick = sc.nextLine();
        User user = new User(nick);

    }
}
