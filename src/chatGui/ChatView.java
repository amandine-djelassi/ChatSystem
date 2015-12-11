package chatGui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.User;

public class ChatView extends JFrame implements ActionListener{
	private JPanel disconnect;
	private JButton bDisconnect;
	private JButton bSend;
	private JButton bSendFile;
	private JPanel send;
	private JTextArea textToSend;
	public JTextArea textReceived;
	private String mess;
	private ChatGui gui;
	private JList list;
	private DefaultListModel<User> listModel;
	private List<User> userList; 
	
	public ChatView(ChatGui gui){
		this.gui = gui;
		initComponent();
	}
	
	public void addToUserList(User user) {
		listModel.addElement(user);
	}
	
	public void removeFromUserList(User user){
		if(listModel.contains(user)){
			listModel.removeElement(user);
		}	
	}
	
	public void removeAllFromUserList(){
		listModel.removeAllElements();
	}
	
	private void initComponent(){
		listModel = new DefaultListModel<User>();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBorder(null);
		
		bSend = new JButton ("Send message");
		bSend.addActionListener(this);
		send = new JPanel();
		send.setBackground(Color.WHITE);

		bDisconnect = new JButton ("Disconnect");
		bDisconnect.addActionListener(this);
		disconnect = new JPanel();
		disconnect.setBackground(Color.WHITE);
		
		bSendFile = new JButton ("Send a file");
		bSendFile.addActionListener(this);

		textToSend = new JTextArea();
		textToSend.setLineWrap(true);
		textToSend.setBorder(BorderFactory.createLineBorder(Color.black));
		textToSend.addKeyListener(keyListener);
		JScrollPane textToSendScroller = new JScrollPane(textToSend);
		
		textReceived = new JTextArea();
		textReceived.setLineWrap(true);	
		textReceived.setEnabled(false);
		textReceived.setDisabledTextColor(Color.BLACK);
		textReceived.setBorder(BorderFactory.createLineBorder(Color.black));
		JScrollPane textReceivedScroller = new JScrollPane(textReceived);
		
		this.setTitle("Chat System");

		GridBagLayout repartiteur = new GridBagLayout();
	    GridBagConstraints gbc;
	    Container interieur = getContentPane();
	    interieur.setLayout(repartiteur); 
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.ipady = 15;
	    repartiteur.setConstraints(bDisconnect, gbc);
	    interieur.add(bDisconnect);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.15;
	    repartiteur.setConstraints(disconnect, gbc);
	    interieur.add(disconnect);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.ipady = 15;
	    repartiteur.setConstraints(bSend, gbc);
	    interieur.add(bSend);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.ipady = 15;
	    repartiteur.setConstraints(bSendFile, gbc);
	    interieur.add(bSendFile);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridheight=1;
		gbc.gridwidth=2;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.15;
	    repartiteur.setConstraints(send, gbc);
	    interieur.add(send);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridheight = 8;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.15;
	    gbc.weighty = 0.7;
	    repartiteur.setConstraints(listScroller, gbc);
	    interieur.add(listScroller);
	    
    	gbc = new GridBagConstraints();
	    gbc.gridx=2;
		gbc.gridy=0;
		gbc.gridheight=7;
		gbc.gridwidth=13;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.85;
	    gbc.weighty = 0.7;
	    repartiteur.setConstraints(textReceivedScroller, gbc);
	    interieur.add(textReceivedScroller); 
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 2;
	    gbc.gridy = 7;
	    gbc.gridheight = 3;
	    gbc.gridwidth = 13;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.85;
	    gbc.weighty = 0.3;
	    repartiteur.setConstraints(textToSendScroller, gbc);
	    interieur.add(textToSendScroller);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	gui.disconnect();
            }
	    });
	    setSize(600,450);
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
	    this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);    
	    setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bSend){
			enter();
		}	
		else if(source == bDisconnect){
			gui.disconnect();
		}
		else if(source == bSendFile){
/*			mess = this.textToSend.getText();
			this.textToSend.setText("");
			userList = list.getSelectedValuesList();
			gui.file(new File(mess), userList);*/
			userList = list.getSelectedValuesList();
			
			if(userList.toString() == "[]"){
				userList = new ArrayList<User>();
				for(int i=0; i<listModel.getSize(); i++){
					User u;
					u = listModel.getElementAt(i);
					this.userList.add(u);
				}
			}
						
			JFileChooser filechoose = new JFileChooser();
			filechoose.setCurrentDirectory(new File(".")); // Le répertoire source du JFileChooser est le répertoire d'où est lancé notre programme
			String approve = new String("ENVOYER"); // Le bouton pour valider l'enregistrement portera la mention ENREGSITRER
			int resultatEnregistrer = filechoose.showDialog(filechoose, approve); // Pour afficher le JFileChooser...
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) // Si l'utilisateur clique	sur le bouton ENREGSITRER
			{ 
			   String path= new String(filechoose.getSelectedFile().toString());
			   gui.file(new File(path), userList);
			   
			}		
				
		}
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

	    private void enter(){
	    	mess = this.textToSend.getText();
	    	mess = mess.replaceAll("\n","");
	    	if(mess.equals("")){
	    		
	    	}
	    	else{
	    		this.textToSend.setText("");
				userList = list.getSelectedValuesList();
				//TODO a tester avec un autre ordinateur ! 
				if(userList.toString() == "[]"){
					userList = new ArrayList<User>();
					for(int i=0; i<listModel.getSize(); i++){
						User u;
						u = listModel.getElementAt(i);
						this.userList.add(u);
					}
				}
				gui.message(mess, userList);
	    	}
	    }
}
