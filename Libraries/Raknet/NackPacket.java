import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import PocketDroid.Libraries.Raknet.Packet;

public class NackPacket extends Packet {

    private List<Integer> sequenceNumbers; // List of missing sequence numbers

    public NackPacket() {
        super((byte) 0xA0); // Set the Packet ID for NACK (example: 0xA0)
        this.sequenceNumbers = new ArrayList<>();
    }

    public void addSequenceNumber(int sequenceNumber) {
        sequenceNumbers.add(sequenceNumber);
    }

    public List<Integer> getSequenceNumbers() {
        return sequenceNumbers;
    }

    @Override
    public void decode(ByteBuffer buffer) {
        // Reset and decode the packet
        sequenceNumbers.clear();
        byte packetId = buffer.get(); // Read the Packet ID
        if (packetId != this.packetId) {
            throw new IllegalArgumentException("Invalid Packet ID for NackPacket");
        }

        // Decode sequence numbers
        int count = buffer.getShort(); // Number of sequence numbers
        for (int i = 0; i < count; i++) {
            sequenceNumbers.add(buffer.getInt());
        }
    }

    @Override
    public void encode(ByteBuffer buffer) {
        buffer.clear(); // Reset buffer
        buffer.put(packetId); // Write the Packet ID

        // Write sequence numbers
        buffer.putShort((short) sequenceNumbers.size()); // Number of sequence numbers
        for (int sequenceNumber : sequenceNumbers) {
            buffer.putInt(sequenceNumber);
        }

        buffer.flip(); // Prepare buffer for sending
    }
}
