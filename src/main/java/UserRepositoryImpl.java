import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepositoryImpl  implements IRepository<User> {

    private java.sql.Connection con;

    public UserRepositoryImpl(){
        this.con = Cs2Service.getConnection();
    }

    public User bdToEntity(ResultSet rs) throws SQLException {
        User users = new User(rs.getInt("User_id"), rs.getString("nick"));
        return users;
    }

    /***
     * findAll() sirve para buscar todos los usuarios.
     * @return
     * @throws SQLException
     */
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();

        Statement st = this.con.createStatement();
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset.
        ResultSet rs = st.executeQuery("SELECT * FROM Users");

        while (rs.next()) {
            //Mapeamos el registro de la BD en un usuario.
            User user = bdToEntity(rs);
            //Añadir el Usuario al conjunto de usuarios.
            users.add(user);
        }
        return users;
    }

    /***
     * findById() sirve para buscar usuarios uno a uno.
     * @param User_id
     * @return
     * @throws SQLException
     */
    public User findById(int User_id) throws SQLException {
        //Creamos la variable user en null para poder devolverla, pero para devolver el resultado de la consulta.
        User user = null;
        PreparedStatement st = con.prepareStatement("SELECT * FROM Users WHERE user_id = ?");
        st.setInt(1,User_id);
        ResultSet rs = st.executeQuery();

        if(rs.next()){
            user = bdToEntity(rs);
        }
        return user;
    }

    /***
     * Buscar en la BD por el nick del usuario.
     * @param nick
     * @return
     * @throws SQLException
     */
    public User findByNick(String nick)throws SQLException , ClassNotFoundException{
        User user = null;
        PreparedStatement st;
        ResultSet rs;

        st = con.prepareStatement("SELECT * FROM Users WHERE nick = ?");
        st.setString(1,nick);
        rs = st.executeQuery();

        if (rs.next()){
            user = bdToEntity(rs);
        }else {
         throw new ClassNotFoundException();
        }

        return user;
    }


    /***
     * Guarda el usuario User en la base de datos, insertando si id es distinto de 0 o actualizando aquél
     * registro con dicho id
     * @param user
     * @throws SQLException
     */
    public void save(User user) throws SQLException {

        //En caso de que no haya nigún usuario creado porque nuestro id esta en 0.
        if (user.getId() == 0){
            //Declaramos parametros para ejecutar consultas.
            PreparedStatement st;
            ResultSet rs;

            //Creamos la consulta.
            String query = "INSERT INTO Users (User_id, nick) VALUES (?,?)";
            //Fijáos en Statement.RETURN_GENERATED_KEYS. Permite recuperar el campo ID autogenerado por MySql
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,user.getId());
            st.setString(2,user.getNick());
            st.executeQuery();
            //Recuperar el id autogenerado.
            rs = st.getGeneratedKeys();

            //Actualizamos
            if (rs.next()){
                user.setId(rs.getInt(1));
            }else {
                st = con.prepareStatement("UPDATE Users SET nick = ? WHERE User_id = ?");
                st.setInt(1, user.getId());
                st.setString(2,user.getNick());
                st.executeUpdate();
            }
        }
    }

    public void delete(User user) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM Users WHERE id = ?");
        st.setInt(1, user.getId());
        st.executeUpdate();
        st.close();
    }
}
