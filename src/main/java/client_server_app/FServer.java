package client_server_app;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class FServer extends JFrame {
    private GoiNhan gn = new GoiNhan();
    private final int CONG_NHAN_SERVER = 50001;
    
    private Thread tdnhan; 

    private JList<String> list1;
    private DefaultListModel<String> listModel;
    private JButton btnNhan;
    
    private JTextField txtIpGui;
    private JTextField txtPortGui;
    private JTextField txtGoi;
    private JButton btnGoi;
    
    public class Mythread extends Thread {
        public void run() {
            while(true) {
                String receivedData = null;
                try {
                    receivedData = gn.Nhan(); 
                } catch (Exception e) {
                    receivedData = "EXCEPTION: " + e.getMessage();
                }

                final String finalData = receivedData;
                SwingUtilities.invokeLater(() -> {
                    if (finalData != null && !finalData.contains("EXCEPTION")) {
                        listModel.addElement("CLIENT GUI: " + finalData);
                    } else if (finalData != null) {
                        listModel.addElement("ERROR NHAN: " + finalData);
                    }
                    list1.ensureIndexIsVisible(listModel.getSize() - 1);
                });
            }
        }
    }

    public FServer() {
        setTitle("Server");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        initComponents();
        addListeners();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                gn.MoCong(CONG_NHAN_SERVER);
                
                if (gn.Sserver != null) {
                    System.out.println("FServer: Da mo cong nhan " + CONG_NHAN_SERVER);
                    listModel.addElement("SERVER: Da mo cong nhan " + CONG_NHAN_SERVER);
                    
                    btnNhan.setEnabled(false); 
                    
                    tdnhan = new Mythread();
                    tdnhan.start();
                    
                    listModel.addElement("SERVER: Dang lang nghe tu dong...");
                } else {
                    listModel.addElement("SERVER ERROR: Khong mo cong nhan duoc.");
                    btnNhan.setEnabled(false);
                }
            }
        });
    }

    private void initComponents() {
        add(new JLabel("Ip Cua may can ket noi den:")).setBounds(20, 20, 180, 25);
        txtIpGui = new JTextField("127.0.0.1"); 
        txtIpGui.setBounds(200, 20, 80, 25);
        add(txtIpGui);

        add(new JLabel("Cong:")).setBounds(300, 20, 50, 25);
        txtPortGui = new JTextField("1260"); 
        txtPortGui.setBounds(350, 20, 60, 25);
        add(txtPortGui);
        
        txtGoi = new JTextField("Server gui tin nhan!"); 
        txtGoi.setBounds(20, 370, 200, 25);
        add(txtGoi);
        
        listModel = new DefaultListModel<>();
        listModel.addElement("Log Server (Nhan):");
        list1 = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setBounds(20, 60, 480, 290);
        add(scrollPane);

        btnGoi = new JButton("Goi");
        btnGoi.setBounds(250, 370, 80, 25);
        add(btnGoi);
        
        btnNhan = new JButton("Nhan");
        btnNhan.setBounds(350, 370, 80, 25);
        btnNhan.setEnabled(false); 
        add(btnNhan);
    }

    private void addListeners() {
        
        btnNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addElement("Chuc nang Nhan da duoc chuyen sang che do tu dong (Thread).");
            }
        });
        
        btnGoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String msg = txtGoi.getText();
                final String ip = txtIpGui.getText();
                final String portText = txtPortGui.getText();
                
                try {
                    final int port = Integer.parseInt(portText);
                    
                    new Thread(() -> {
                        gn.Goi(msg, ip, port);
                        
                        SwingUtilities.invokeLater(() -> {
                             listModel.addElement("-> Da gui: '" + msg + "' toi " + ip + ":" + port);
                             txtGoi.setText(""); 
                        });
                        
                    }).start();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FServer.this, "Cong phai la so nguyen.", "Loi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        FServer fServer = new FServer();
        fServer.setVisible(true);
    }
}