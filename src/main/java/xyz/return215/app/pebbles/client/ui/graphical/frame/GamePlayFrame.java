package xyz.return215.app.pebbles.client.ui.graphical.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import xyz.return215.app.pebbles.client.ClientHandler;

public class GamePlayFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7811613012616821159L;
    
    ClientHandler client;
    
    public GamePlayFrame(ClientHandler c) {
        client = c;
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        
        JPanel PanelPotKiri = new JPanel();
        PanelPotKiri.setPreferredSize(new Dimension(100, 486));
        PanelPotKiri.setLayout(new GridLayout(3, 0));
        
        JLabel labelPotKiri = new JLabel();
        PanelPotKiri.add(labelPotKiri);
        
        JTextField potKiri = new JTextField();
        potKiri.setHorizontalAlignment(JTextField.CENTER);
        PanelPotKiri.add(potKiri);
        
        getContentPane().add(PanelPotKiri);
        
        JPanel PanelPotTengah = new JPanel();
        PanelPotTengah.setLayout(new GridLayout(3, 0));
        
        JPanel PanelAtas = new JPanel();
        PanelAtas.setLayout(new GridLayout(3, 0));
        JLabel labelAtas1 = new JLabel();
        JLabel labelAtas2 = new JLabel();
        PanelAtas.add(labelAtas1);
        PanelAtas.add(labelAtas2);
        
        JLabel labelUserAtas = new JLabel();
        labelUserAtas.setHorizontalAlignment(SwingConstants.CENTER);
        labelUserAtas.setText("<username 2>");
        PanelAtas.add(labelUserAtas);
        
        PanelPotTengah.add(PanelAtas);
        
        JPanel PanelTengah = new JPanel();
        PanelTengah.setPreferredSize(new Dimension(600, 162));
        PanelTengah.setLayout(new GridLayout(3, 0));
        
        JPanel PanelPotAtas = new JPanel();
        PanelPotAtas.setLayout(new GridLayout(0, 5));
        JTextField pot1 = new JTextField();
        JTextField pot2 = new JTextField();
        JTextField pot3 = new JTextField();
        JTextField pot4 = new JTextField();
        JTextField pot5 = new JTextField();
        PanelPotAtas.add(pot1);
        PanelPotAtas.add(pot2);
        PanelPotAtas.add(pot3);
        PanelPotAtas.add(pot4);
        PanelPotAtas.add(pot5);
        
        PanelTengah.add(PanelPotAtas);
        
        JPanel PanelPotTengahKosong = new JPanel();
        PanelPotTengahKosong.setLayout(new BorderLayout());
        PanelTengah.add(PanelPotTengahKosong);
        
        JPanel PanelPotBawah = new JPanel();
        PanelPotBawah.setLayout(new GridLayout(0, 5));
        
        JTextField pot10 = new JTextField();
        JTextField pot9 = new JTextField();
        JTextField pot8 = new JTextField();
        JTextField pot7 = new JTextField();
        JTextField pot6 = new JTextField();
        PanelPotBawah.add(pot10);
        PanelPotBawah.add(pot9);
        PanelPotBawah.add(pot8);
        PanelPotBawah.add(pot7);
        PanelPotBawah.add(pot6);
        
        PanelTengah.add(PanelPotBawah);
        
        PanelPotTengah.add(PanelTengah);
        
        JPanel PanelBawah = new JPanel();
        PanelBawah.setLayout(new GridLayout(3, 0));
        
        JLabel labelUserBawah = new JLabel();
        labelUserBawah.setHorizontalAlignment(SwingConstants.CENTER);
        labelUserBawah.setText("<username 2>");
        PanelBawah.add(labelUserBawah);
        
        JTextField turnMessage = new JTextField();
        turnMessage.setHorizontalAlignment(JTextField.CENTER);
        turnMessage.setText("<turn message>");
        PanelBawah.add(turnMessage);
        
        PanelPotTengah.add(PanelBawah);
        
        getContentPane().add(PanelPotTengah);
        
        JPanel PanelPotKanan = new JPanel();
        PanelPotKanan.setPreferredSize(new Dimension(100, 486));
        PanelPotKanan.setLayout(new GridLayout(3, 0));
        PanelPotKanan.add(new JLabel());
        
        JTextField potKanan = new JTextField();
        potKanan.setHorizontalAlignment(JTextField.CENTER);
        PanelPotKanan.add(potKanan);
        
        getContentPane().add(PanelPotKanan);
        
        pack();
    }
    
    // public static void main(String args[]) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // new GamePlayFrame().setVisible(true);
    // }
    // });
    // }
    
}
