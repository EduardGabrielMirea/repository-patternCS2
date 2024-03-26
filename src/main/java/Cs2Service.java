import java.sql.Connection;
import java.sql.SQLException;

public class Cs2Service {
    static User user = null;

    static java.sql.Connection connection;

    public static Connection getConnection() {
        String host = "jdbc:sqlite:src/main/resources/Cs2BasaDate";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            }catch (SQLException sql){
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }
}
