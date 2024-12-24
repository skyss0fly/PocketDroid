import java.nio.ByteBuffer;
import PocketDroid.Libraries.Raknet.Packet;

public class ConnectionAcceptedPacket extends Packet {

    private long clientId; // Unique client ID
    private long serverTimestamp; // Timestamp from the server
    private long clientTimestamp; // Echoed client timestamp for latency calculation

    public ConnectionAcceptedPacket() {
        super((byte) 0x10); // Set the Packet ID for Connection Accepted (example: 0x10)
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(long serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    public long getClientTimestamp() {
        return clientTimestamp;
    }

    public void setClientTimestamp(long clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        byte packetId = buffer.get(); // Read the Packet ID
        if (packetId != this.packetId) {
            throw new IllegalArgumentException("Invalid Packet ID for ConnectionAcceptedPacket");
        }

        // Decode fields
        this.clientId = buffer.getLong();
        this.serverTimestamp = buffer.getLong();
        this.clientTimestamp = buffer.getLong();
    }

    @Override
    public void encode(ByteBuffer buffer) {
        buffer.clear(); // Reset buffer
        buffer.put(packetId); // Write the Packet ID

        // Write fields
        buffer.putLong(clientId);
        buffer.putLong(serverTimestamp);
        buffer.putLong(clientTimestamp);

        buffer.flip(); // Prepare buffer for sending
    }
}
