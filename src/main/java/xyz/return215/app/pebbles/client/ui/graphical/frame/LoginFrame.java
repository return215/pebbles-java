package xyz.return215.app.pebbles.client.ui.graphical.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import xyz.return215.app.pebbles.client.ClientHandler;

public class LoginFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -1129274848935281997L;
    
    ClientHandler client;
    
    public LoginFrame(ClientHandler c) {
        client = c;
        initComponents();
    }
    
    private void initComponents() {
        JTextField txtUsername = new JTextField();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(360, 480));
        getContentPane().setLayout(new GridLayout(2, 0));
        
        JPanel PanelLogo = new JPanel();
        PanelLogo.setLayout(new BorderLayout());
        
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setIcon(new ImageIcon(getClass().getResource("/res/logo.jpg"))); // NOI18N
        PanelLogo.add(logo, BorderLayout.CENTER);
        
        getContentPane().add(PanelLogo);
        
        JPanel PanelUtama = new JPanel();
        PanelUtama.setLayout(new GridLayout(2, 0));
        
        JPanel PanelInput = new JPanel();
        PanelInput.setLayout(new GridLayout(3, 0));
        
        PanelInput.add(new JLabel());
        PanelInput.add(txtUsername);
        PanelInput.add(new JLabel());
        
        PanelUtama.add(PanelInput);
        
        JPanel PanelProcess = new JPanel();
        PanelProcess.setLayout(new GridLayout(2, 0));
        
        JPanel PanelAtas = new JPanel();
        PanelAtas.setLayout(new GridLayout(0, 3));
        PanelAtas.add(new JLabel());
        
        JButton btnStart = new JButton();
        btnStart.setText("START");
        
        // This is old school
        // btnStart.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent evt) { start(); }
        //
        // });
        
        // This is now, use lambdas for anonymous classes with one method
        btnStart.addActionListener((evt) -> tryLogin());
        PanelAtas.add(btnStart);
        PanelAtas.add(new JLabel());
        
        PanelProcess.add(PanelAtas);
        
        PanelUtama.add(PanelProcess);
        
        getContentPane().add(PanelUtama);
        
        pack();
    }
    
    private void tryLogin() {
        MainFrame m = new MainFrame(client);
        m.setVisible(true);
        
        this.dispose();
    }
    
}
