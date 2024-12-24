import java.nio.ByteBuffer;
import PocketDroid.Libraries.Raknet.Packet;

public class PongPacket extends Packet {

    private long pingTimestamp; // Echoed timestamp from PingPacket
    private long serverTimestamp; // Current server timestamp

    public PongPacket() {
        super((byte) 0x03); // Set the Packet ID for Pong (example: 0x03)
    }

    public long getPingTimestamp() {
        return pingTimestamp;
    }

    public void setPingTimestamp(long pingTimestamp) {
        this.pingTimestamp = pingTimestamp;
    }

    public long getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(long serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        byte packetId = buffer.get(); // Read the Packet ID
        if (packetId != this.packetId) {
            throw new IllegalArgumentException("Invalid Packet ID for PongPacket");
        }

        // Decode timestamps
        this.pingTimestamp = buffer.getLong();
        this.serverTimestamp = buffer.getLong();
    }

    @Override
    public void encode(ByteBuffer buffer) {
        buffer.clear(); // Reset buffer
        buffer.put(packetId); // Write the Packet ID

        // Write timestamps
        buffer.putLong(pingTimestamp);
        buffer.putLong(serverTimestamp);

        buffer.flip(); // Prepare buffer for sending
    }
}
