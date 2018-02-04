import java.io.Serializable;

public class Packet implements Serializable{

    private byte[] data;
    private int number;

    Packet (byte[] data, int number) {
        this.data = data;
        this.number = number;
    }

    public byte[] getData() {
        return data;
    }

    public int getNumber() {
        return number;
    }
}
