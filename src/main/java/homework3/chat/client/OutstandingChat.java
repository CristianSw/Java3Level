package homework3.chat.client;



import homework3.chat.client.communication.ClientCommunicator;
import homework3.chat.client.gui.ChatFrame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class OutstandingChat {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;

    public OutstandingChat() {
        communicator = new ClientCommunicator();

        Consumer<String> outboundMessageConsumer = communicator::sendMessage;

        frame = new ChatFrame(outboundMessageConsumer);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            while (true) {
                String inboundMessage = communicator.receiveMessage();
                frame.getInboundMessageConsumer().accept(inboundMessage);
            }
        });
        }


}
