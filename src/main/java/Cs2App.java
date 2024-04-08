import java.security.PublicKey;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Cs2App {

    //Creamos una variable global.
    private static final UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private static int currentScreen = 0;
    public static void main(String[] args) throws SQLException {
        /*List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
         */
        int opcion;
        //printBanner();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while (!salir){
            printMenu();
            opcion = selectOption();

            if (currentScreen == 0){
                switch (opcion){
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        UserController.printAllUsers();
                        break;
                    case 2:
                        UserController.login();
                        break;
                    case 3:

                }
            }
        }
    }

    public static void printMenu(){

        if (currentScreen == 0){
            System.out.println("0 Exit | 1 AllUsers | 2 Login | 3 Register");
        }else if (currentScreen == 1){
            System.out.println(" ");
        }
    }

    public static int selectOption(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        while (true){
            try {
                opcion = Integer.parseInt(sc.next());
                //En caso de que eligas una opcion que este fuera del rango del menu
                if ((currentScreen == 0 && opcion <= 3) || (currentScreen == 1 && opcion <= 6)){
                    break;
                }else {
                    System.out.println("Incorrect Option");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Incorrect Option");
            }
        }
        return opcion;
    }
}
