package homework3.chat.server;

import java.sql.SQLException;

public interface AuthServiceInterface {
    void start() throws ClassNotFoundException , SQLException;
    void stop();
    String findUserByLoginAndPassword(final String login,final String passwd);
}
