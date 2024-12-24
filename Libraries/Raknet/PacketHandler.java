import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import PocketDroid.Libraries.Raknet.Packet;

public class PacketHandler {

    private final Map<Byte, Class<? extends Packet>> packetRegistry = new HashMap<>();

    // Register a packet type with its packet ID
    public void registerPacket(byte packetId, Class<? extends Packet> packetClass) {
        packetRegistry.put(packetId, packetClass);
    }

    // Decode a packet from ByteBuffer
    public Packet decodePacket(ByteBuffer buffer) throws Exception {
        if (buffer.remaining() < 1) {
            throw new IllegalArgumentException("Buffer is too short to decode a packet.");
        }

        byte packetId = buffer.get(); // Read the Packet ID
        Class<? extends Packet> packetClass = packetRegistry.get(packetId);

        if (packetClass == null) {
            throw new IllegalArgumentException("Unknown Packet ID: " + packetId);
        }

        // Create a new instance of the packet and decode it
        Packet packet = packetClass.getDeclaredConstructor().newInstance();
        packet.decode(buffer);
        return packet;
    }

    // Encode a packet to ByteBuffer
    public ByteBuffer encodePacket(Packet packet) {
        ByteBuffer buffer = ByteBuffer.allocate(1024); // Allocate an appropriate size
        packet.encode(buffer);
        return buffer;
    }

    // Handle incoming packets
    public void handlePacket(Packet packet) {
        if (packet instanceof PingPacket) {
            handlePing((PingPacket) packet);
        } else if (packet instanceof PongPacket) {
            handlePong((PongPacket) packet);
        } else if (packet instanceof ConnectionRequestPacket) {
            handleConnectionRequest((ConnectionRequestPacket) packet);
        } else if (packet instanceof ConnectionAcceptedPacket) {
            handleConnectionAccepted((ConnectionAcceptedPacket) packet);
        } else if (packet instanceof AckPacket) {
            handleAck((AckPacket) packet);
        } else if (packet instanceof NackPacket) {
            handleNack((NackPacket) packet);
        } else {
            System.out.println("Unhandled packet: " + packet.getClass().getSimpleName());
        }
    }

    // Specific packet handling methods
    private void handlePing(PingPacket packet) {
        System.out.println("Received PingPacket with timestamp: " + packet.getTimestamp());
    }

    private void handlePong(PongPacket packet) {
        System.out.println("Received PongPacket with timestamps: Ping = " +
                packet.getPingTimestamp() + ", Server = " + packet.getServerTimestamp());
    }

    private void handleConnectionRequest(ConnectionRequestPacket packet) {
        System.out.println("Received ConnectionRequestPacket with Client ID: " + packet.getClientId());
    }

    private void handleConnectionAccepted(ConnectionAcceptedPacket packet) {
        System.out.println("Received ConnectionAcceptedPacket with Server Timestamp: " +
                packet.getServerTimestamp());
    }

    private void handleAck(AckPacket packet) {
        System.out.println("Received AckPacket with sequence numbers: " + packet.getSequenceNumbers());
    }

    private void handleNack(NackPacket packet) {
        System.out.println("Received NackPacket with sequence numbers: " + packet.getSequenceNumbers());
    }
}
