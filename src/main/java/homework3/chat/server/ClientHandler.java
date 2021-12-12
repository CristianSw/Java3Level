package homework3.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class ClientHandler {

    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean isAuthenticated = false;

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
            new Thread(() -> timeoutValidator(socket, startConnectingTime)).start();
            String maybeCredentials = in.readUTF();
            if (maybeCredentials.startsWith("-auth")) {
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
                        return;
                    } else {
                        sendMessage("This username is busy!");
                    }
                }
            } else {
                sendMessage("Wrong Login or Passwd !!!!");
            }
        }
    }

    private void timeoutValidator(Socket socket, int initialConnectionTime) {
        int currentTime;
        while (!isAuthenticated) {
            currentTime = (int) System.currentTimeMillis();
            if ((currentTime - initialConnectionTime) >= 120000) {
                sendMessage("Connection Timeout. Closing connection!");
                closeConnection(socket);
                break;
            }
        }

    }

    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenMessages() throws IOException {
        while (true) {
            String inboundMessage = in.readUTF();
            if (inboundMessage.equals("-exit")) {
                break;
            }
            server.broadcastMessage(inboundMessage);
        }
    }

}
