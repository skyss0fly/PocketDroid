import java.nio.ByteBuffer;
import PocketDroid.Libraries.Raknet.Packet;

public class PingPacket extends Packet {

    private long timestamp; // Timestamp when the packet is sent

    public PingPacket() {
        super((byte) 0x00); // Set the Packet ID for Ping (example: 0x00)
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        byte packetId = buffer.get(); // Read the Packet ID
        if (packetId != this.packetId) {
            throw new IllegalArgumentException("Invalid Packet ID for PingPacket");
        }

        // Decode timestamp
        this.timestamp = buffer.getLong();
    }

    @Override
    public void encode(ByteBuffer buffer) {
        buffer.clear(); // Reset buffer
        buffer.put(packetId); // Write the Packet ID

        // Write timestamp
        buffer.putLong(timestamp);

        buffer.flip(); // Prepare buffer for sending
    }
}
