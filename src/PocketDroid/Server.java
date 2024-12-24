import com.whirvis.jraknet.server.RakNetServer;

public class MinecraftServer {
    public static void main(String[] args) {
        int port = 19132; // Default Bedrock port
        RakNetServer server = new RakNetServer(port, 10); // Allow up to 10 clients

        server.addListener(event -> {
            System.out.println("Received packet: " + event.getPacket());
        });

        server.startThreaded();
        System.out.println("Server started on port " + port);
    }
}
