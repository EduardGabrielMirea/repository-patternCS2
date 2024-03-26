import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepositoryImpl  implements IRepository<User> {

    private static List<User> entities = new ArrayList<>();
    private static Set<User> usersCached = new HashSet<>();
    private java.sql.Connection con;

    public UserRepositoryImpl(){
        this.con = Cs2Service.getConnection();
    }

    @Override
    public User bdToEntity(ResultSet rs) throws SQLException {
        User users = getUserCacher(rs.getInt("id"));

        return u;
    }

    /**
     * Busca el usuario por id en la tabla user
     * @param user_id
     * @return
     */
    private User getUserCacher(int user_id){
        for (User users : usersCached){
            if (users.getId() == user_id) return users;
        }
        return  null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void save(User entity) throws SQLException {

    }

    @Override
    public void delete(User entity) throws SQLException {

    }
}
