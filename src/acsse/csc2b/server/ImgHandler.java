package acsse.csc2b.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * ImgHandler implements the runnable interface from threads received from client connections
 * Handles client and server communication
 * @author Nogantsho, Y 201602466
 *@version P04
 */
public class ImgHandler implements Runnable{
//attributes
	Socket connection;
	PrintWriter pw;
	BufferedReader br;
	DataInputStream dis;
	DataOutputStream dos;
	InputStream is;
	OutputStream os;
	/**
	 * Constructor handles initialization of variables
	 * @param socket 
	 */
	public ImgHandler(Socket socket) {
		this.connection = socket;
		try {
			os = connection.getOutputStream();
		    pw = new PrintWriter((os),true);
		    is = connection.getInputStream();
		    br = new BufferedReader(new InputStreamReader(is));
		    dos = new DataOutputStream(os);
		    dis = new DataInputStream(is);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
	     //Making file downloadable to client
		System.out.println("Handling client requests(downloading file...)");
		boolean processing = true;
		try {
			while(processing) {
				//read the string input of user which should be download+id
				String message = br.readLine();
				//output the message to standard output stream
				System.out.println("Message "+ message);
				//Download 2
				StringTokenizer st = new StringTokenizer(message);
				String command = st.nextToken().toUpperCase();
				switch(command) {
				case "DOWNLOAD":
					String fileID = st.nextToken();
					String fileName = "" ;
					File FileLst = new File("data/ImgList.txt");
					Scanner sc = new Scanner(FileLst);
					String line = "";
					while(sc.hasNext()) {
						line = sc.nextLine();
						StringTokenizer st2 = new StringTokenizer(line);
						String ID = st2.nextToken();
						String fName = st2.nextToken();
						if(ID.equals(fileID)) {
							fileName = fName;
						}
					}
					sc.close();
					System.out.println("Name of file requested :" + fileName);
					
					File returnFile = new File("data/"+ fileName);
					if(returnFile.exists()) {
						pw.println(returnFile.length());
						
						FileInputStream fis = new FileInputStream(returnFile);
						byte[] buffer = new byte[1024];
						int n =0;
						while((n= fis.read(buffer))>0) {
							dos.write(buffer,0,n);
							dos.flush();
						}
						fis.close();
						System.out.println("File had been sent to client!");
 					}
					else 
					if(!returnFile.exists()){
						System.out.println("File does not exists");
					}
					break;
				case "UPLOAD":
					FileOutputStream fos = null;
					try {
						//read in ID Filename size txt
						int ID = Integer.parseInt(br.readLine());
						String FName = br.readLine();
						Integer FSize = Integer.parseInt(br.readLine());
						System.out.println("Read in values from client");
						
						//Create new file
						File DFile = new File("data/"+ FName);
						//retrieve txt file
						fos = new FileOutputStream(DFile);
						byte[] buffer = new byte[1024];
						int n =0;
						int totalbytes =0;
						
						while(totalbytes!=FSize) {
							n= dis.read(buffer, 0, buffer.length);
							fos.write(buffer,0,n);
							fos.flush();
							totalbytes +=n;
							
						}
						System.out.println("The File "+ FName +" has been uploaded to Server");
						fos.close();
						//inform user of the status of upload
						pw.println("Uploading...");
						System.out.println("UPLOADING");
						
						PrintWriter bw= new PrintWriter(new BufferedWriter(new FileWriter("data/ImgList.txt",true)));
						pw.println(ID + " " + FName);
						bw.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					default:
					break;
				}
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
