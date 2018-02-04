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

	public ClientSocket(int port, InetAddress servAddr;){
		super();
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
			       writer.println ("f");
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
			writer.println("g" + filename);
			writer.close();
			reader = new BufferedReader(getInputAddress());
			return String.split(reader, ';');
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

	//Assigns tasks to the peer servers
	assignTasks(){

	}

	//Sends data corresponding to assigned tasks
	sendData(){

	}

	//Controls the flow of the program
	control(){
		Socket sock = null;
		//threaded
		while (/*ACTIVE*/){
			try{
				sock = sockListen.accept();
			} catch(Exception e){}
			if (/*TRIGGERED TO REQUEST IP*/){
				System.out.println(requestFileList());
			}
			if(/*TRIGGERED TO SEND REQUEST*/){
				if (sendRequest(filename)){
					receiveIPs(filename);
				}
				else
					servDirect(filename);
			}
			if (/*CONTINUING A DOWNLOAD FROM PEERS*/){
				assignTasks();
			}
			if (sock != null){
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
