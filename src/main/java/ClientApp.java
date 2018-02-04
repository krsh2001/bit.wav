import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.Socket;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {
        int biggestPack;
        RandomAccessFile input = null;
        int packNum = 0;
        try {
            Socket client = new Socket(InetAddress.getByAddress(new byte[]{(byte) 172, (byte) 17, (byte) 51, (byte) 231}), 4321);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            while(true){
                input = new RandomAccessFile(new File("drums.wav"), "r");
                biggestPack = (int) Math.ceil(input.length() / 1024.0);
                input.setLength(biggestPack * 1024);
                byte[] data = new byte[1024];
                input.readFully(data, packNum * 1024, 1024);

                Packet p = new Packet(data, packNum);

                out.writeObject(p);

                if (packNum < biggestPack)
                    packNum++;
                else
                    break;
            }
            client.close();
            out.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }catch (Exception e){System.out.println("fuck");}
    }
}
