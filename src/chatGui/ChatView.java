package chatGui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatView extends JFrame implements ActionListener{
	private JButton bDisconnect;
	private JButton bSend;
	private JLabel nickname;
	private JTextArea textToSend;
	private JTextArea textReceived;
	private String nick;
	private ChatGui gui;
	private JList list;
	
	
	public ChatView(ChatGui gui){
		this.gui = gui;
		initComponent();
	}
	
	private void initComponent(){		
		/*list = new JList(gui.getUserList());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		*/
		bSend = new JButton ("send ");
		bDisconnect = new JButton ("Disconnect ");
		textToSend = new JTextArea();
		textReceived = new JTextArea();
	/*	
		this.setLayout(new GridLayout(1, 1));
	    this.getContentPane().add(list);
	    */
		this.setTitle("Chat Sytem");
		
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		
		//On crée nos différents conteneurs
		JPanel bDisconnect = new JPanel();
		bDisconnect.setPreferredSize(new Dimension(180,60));
		bDisconnect.setBackground(Color.black);
		JPanel listUser = new JPanel();
		listUser.setPreferredSize(new Dimension(180,540));
		listUser.setBackground(Color.BLUE);
		JPanel fenetre = new JPanel();
		fenetre.setPreferredSize(new Dimension(720,420));
		fenetre.setBackground(Color.DARK_GRAY);
		JPanel ecrire = new JPanel();
		ecrire.setPreferredSize(new Dimension(720,180));
		ecrire.setBackground(Color.GREEN);
		JPanel bSend = new JPanel();
		bSend.setPreferredSize(new Dimension(180,60));
		bSend.setBackground(Color.MAGENTA);
		
		//le coteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(900,600));
		content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//on positionnela case d edépart du composant 
		gbc.gridx=0;
		gbc.gridy=0;
		//la taille en hauteur et en largeur
		gbc.gridheight=2;
		gbc.gridwidth=3;
		content.add(bDisconnect, gbc);
		
		//--------
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridheight=8;
		gbc.gridwidth=3;
		content.add(listUser, gbc);
		//--------
		gbc.gridx=3;
		gbc.gridy=0;
		gbc.gridheight=7;
		//Cette instruction informe le layout que c'est une fin de ligne
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		content.add(fenetre, gbc);
		//--------
		gbc.gridx=3;
		gbc.gridy=7;
		gbc.gridheight=3;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		content.add(ecrire, gbc);
		//--------
		gbc.gridx=12;
		gbc.gridy=9;
		gbc.gridheight=1;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		content.add(bSend, gbc);
		
		this.setContentPane(content);
		
		
		this.setVisible(true);
		
	    this.pack();
	  //  bSend.addActionListener(this);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*Object source = e.getSource();
		if(source == connect){
			nick = 	this.textNickname.getText();
			gui.connect(nick);
			//ouvrir nouvelle fenetre 
		}		*/
	}
}
