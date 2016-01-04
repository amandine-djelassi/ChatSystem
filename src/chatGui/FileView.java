package chatGui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import model.User;

public class FileView extends JFrame implements ActionListener{
	private JButton yes;
	private JButton no;
	private JLabel demande;
	private ChatGui gui;
	private String nameFile;
	private User user;
	private int timestamp;
	private JPanel button;
	private JPanel back;
	
	
	public FileView(ChatGui gui, String nameFile, User user, int timestamp){
		this.gui = gui;
		this.nameFile = nameFile;
		this.user = user;
		this.timestamp = timestamp;
		initComponent();
	}
	
	private void initComponent(){
		back = new JPanel();
		yes = new JButton ("yes ");
		no = new JButton ("no ");
		demande = new JLabel ("Voulez Vous recevoir le fichier : "+nameFile+" de "+user.getNickname());
		button = new JPanel();
		
	    yes.addActionListener(this);
	    no.addActionListener(this);
		
	    button.add(yes);
	    button.add(no);
	    button.setOpaque(false);
	    
		back.setLayout(new GridLayout(2, 1));
		back.add(demande);
	    back.add(button);
	    back.setBackground(Color.getHSBColor(0.6f, 0.5f, 1f));	    
	   
	    this.setLayout(new GridLayout(1,1));
	    this.getContentPane().add(back);
	    this.pack();
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
	    this.setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/3);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	    addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	gui.responseFile(false,"",user, timestamp);
            }
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == yes){
			JFileChooser filechoose = new JFileChooser();
			File f = new File(nameFile);
			filechoose.setSelectedFile(f);
			filechoose.setCurrentDirectory(new File(".")); 
			String approve = new String("ENREGISTRER"); 
			int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION) 
			{ 
			   String path= new String(filechoose.getSelectedFile().toString());
			   gui.responseFile(true, path, user, timestamp);
			}
		}		
		else if (source == no){
			gui.responseFile(false,"",user, timestamp);
		}
	}
	
}
