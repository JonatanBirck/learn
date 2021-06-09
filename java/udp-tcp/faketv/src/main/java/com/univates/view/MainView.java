package com.univates.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.univates.models.User;

public class MainView extends JFrame 
{
    private User user;

    private JPanel container;
    private JLabel titleMyIP;
    private JTextField myIP;
    private JLabel titleMyPassword;
    private JTextField myPassword;
    private JLabel change;
    private JLabel titleClientIP;
    private JTextField clientIP;
    private JLabel titleClientPassword;
    private JTextField clientPassword;
    private JButton connect;    

    public MainView() 
    {        
        init();
    }

    public MainView( User user ) 
    {        
        this.user = user;
        init();
    }    

    private void init()
    {
        setTitle("Fake Team Viewer");
        setIconImage( new ImageIcon( getClass().getResource("../img/fakeTeamViewer64.png") ).getImage() );
        setBounds(0, 0, 440, 250);
        setVisible( true );
        setLocationRelativeTo( null );
        setResizable( false );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
        
        container = new JPanel();
        container.setLayout( null ); // absolute
        container.setBackground( Color.decode("#aed7f4") );
        add( container );

        // my ip
        titleMyIP = new JLabel("Meu IP:");
        titleMyIP.setBounds(25, 25, 150, 10);
        titleMyIP.setFont( new Font("Times new Roman", Font.BOLD, 14 ) );
        titleMyIP.setVisible(true);
        container.add( titleMyIP );   

        myIP = new JTextField( this.user.getIp().getHostAddress() );
        myIP.setBounds(30, 45, 150, 30);
        myIP.setFont( new Font("Times new Roman", Font.BOLD, 20 ) );
        myIP.setEditable( false );
        myIP.setBackground( Color.decode("#aed7f4") );        
        myIP.setVisible(true);
        container.add( myIP ); 
        
        // my password
        titleMyPassword = new JLabel("Minha senha:");
        titleMyPassword.setBounds(225, 25, 150, 10);
        titleMyPassword.setFont( new Font("Times new Roman", Font.BOLD, 14 ) );
        titleMyPassword.setVisible(true);
        container.add( titleMyPassword );   

        myPassword = new JTextField( this.user.getPassword() );
        myPassword.setBounds(230, 45, 150, 30);
        myPassword.setFont( new Font("Times new Roman", Font.BOLD, 20 ) );
        myPassword.setEditable( false );
        myPassword.setBackground( Color.decode("#aed7f4") );       
        myPassword.setVisible(true);
        container.add( myPassword ); 

        change = new JLabel();
        change.setBounds(385, 45, 30, 30);
        change.setIcon( new ImageIcon( getClass().getResource("../img/change.png") ) );
        change.setVisible(true);
        container.add( change );

        change.addMouseListener( new MouseListener()  
        {  
            @Override
            public void mouseClicked(MouseEvent e) {
                changePassword();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                change.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                change.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
                
            }  
        }); 

        // connect ip
        titleClientIP = new JLabel("Acessar IP:");
        titleClientIP.setBounds(25, 95, 150, 10);
        titleClientIP.setFont( new Font("Times new Roman", Font.BOLD, 14 ) );
        titleClientIP.setVisible(true);
        container.add( titleClientIP );   

        clientIP = new JTextField();
        clientIP.setBounds(30, 115, 150, 30);
        clientIP.setFont( new Font("Times new Roman", Font.BOLD, 20 ) );     
        clientIP.setVisible(true);
        container.add( clientIP ); 

        // connect password
        titleClientPassword = new JLabel("Senha:");
        titleClientPassword.setBounds(225, 95, 150, 10);
        titleClientPassword.setFont( new Font("Times new Roman", Font.BOLD, 14 ) );
        titleClientPassword.setVisible(true);
        container.add( titleClientPassword );   

        clientPassword = new JTextField();
        clientPassword.setBounds(230, 115, 150, 30);
        clientPassword.setFont( new Font("Times new Roman", Font.BOLD, 20 ) );    
        clientPassword.setVisible(true);
        container.add( clientPassword ); 

        connect = new JButton("Acessar");
        connect.setBounds(155, 165, 100, 25);
        connect.setVisible( true );
        container.add( connect );

        repaint();
    }

    private void changePassword()
    {
        this.user.changePassword();
        this.myPassword.setText( this.user.getPassword() );
        this.myPassword.repaint();
    }
 
}
