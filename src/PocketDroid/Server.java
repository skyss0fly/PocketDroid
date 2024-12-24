import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import PocketDroid.Libraries.Raknet.Packet;
import PocketDroid.Libraries.Raknet.Acknowledge;
import PocketDroid.Libraries.Raknet.NackPacket;
import PocketDroid.Libraries.Raknet.ConnectionRequestPacket;
import PocketDroid.Libraries.Raknet.ConnectionAcceptedPacket;
import PocketDroid.Libraries.Raknet.PingPacket;
import PocketDroid.Libraries.Raknet.PongPacket;
import PocketDroid.Libraries.Raknet.PacketHander;

public class Server {

    private final int port;
    private DatagramSocket socket;
    private boolean running;

    // Server Information
    private final String serverName;
    private final String motd; // Message of the Day
    private final int maxPlayers;
    private int onlinePlayers;

    // Constructor to initialize the server
    public Server(int port, String serverName, String motd, int maxPlayers) {
        this.port = port;
        this.serverName = serverName;
        this.motd = motd;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = 0;
    }

    // Initialize the server
    public void initialize() throws Exception {
        // Bind to the port
        socket = new DatagramSocket(port);
        running = true;

        System.out.println("Server started on port " + port);
        System.out.println("Server Name: " + serverName);
        System.out.println("MOTD: " + motd);
        System.out.println("Max Players: " + maxPlayers);
    }

    // Run the main server loop
    public void run() {
        if (!running) {
            System.out.println("Server is not initialized. Call initialize() first.");
            return;
        }

        new Thread(() -> {
            while (running) {
                try {
                    // Buffer to receive data
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                    // Receive packet
                    socket.receive(packet);
                    InetAddress address = packet.getAddress();
                    int port = packet.getPort();

                    // Handle the received data
                    handlePacket(packet, address, port);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Handle incoming packets
    private void handlePacket(DatagramPacket packet, InetAddress address, int port) {
        System.out.println("Received packet from " + address + ":" + port);
        // Placeholder for packet handling logic
    }

    // Shut down the server
    public void shutdown() {
        running = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        System.out.println("Server shut down.");
    }

    // Getters for server info
    public String getServerName() {
        return serverName;
    }

    public String getMotd() {
        return motd;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    // Method to update the number of online players
    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }
}
