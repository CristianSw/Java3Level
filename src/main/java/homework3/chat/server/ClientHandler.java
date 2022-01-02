package homework3.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean isAuthenticated = false;
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    doAuthentication(socket);
                    listenMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection(socket);
                }
            })
                    .start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }

    private void closeConnection(Socket socket) {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void doAuthentication(Socket socket) throws IOException {
        int startConnectingTime = (int) System.currentTimeMillis();

        sendMessage("Greeting you in the Outstanding Chat.");
        sendMessage("Please do authentication. Template is: -auth [login] [password]");

        while (true) {
            executorService.execute(()->timeoutValidator(socket, startConnectingTime));
           // new Thread(() -> timeoutValidator(socket, startConnectingTime)).start(); //alternative to executor service
            String maybeCredentials = in.readUTF();
            if (maybeCredentials.startsWith("-auth")) {
                logger.info("Authentication started!");
                String[] credentials = maybeCredentials.split("\\s");

                String username = server
                        .getAuthService()
                        .findUserByLoginAndPassword(credentials[1], credentials[2]);


                if (username != null) {
                    if (server.isNotUserOccupied(username)) {
                        sendMessage("Client LOGIN: " + username);
                        name = username;
                        server.broadcastMessage("Hello, " + username);
                        server.subscribe(this);
                        logger.info("User :{} is logged in",username);
                        return;
                    } else {
                        sendMessage("This username is busy!");
                        logger.info("username is busy : {}", username);
                    }
                }
            } else {
                sendMessage("Wrong Login or Passwd !!!!");
                logger.warn("Wrong login or passwd");
            }
        }
    }

    private void timeoutValidator(Socket socket, int initialConnectionTime) {
        int currentTime;
        while (!isAuthenticated) {
            currentTime = (int) System.currentTimeMillis();
            if ((currentTime - initialConnectionTime) >= 120000) {
                sendMessage("Connection Timeout. Closing connection!");
                logger.warn("Connection timeout!!!!");
                closeConnection(socket);
                break;
            }
        }

    }

    private void history(final String login){

    }

    private void loadHistory(final String login){

    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
            logger.info("client send message : {}", outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")) {
                logger.info("user exit !");
                break;
            }
            server.broadcastMessage(inboundMessage);
        }
    }

}
