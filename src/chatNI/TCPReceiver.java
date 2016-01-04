package chatNI;

import java.io.*;
import java.net.*;

public class TCPReceiver extends Thread {

    private final Socket socket;
    private final ChatNI chatNI;

    public TCPReceiver(Socket socket, ChatNI chatNI) {
        this.socket = socket;
        this.chatNI = chatNI;
    }

    @Override
    public void run() {
            FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(chatNI.getFileToReceived());
	            byte[] buffer = new byte[1];
                while (this.socket.getInputStream().read(buffer) >= 0) {
                    fileOutputStream.write(buffer);
                }
                this.chatNI.file(this.socket.getInetAddress(), chatNI.getFileToReceived());
                this.socket.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    }
}