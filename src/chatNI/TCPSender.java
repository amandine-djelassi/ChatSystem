package chatNI;

import java.io.*;
import java.net.*;

public class TCPSender extends Thread {
    
    private Socket socket;
    private File file;
    
    public TCPSender(InetAddress add, File file) {
        this.file = file;
        this.socket = null;
        try {
			this.socket = new Socket(add, UDPReceiver.PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void run() {
            DataOutputStream outputStream;
			try {
				outputStream = new DataOutputStream(this.socket.getOutputStream());
	            try (FileInputStream fileInputStream = new FileInputStream(file)) {
	                byte[] buffer = new byte[1];
	                while (fileInputStream.read(buffer) > -1) {
	                    this.socket.getOutputStream().write(buffer);
	                }
	            }
	            outputStream.flush();
	            this.socket.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }
}