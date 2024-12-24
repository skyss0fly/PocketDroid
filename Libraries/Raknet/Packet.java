public abstract class Packet {
    protected byte packetId;
    protected ByteBuffer buffer;

    public Packet(byte packetId) {
        this.packetId = packetId;
        this.buffer = ByteBuffer.allocate(1024); // Example size
    }

    public abstract void decode(ByteBuffer buffer);
    public abstract void encode(ByteBuffer buffer);
}
