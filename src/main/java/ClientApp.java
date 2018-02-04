import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.Socket;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;
	private final byte[] SERVERIP = {(byte)172, (byte)17, (byte)51, (byte)43}
	public boolean confirmFile(String filename){
		try{
			Socket client = new Socket(InetAddress.getByAddress(SERVERIP),8008);
			PrintWriter output = new PrintWriter (client.getOutputStream());
		if(new File ("local_files/"+filename).exists()){
			output.print("true");
		return true;} else {
			output.print ("false");
		return false;}
		} catch (Exception e) {System.out.println (e.geMessage());}
	}

    private void sendFile(String filename, byte[] IP, int num, int denom) {
        int biggestPack;
        RandomAccessFile input = null;
        int packNum = num;
        try {
            Socket client = new Socket(InetAddress.getByAddress(IP, 4321);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            while(true){
                input = new RandomAccessFile(new File(filename), "r");
                biggestPack = (int) Math.ceil(input.length() / 1024.0);
                input.setLength(biggestPack * 1024);
                byte[] data = new byte[1024];
                input.readFully(data, packNum * 1024, 1024);

                Packet p = new Packet(data, packNum);

                out.writeObject(p);

                if (packNum < biggestPack)
                    packNum+= denom + 1;
                else
                    break;
            }
            client.close();
            out.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }catch (Exception e){System.out.println("ERROR");}
    }

	public static void main(String[] args){
		ServerSocket listener = new ServerSocket(4321);
		Socket client = listener.accept();
		while (true){
		try{
			BufferedReader input = new BufferedReader(client.getInputStream());
			String filename = input.readline();
			if (confirmFile(filename)){
				String[] IP = input.readLine().split(".");
				byte[] ip = new byte[IP.length];
				for (int i = 0 ; i < ip.length ; i++){
					ip[i] = Byte.parseByte(IP[i]);
				int num = Integer.parseInt(input.readLine());
				sendFile (filename, ip, num, Integer.parseInt(input.readLine()));
			}
		}
	} catch (Exception e){System.out.println(e.getMessage());}}
}
