package chatNI;

import java.io.*;
import java.net.*;

public class TCPServer extends Thread {

    private ServerSocket serverSocket;
    private final ChatNI chatNI;
    private boolean canRun;

    public TCPServer(ChatNI chatNI) {
        this.canRun = true;
        this.chatNI = chatNI;
        try {
			serverSocket = new ServerSocket(UDPReceiver.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void run() {
        while (canRun) {
            Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				(new TCPReceiver(clientSocket, chatNI)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void close() {
        this.canRun = false;
        try {
			this.serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
