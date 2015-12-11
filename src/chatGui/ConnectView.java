package chatGui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ConnectView extends JFrame implements ActionListener{
	private JButton bconnect;
	private JPanel back;
	private JPanel connect;
	private JPanel textArea;
	private JLabel enterNickname;
	private JTextField textNickname;
	private String nick;
	private ChatGui gui;
	
	public ConnectView(ChatGui gui){
		initComponent();
		this.gui = gui;
	}
	
	private void initComponent(){
		Font textFont = new Font("Verdana",Font.ROMAN_BASELINE,12);
		Font nicknameFont = new Font("Serif",Font.BOLD, 20);
		Border nicknameBorder = new LineBorder(Color.black, 1); 
		
		//background JPanel
		back = new JPanel();
		
		//welcome message area
		enterNickname = new JLabel ("Please, enter your nickname");
		enterNickname.setHorizontalAlignment(JLabel.CENTER);
		enterNickname.setFont(textFont);
		enterNickname.setOpaque(false);
		
		//nickname text area
		textArea = new JPanel();		
		textNickname = new JTextField(9);
		textNickname.addKeyListener(keyListener);
		textNickname.setBorder(nicknameBorder);
		textNickname.setFont(nicknameFont); 
		textArea.add(textNickname,BorderLayout.CENTER);
		textArea.setOpaque(false);
		
		//connect button 
		connect = new JPanel();
		bconnect = new JButton ("Connect");
		bconnect.addActionListener(this);
		bconnect.setPreferredSize(new Dimension(130, 40));
		bconnect.setOpaque(false);
		connect.add(bconnect,BorderLayout.CENTER);
		connect.setOpaque(false);
		
		//view creation
		back.setLayout(new GridLayout(3, 1));
		back.add(enterNickname);
	    back.add(textArea);  
	    back.add(connect);
	    back.setBackground(Color.getHSBColor(0.6f, 0.5f, 1f)); 
	    
	    //JFrame
	    this.setLayout(new GridLayout(1,1));
	    this.getContentPane().add(back);
	    this.setSize(225,215);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
	    this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/3);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	private void enter(){
		nick = this.textNickname.getText();
		nick = nick.replaceAll("\n","");
		gui.connect(nick);
	}
	
	KeyListener keyListener = new KeyListener() {
		public void keyReleased(KeyEvent e) {
			  if (e.getKeyCode() == KeyEvent.VK_ENTER){
				  enter();
			 }
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}
	};
	    
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bconnect){
			enter();
		}		
	}
	
}
