package chatNI;

import java.net.InetAddress;

public interface FromToRemoteApp {
	public void hello(String nickname, Boolean ReqReply, InetAddress address);
	public void bye(InetAddress address);
}
