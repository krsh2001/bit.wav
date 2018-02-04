import java.net.*;
import java.io.*;

class ClientSocket extends Socket{

	private ServerSocket sockListen;
	private PrintWriter writer;
	private BufferedReader reader;
	private ByteArrayInputStream byteReader;
	private ByteArrayOutputStream byteWriter;
	private InetAddress serverAddress;
	private String[] peers;
	private int fileSize;
	private boolean dl;
	private int tasks[], peerTasks[][];
	private String filename;
	boolean receivedTasks[];

	public ClientSocket(int port, InetAddress servAddr;){
		super();
		dl = false;
		sockListen = new Socket(port);
	}


	//Sends the server data on files present
	boolean checkFile(int hash){
		File loc = new File("local_files/");
		File[] files = loc.listFiles();
		String chksm[] = new String[files.length()];
		if (file == null){
			return false;
		} else {
			for (int f = 0 ; f < files.length() ; f++){
				if (files[f].hashCode() == hash){
					try{
						writer = new PrintWriter(getOutputAddress());
						writer.println (true);
						writer.close();
						return true;
					} catch (UnknownHostException e){
						System.out.println ("Unknown host: " + e.getMessage());
					} catch (IOException e){
						System.out.println ("IO Error: " + e.getMessage());
					}
					break;
				}
			}
		}
		return false;

	}

	//Requests a list of available files from the server
	String requestFileList(){
		String list = "";
			try{
                              writer = new PrintWriter(getOutputAddress());
                               //Signal the server for file list
			       writer.println ("|f");
                               writer.close();
				reader = new BufferedReader (getInputAddress());
				try{
					while (true){
						list += reader.readLine()+'\n';
					}

				} catch(Exception e){}
				reader.close();
                                       
                       } catch (UnknownHostException e){
			       return null;
                                System.out.println ("Unknown host: " + e.getMessage());
                        } catch (IOException e){
				return null;
				System.out.println ("IO Error: " + e.getMessage());
                        }
		return list;
	}

	//Sends a file request to the server
	boolean sendRequest(String filename){
		try{
			writer = new PrintWriter(getOutputAddress());
			//Signal the server for get
			writer.println("|g");
			writer.println(filename);
			this.filename = filename;
			writer.close();
			reader = new BufferedReader(getInputAddress());
			if (reader.getLine().equals("|d");
				return String.split(reader.getLine(), ';');
		} catch (UnknownHostException e){
			return null;
			System.out.println("Unknown host: " + e.getMessage());
		} catch (IOException e){
			return null;
			System.out.println("IO Error: " + e.getMessage());
		}
	}

	//Downloads directly from the server
	servDirect(String filename){
	}

	//Assigns tasks to the peer servers if dead, receives data
	receiveData(boolean init){
		Socket peer;
		boolean death = init;
		for (int i = 0 ; i < peers.length() ; i++){
			peer = new Socket(InetAddress.getbyAddress(ByteBuffer.allocate(4).putInt(Integer.parseInt(peers[i]).array())), 9637);
			try{
				writer = new PrintWriter (peer.getOutputAddress());
				writer.print("");
				writer.close();
			} catch (UnknownHostException e){
					death = true;
					int orphanedTasks[], orplen = 0;
					for (int z = 0 ; z < peerTasks[i].length(); z++){
						if (!receivedTasks(peertasks[i][z])){
							orplen++;
						}
					}
					orphanedTasks = new int[orplen];
					orplen = 0;
					for (int z = 0 ; z < peerTasks[i].length() ; z++){
						if (!receivedTasks(peerTasks[i][z])){
							orphanedTasks[z] = peerTasks[i][z];
						}
					}
					String temp[][] = new String[peers.length()-1][peers[0].length()+orphanedTasks.length()/peers.length()];
					int q = 0;
					for (int z = 0 ; z < peers.length() ; z++){
						if (z == i)
							continue;
						for (int y = 0 ; y < temp[0][0].length(); y++){
							if (y < peers.length())
								temp[q][y] = peers[z][y];
							else if (4 * (y-peers.length()) < orplen)
								temp[q][y] = orphanedTasks[4*(y-peers.length())];
						}
						q++;
					}
					i--;
					peers = temp;
			//END OF REDISTRIBUTION CHECK
			}
			if (peers.length() == 0){
				servDirect(filename);
				return;
			}
			if (death){
				peer = new Socket(InetAddress.getbyAddress(ByteBuffer.allocate(4).putInt(Integer.parseInt(peers[i]).array())),9637);
				try{
					writer = new PrintWriter(peer.getOutputAddress());
					writer.println("|o");
					for (int z = 0 ; z < peerTasks[i].length() ; z++){
						writer.println(peerTasks[i][z]);
					}
					writer.println("|c");
					for (int z = 0 ; z < receivedTasks.length();z++){
						if (IntStream.of(peerTasks[i][z]).anyMatch(x->x==z) && receivedTasks(z))
						{
							writer.println(z); //prints the tasks that need no completion
						}
					}
					writer.println("|s");
					writer.close();
				} catch (Exception e){ System.out.println(e.getMessage()); }
			}
			try{
				reader = new BufferedReader(peer.getInputAddress());
				byte data[] = //TODO
			}
		}
	}

	//Sends data corresponding to assigned tasks
	sendData(){

	}

	//Controls the flow of the program
	control(){
		Socket sock = null;
		//threaded
		while (true){
			try{
				sock = sockListen.accept();
			} catch(Exception e){}
			if (/*TRIGGERED TO REQUEST files*/){
				System.out.println(requestFileList());
			}
			if(/*TRIGGERED TO SEND REQUEST*/){
				receivedTasks = new int[Math.ceil(fileSize/1024)];
				peers = sendRequest(filename);
				for (int i = 0 ; i < peers.length() ; i++) {
					peerTasks[i] = new int[Math.ceil(fileSize/1024) / peers.length()];
				}
				for (int i = 0 ; i < Math.ceil(filesize/1024) ; i++){
					peertasks[i%peers.length()][i/(i%peers.length())] = i;
				}
				receiveData(true);
			}
				else
					servDirect(filename);
			}
			if (dl){
				receiveData(false);
			}
			if (sock != null && (dl)){
				try{
					code = new BufferedReader(sock.getInputStream()).getLine();
					if (code.charAt(0) == 'r'){
						code = code.substr(1);
					}
				} catch (Exception e){ continue; }
				peers = checkFile(code);
			}
		}
	}
}
