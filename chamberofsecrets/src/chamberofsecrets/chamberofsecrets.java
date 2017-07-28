package chamberofsecrets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class chamberofsecrets {
	public static void main(String[] args) {
		System.load("C:\\Users\\Kait\\git\\chamberofsecrets-java\\chamberofsecrets\\src\\hello.dll");
		
		String fullFilePath;
		ServerSocket welcomeSocket = null;
		try {
			System.out.println("The Camber Of Secrets Starts");
			welcomeSocket = new ServerSocket(6789);
			while (true) {
				System.out.println("Listen Start!!!");
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				fullFilePath = inFromClient.readLine();
				System.out.println("Received: " + fullFilePath);
				
				MyRunnableClass mrc = new MyRunnableClass();
				mrc.setFilepath(fullFilePath);
				new Thread(mrc).start();
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
