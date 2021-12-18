package xyz.return215.app.pebbles.client.ui.graphical.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import xyz.return215.app.pebbles.client.ClientHandler;

public class LobbyFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -839812756883386023L;
    
    ClientHandler client;
    
    public LobbyFrame(ClientHandler c) {
        client = c;
        initComponents();
    }
    
    private void initComponents() {
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        getContentPane().setLayout(new GridLayout(0, 3));
        
        JPanel PanelKiri = new JPanel();
        PanelKiri.setLayout(new GridLayout(15, 0));
        
        JPanel PanelChat1 = new JPanel();
        PanelChat1.setLayout(new BoxLayout(PanelChat1, BoxLayout.X_AXIS));
        
        PanelChat1.add(new JLabel("User 1"));
        PanelChat1.add(new JLabel("Pesan 1"));
        
        PanelKiri.add(PanelChat1);
        
        JPanel PanelChat2 = new JPanel();
        PanelChat2.setLayout(new BoxLayout(PanelChat2, BoxLayout.X_AXIS));
        
        PanelChat2.add(new JLabel("User 2"));
        PanelChat2.add(new JLabel(": Pesan 1"));
        
        PanelKiri.add(PanelChat2);
        
        JPanel PanelChat3 = new JPanel();
        PanelChat3.setLayout(new BoxLayout(PanelChat3, BoxLayout.X_AXIS));
        
        PanelChat3.add(new JLabel("User 2"));
        PanelChat3.add(new JLabel(": Pesan 3"));
        
        PanelKiri.add(PanelChat3);
        
        JPanel PanelChat4 = new JPanel();
        PanelChat4.setLayout(new BoxLayout(PanelChat4, BoxLayout.X_AXIS));
        
        PanelChat4.add(new JLabel("User 1"));
        PanelChat4.add(new JLabel(": Pesan 4"));
        PanelKiri.add(PanelChat4);
        
        JPanel PanelChat5 = new JPanel();
        PanelChat5.setLayout(new BoxLayout(PanelChat5, BoxLayout.X_AXIS));
        
        PanelChat5.add(new JLabel("User 2"));
        PanelChat5.add(new JLabel(": Pesan 5"));
        PanelKiri.add(PanelChat5);
        
        JPanel PanelChat6 = new JPanel();
        PanelChat6.setLayout(new BoxLayout(PanelChat6, BoxLayout.X_AXIS));
        
        PanelChat6.add(new JLabel("User 1"));
        PanelChat6.add(new JLabel(": Pesan 6"));
        PanelKiri.add(PanelChat6);
        
        JPanel PanelChat7 = new JPanel();
        PanelChat7.setLayout(new BoxLayout(PanelChat7, BoxLayout.X_AXIS));
        
        PanelChat7.add(new JLabel("User 1"));
        PanelChat7.add(new JLabel(": Pesan 7"));
        PanelKiri.add(PanelChat7);
        
        JPanel PanelChat8 = new JPanel();
        PanelChat8.setLayout(new BoxLayout(PanelChat8, BoxLayout.X_AXIS));
        
        PanelChat8.add(new JLabel("User 1"));
        PanelChat8.add(new JLabel(": Pesan 8"));
        PanelKiri.add(PanelChat8);
        
        JPanel PanelChat9 = new JPanel();
        PanelChat9.setLayout(new BoxLayout(PanelChat9, BoxLayout.X_AXIS));
        
        PanelChat9.add(new JLabel("User 2"));
        PanelChat9.add(new JLabel(": Pesan 9"));
        PanelKiri.add(PanelChat9);
        
        JPanel PanelChat10 = new JPanel();
        PanelChat10.setLayout(new BoxLayout(PanelChat10, BoxLayout.X_AXIS));
        
        PanelChat10.add(new JLabel("User 2"));
        PanelChat10.add(new JLabel(": Pesan 10"));
        PanelKiri.add(PanelChat10);
        
        JPanel PanelChat11 = new JPanel();
        PanelChat11.setLayout(new BoxLayout(PanelChat11, BoxLayout.X_AXIS));
        
        PanelChat11.add(new JLabel("User 1"));
        PanelChat11.add(new JLabel(": Pesan 11"));
        PanelKiri.add(PanelChat11);
        
        JPanel PanelChat12 = new JPanel();
        PanelChat12.setLayout(new BoxLayout(PanelChat12, BoxLayout.X_AXIS));
        
        PanelChat12.add(new JLabel("User 1"));
        PanelChat12.add(new JLabel(": Pesan 12"));
        PanelKiri.add(PanelChat12);
        
        JPanel PanelChat13 = new JPanel();
        PanelChat13.setLayout(new GridLayout());
        
        PanelChat13.add(new JTextField("masukan chat disini .."));
        
        PanelChat13.add(new JButton(">"));
        
        PanelKiri.add(PanelChat13);
        
        getContentPane().add(PanelKiri);
        
        JPanel PanelTengah = new JPanel();
        PanelTengah.setLayout(new GridLayout(3, 0));
        
        JPanel PanelPlayer1 = new JPanel();
        PanelPlayer1.setLayout(new BorderLayout());
        
        JLabel labelPlayer1 = new JLabel();
        labelPlayer1.setFont(new Font("Dialog", 1, 24));
        labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer1.setText("PLAYER 1");
        PanelPlayer1.add(labelPlayer1, BorderLayout.CENTER);
        
        PanelTengah.add(PanelPlayer1);
        
        JPanel PanelReady = new JPanel();
        PanelReady.setLayout(new GridBagLayout());
        
        JButton btnReady = new JButton();
        btnReady.setText("READY");
        btnReady.addActionListener((ActionEvent e) -> { move(); });
        PanelReady.add(btnReady, new GridBagConstraints());
        
        PanelTengah.add(PanelReady);
        
        JPanel PanelPlayer2 = new JPanel();
        PanelPlayer2.setLayout(new BorderLayout());
        
        JLabel labelPlayer2 = new JLabel();
        labelPlayer2.setFont(new Font("Dialog", 1, 24));
        labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayer2.setText("PLAYER 2");
        PanelPlayer2.add(labelPlayer2, BorderLayout.CENTER);
        
        PanelTengah.add(PanelPlayer2);
        
        getContentPane().add(PanelTengah);
        
        JPanel PanelKanan = new JPanel();
        PanelKanan.setLayout(new GridLayout(3, 0));
        
        PanelKanan.add(new JPanel());
        
        JPanel PanelBawah = new JPanel();
        PanelBawah.setLayout(new GridLayout(4, 2));
        
        PanelBawah.add(new JLabel("Size"));
        
        JTextField txtSize = new JTextField();
        PanelBawah.add(txtSize);
        
        PanelBawah.add(new JLabel("Initial Value"));
        JTextField txtInitialValue = new JTextField();
        PanelBawah.add(txtInitialValue);
        
        PanelBawah.add(new JLabel("Rotation"));
        JTextField txtRotation = new JTextField();
        PanelBawah.add(txtRotation);
        
        PanelKanan.add(PanelBawah);
        
        getContentPane().add(PanelKanan);
        
        pack();
    }
    
    private void move() {
        GamePlayFrame p = new GamePlayFrame(client);
        p.setVisible(true);
        
        this.dispose();
    }
    
    // public static void main(String args[]) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // new LobbyFrame().setVisible(true);
    // }
    // });
    // }
    
}
