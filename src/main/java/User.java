public class User {
    private int id;
    private String nick;

    public User(String nick) {
        this.id = -1;
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString(){
        return "El usuario: "+this.nick;
    }
}
