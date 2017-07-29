package chamberofsecrets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class chamberofsecrets {
	final static String projectPath = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		System.load(projectPath+"/libs/hello.dll");
		
		String fullFilePath;
		ServerSocket welcomeSocket = null;
		long userSeq = 0;
		String userId = "";
		try {
			System.out.println("The Camber Of Secrets Starts");
			welcomeSocket = new ServerSocket(6789);
			while (true) {
				System.out.println("Listen Start!!!");
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				
				userSeq++;
				userId = inFromClient.readLine();
				fullFilePath = inFromClient.readLine();
				System.out.println("Received: " + fullFilePath);
				
				MyLockerThread mrc = new MyLockerThread(userId, fullFilePath);
				mrc.start();
			}
		} catch (Exception e) {
			try {
				welcomeSocket.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
