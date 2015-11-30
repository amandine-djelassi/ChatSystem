package chatGui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ConnectView extends JFrame implements ActionListener{
	private JButton connect;
	private JLabel nickname;
	private JTextArea textNickname;
	private String nick;
	private ChatGui gui;
	
	public ConnectView(ChatGui gui){
		initComponent();
		this.gui = gui;
	}
	
	private void initComponent(){
		connect = new JButton ("Connect ");
		nickname = new JLabel ("Enter nickname");
		textNickname = new JTextArea();
		
		this.setVisible(true);
		
		this.setLayout(new GridLayout(3, 1));
	    this.getContentPane().add(nickname);
	    this.getContentPane().add(textNickname);
	    this.getContentPane().add(connect);
	    this.pack();
	    connect.addActionListener(this);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == connect){
			nick = 	this.textNickname.getText();
			gui.connect(nick);
		}		
	}
}
