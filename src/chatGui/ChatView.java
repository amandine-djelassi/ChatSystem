package chatGui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import model.User;

public class ChatView extends JFrame implements ActionListener{
	private JPanel disconnect;
	private JButton bDisconnect;
	private JButton bSend;
	private JPanel send;
	private JTextArea textToSend;
	public JTextArea textReceived;
	private String mess;
	private ChatGui gui;
	private JList list;
	private DefaultListModel<String> listModel;
	private List<User> userList; 
	
	public ChatView(ChatGui gui){
		this.gui = gui;
		initComponent();
	}
	
	public void refreshUserList(List<String> userList) {
		listModel.removeAllElements();
		for(String user : userList){
			listModel.addElement(user);
		}
	}
	
	private void initComponent(){
		listModel = new DefaultListModel<String>();
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBorder(null);
		
		bSend = new JButton ("Send");
		bSend.addActionListener(this);
		send = new JPanel();
		send.setBackground(Color.WHITE);

		bDisconnect = new JButton ("Disconnect");
		bDisconnect.addActionListener(this);
		disconnect = new JPanel();
		disconnect.setBackground(Color.WHITE);

		textToSend = new JTextArea();
		textToSend.setLineWrap(true);
		textToSend.setBorder(BorderFactory.createLineBorder(Color.black));
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
		gbc.gridwidth=3;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.ipady = 30;
	    repartiteur.setConstraints(bDisconnect, gbc);
	    interieur.add(bDisconnect);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 3;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.2;
	    repartiteur.setConstraints(disconnect, gbc);
	    interieur.add(disconnect);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridheight=1;
		gbc.gridwidth=3;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
	    gbc.ipady = 30;
	    repartiteur.setConstraints(bSend, gbc);
	    interieur.add(bSend);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 9;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 3;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.2;
	    repartiteur.setConstraints(send, gbc);
	    interieur.add(send);
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 3;
	    gbc.gridheight = 8;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.2;
	    gbc.weighty = 0.7;
	    repartiteur.setConstraints(listScroller, gbc);
	    interieur.add(listScroller);
	    
    	gbc = new GridBagConstraints();
	    gbc.gridx=3;
		gbc.gridy=0;
		gbc.gridheight=7;
		gbc.gridwidth=12;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.8;
	    gbc.weighty = 0.7;
	    repartiteur.setConstraints(textReceivedScroller, gbc);
	    interieur.add(textReceivedScroller); 
	    
	    gbc = new GridBagConstraints();
	    gbc.gridx = 3;
	    gbc.gridy = 7;
	    gbc.gridwidth = 12;
	    gbc.gridheight = 3;
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.weightx = 0.8;
	    gbc.weighty = 0.3;
	    repartiteur.setConstraints(textToSendScroller, gbc);
	    interieur.add(textToSendScroller);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(600,450);
	    setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == bSend){
			mess = this.textToSend.getText();
			this.textToSend.setText("");
			userList = list.getSelectedValuesList();
			//recupère item selectionne 
			gui.message(mess, userList);
			//ouvrir nouvelle fenetre 
		}	
		else if(source == bDisconnect){
			gui.disconnect();
		}
	}
}
