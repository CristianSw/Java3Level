package homework3.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private final List<ClientHandler> loggedUser;
    private final DBAuthService dbAuthService;

    public Server() {
        dbAuthService = new DBAuthService();
        loggedUser = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DBAuthService getAuthService() {
        return dbAuthService;
    }

    public synchronized void subscribe(ClientHandler user) {
        loggedUser.add(user);
    }

    public synchronized void unsubscribe(ClientHandler user) {
        loggedUser.remove(user);
    }

    public synchronized boolean isNotUserOccupied(String name) {
        return !isUserOccupied(name);
    }

    public synchronized boolean isUserOccupied(String name) {

        for (ClientHandler c : loggedUser){
            if (c.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
//
//        return loggedUser.stream()
//                .anyMatch(u -> u.getName().equals(name));

    }

    public synchronized void broadcastMessage(String outboundMessage) {


        loggedUser.forEach(clientHandler -> clientHandler.sendMessage(outboundMessage));
    }

}
