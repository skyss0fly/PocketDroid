import java.nio.ByteBuffer;
import PocketDroid.Libraries.Raknet.Packet;

public class ConnectionRequestPacket extends Packet {

    private long clientId; // Unique client ID
    private long timestamp; // Timestamp of the request
    private boolean useSecurity; // Whether to use RakNet security

    public ConnectionRequestPacket() {
        super((byte) 0x09); // Set the Packet ID for Connection Request (example: 0x09)
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isUseSecurity() {
        return useSecurity;
    }

    public void setUseSecurity(boolean useSecurity) {
        this.useSecurity = useSecurity;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        byte packetId = buffer.get(); // Read the Packet ID
        if (packetId != this.packetId) {
            throw new IllegalArgumentException("Invalid Packet ID for ConnectionRequestPacket");
        }

        // Decode fields
        this.clientId = buffer.getLong(); // Unique client ID
        this.timestamp = buffer.getLong(); // Timestamp
        this.useSecurity = buffer.get() == 1; // Security flag
    }

    @Override
    public void encode(ByteBuffer buffer) {
        buffer.clear(); // Reset buffer
        buffer.put(packetId); // Write the Packet ID

        // Write fields
        buffer.putLong(clientId); // Unique client ID
        buffer.putLong(timestamp); // Timestamp
        buffer.put((byte) (useSecurity ? 1 : 0)); // Security flag

        buffer.flip(); // Prepare buffer for sending
    }
}
