import java.net.*;
import java.io.*;

class ClientSocket extends Socket{

	private Socket sockListen;
	private PrintWriter writer;
	private BufferedReader reader;
	InetAddress serverAddress;

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
						writer = new PrintWriter(new FileWriter(getOutputAddress()));
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
                              writer = new PrintWriter(new FileWriter(getOutputAddress()));
                               //Signal the server for file list
			       writer.println ("f");
                               writer.close();
				reader = new BufferedReader (new FileReader(getInputAddress()));
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
			writer = new PrintWriter(new FileWriter(getOutputAddress()));
			//Signal the server for get
			writer.println("g" + filename);
			writer.close();
			reader = new BufferedReader(new FileReader(getInputAddress()));
			return Boolean.parseBoolean(reader.readLine());
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
		try{
			writer = new PrintWriter(new FileWriter(getOutputAddress()));
			//Signals server for a direct download
			writer.println("d"+filename);
			writer.close();
			File f = new File("local_files/"+filename);
			if (f.createNewFile()){
				writer = new PrintWriter(new FileWriter("local_files"+filename));
				reader = new BufferedReader(new FileReader(getInputAddress()));
				try{
					while (true){
						char[] buffer[];

						writer.print();
					}
				}
			}
		}
		return false;
	}

	//Receives the IPs of corresponding servers to the server
	receiveIPs(){

	}

	//Assigns tasks to the peer servers
	assignTasks(){

	}

	//Sends data corresponding to assigned tasks
	sendData(){

	}

	//Controls the flow of the program
	control(){
		//TODO: open the server
		
		//threaded
		while (/*ACTIVE*/){
			if(/* TRIGGERED TO SEND REQUEST*/){
				if (sendRequest(filename))
					receiveIPs(filename);
			}
		}
	}
}
