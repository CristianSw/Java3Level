package homework3.chat.client;

public class Clients {
    private String login;
    private String passwd;
    private String username;

    public Clients(String login, String passwd, String username) {
        this.login = login;
        this.passwd = passwd;
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getUsername() {
        return username;
    }
}
