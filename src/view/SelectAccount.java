package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.account;

import javax.swing.ImageIcon;

public class SelectAccount extends JFrame{
	private volatile static SelectAccount instance = null;
	SigningUpView signingUpViewing;
	account signUpData,registeredData;
	LoggingInView loggingInViewing;
	ArtistLoggingInView artistLoggingInView;
	public JButton btnSignUp,btnRegisteredAccount, ArtistAccountbtn;
	private JButton button;
	
	
	public static SelectAccount getInstance() {
        if (instance == null) {
        	instance = new SelectAccount();
        }
		return instance;
	}
	
	
	public SelectAccount() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelectAccount.class.getResource("/images/spotify.png")));
		setTitle("Not So Spotify ");
		
		signingUpViewing = new SigningUpView();
		loggingInViewing = new LoggingInView();
//		artistLoggingInView = new ArtistLoggingInView();
		JPanel backgroundPanel = new JPanel();
		getContentPane().add(backgroundPanel, BorderLayout.CENTER);
		backgroundPanel.setLayout(null);
		
		
		JLabel applicationName = new JLabel("Not So");
		applicationName.setFont(new Font("Palatino Linotype", Font.PLAIN, 29));
		applicationName.setBackground(Color.WHITE);
		applicationName.setBounds(162, 55, 87, 62);
		backgroundPanel.add(applicationName);
		
		btnRegisteredAccount = new JButton("Registered Account");
		btnRegisteredAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnRegisteredAccount.setBounds(162, 236, 246, 38);
		backgroundPanel.add(btnRegisteredAccount);
		
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnSignUp.setBounds(162, 187, 246, 38);

		backgroundPanel.add(btnSignUp);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SelectAccount.class.getResource("/images/spotify.png")));
		label.setFont(new Font("Tahoma", Font.PLAIN, 29));
		label.setBackground(Color.WHITE);
		label.setBounds(279, 35, 129, 129);
		backgroundPanel.add(label);
		
		ArtistAccountbtn = new JButton("Artist Account");
		ArtistAccountbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));

		ArtistAccountbtn.setBounds(162, 334, 246, 38);

		backgroundPanel.add(ArtistAccountbtn);
		
		button = new JButton("Sign Up (Artist)");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(162, 283, 246, 38);
		backgroundPanel.add(button);

		this.setVisible(true);
		this.setSize(593, 485);
		
		//Making the action listeners
		btnSignUp.addActionListener(new signUpButton());
		btnRegisteredAccount.addActionListener(new registeredButton());
		ArtistAccountbtn.addActionListener(new btnArtist());

	}
	
	class signUpButton implements ActionListener
	{
	public void actionPerformed (ActionEvent e)
		{
		System.out.println("Click");
		signingUpViewing.setVisible(true);
		System.out.println("After Click");
		}
}
	public void addAccount(SigningUpView signingUpViewing) {
		this.signingUpViewing = signingUpViewing;
	}
	
	class registeredButton implements ActionListener //LOGGING IN
	{
	public void actionPerformed (ActionEvent e)
		{
		System.out.println("Click Two");
		loggingInViewing.setVisible(true);
		System.out.println("After Click Two");
		}
}
	public void loggingInAccount(LoggingInView loggingInViewing) {
		this.loggingInViewing = loggingInViewing;
	}
	
	class btnArtist implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			artistLoggingInView.setVisible(true);
		}
		
	}
	
	public void closingWindow() {
		this.setVisible(false);
	}
}

