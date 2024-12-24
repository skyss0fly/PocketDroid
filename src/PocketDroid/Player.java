import java.net.InetAddress;

public class Player {

    private final String username;         // Player's username or identifier
    private final InetAddress address;    // IP address of the player
    private final int port;               // Port used by the player
    private long clientId;                // Unique client ID (from RakNet protocol)
    private boolean connected;            // Connection status
    private long lastPingTime;            // Last ping time
    private long lastActivityTime;        // Last time the player sent/received data
    private int ping;                     // Measured ping in milliseconds

    // Constructor
    public Player(String username, InetAddress address, int port, long clientId) {
        this.username = username;
        this.address = address;
        this.port = port;
        this.clientId = clientId;
        this.connected = true;  // Initially connected
        this.lastPingTime = System.currentTimeMillis();
        this.lastActivityTime = System.currentTimeMillis();
        this.ping = 0;  // Default ping value
    }

    // Update the last activity time
    public void updateActivity() {
        this.lastActivityTime = System.currentTimeMillis();
    }

    // Update ping value
    public void updatePing(long pingTimestamp) {
        this.ping = (int) (System.currentTimeMillis() - pingTimestamp);
        this.lastPingTime = System.currentTimeMillis();
    }

    // Disconnect the player
    public void disconnect() {
        this.connected = false;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public long getClientId() {
        return clientId;
    }

    public boolean isConnected() {
        return connected;
    }

    public long getLastPingTime() {
        return lastPingTime;
    }

    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public int getPing() {
        return ping;
    }

    // Setters
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", address=" + address +
                ", port=" + port +
                ", clientId=" + clientId +
                ", connected=" + connected +
                ", lastPingTime=" + lastPingTime +
                ", lastActivityTime=" + lastActivityTime +
                ", ping=" + ping +
                '}';
    }
}
