package xyz.return215.app.pebbles.client.ui.graphical.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import xyz.return215.app.pebbles.client.ClientHandler;

public class MainFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -1767292973684299027L;
    
    ClientHandler client;
    
    public MainFrame(ClientHandler c) {
        client = c;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        JPanel PanelAtas = new JPanel();
        PanelAtas.setPreferredSize(new Dimension(800, 200));
        PanelAtas.setLayout(new BorderLayout());
        
        JLabel labelWelcome = new JLabel();
        labelWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        labelWelcome.setText("WELCOME <Username>");
        PanelAtas.add(labelWelcome, BorderLayout.CENTER);
        
        getContentPane().add(PanelAtas);
        
        JPanel PanelTengah = new JPanel();
        PanelTengah.setPreferredSize(new Dimension(800, 350));
        PanelTengah.setLayout(new GridLayout(0, 2, 20, 0));
        
        JPanel PanelKiri = new JPanel();
        PanelKiri.setLayout(new GridLayout(4, 0));
        
        JLabel labelNewGame = new JLabel();
        labelNewGame.setHorizontalAlignment(SwingConstants.CENTER);
        labelNewGame.setText("NEW GAME");
        PanelKiri.add(labelNewGame);
        
        JButton btnCreate = new JButton("CREATE");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) { moveToLobby(); }
        });
        PanelKiri.add(btnCreate);
        
        PanelTengah.add(PanelKiri);
        
        JPanel PanelKanan = new JPanel();
        PanelKanan.setLayout(new GridLayout(4, 0));
        
        JLabel labelJoinGame = new JLabel();
        labelJoinGame.setHorizontalAlignment(SwingConstants.CENTER);
        labelJoinGame.setText("JOIN GAME");
        PanelKanan.add(labelJoinGame);
        
        JButton btnJoin = new JButton();
        btnJoin.setText("JOIN");
        btnJoin.addActionListener((ActionEvent evt) -> { moveToLobby(); });
        PanelKanan.add(btnJoin);
        
        PanelTengah.add(PanelKanan);
        
        getContentPane().add(PanelTengah);
        
        JPanel PanelBawah = new JPanel();
        PanelBawah.setPreferredSize(new Dimension(800, 67));
        PanelBawah.setLayout(new BorderLayout());
        
        JButton btnHowToPlay = new JButton();
        btnHowToPlay.setText("How to Play");
        btnHowToPlay.addActionListener((ActionEvent evt) -> { howToPlay(); });
        PanelBawah.add(btnHowToPlay, BorderLayout.CENTER);
        
        getContentPane().add(PanelBawah);
        
        pack();
    }
    
    private void howToPlay() {
        // TODO use dialog to explain gameplay
        LobbyFrame l = new LobbyFrame(client);
        l.setVisible(true);
        
        this.dispose();
    }
    
    private void moveToLobby() {
        LobbyFrame l = new LobbyFrame(client);
        l.setVisible(true);
        
        this.dispose();
    }
    
    // public static void main(String args[]) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // new MainFrame().setVisible(true);
    // }
    // });
    // }
}
