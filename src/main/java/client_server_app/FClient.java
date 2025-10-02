package client_server_app;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*; 

public class FClient extends JFrame {
    private GoiNhan gn = new GoiNhan();
    private final int CONG_NHAN_CLIENT = 1260;
    
    private Thread tdnhan; 

    private JList<String> list1;
    private DefaultListModel<String> listModelNhan;
    private JButton btnNhan;
    
    private JTextField txtIp; 
    private JTextField txtPort; 
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
                        listModelNhan.addElement("SERVER GUI VE: " + finalData);
                    } else if (finalData != null) {
                        listModelNhan.addElement("ERROR NHAN: " + finalData);
                    }
                    list1.ensureIndexIsVisible(listModelNhan.getSize() - 1);
                });
            }
        }
    }

    public FClient() {
        setTitle("Client");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        initComponents();
        addListeners();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                gn.MoCong(CONG_NHAN_CLIENT); 
                
                if (gn.Sserver != null) {
                    System.out.println("FClient: Da mo cong nhan " + CONG_NHAN_CLIENT);
                    listModelNhan.addElement("CLIENT: Da mo cong nhan " + CONG_NHAN_CLIENT);
                    
                    btnNhan.setEnabled(false); 
                    
                    tdnhan = new Mythread();
                    tdnhan.start();
                    
                    listModelNhan.addElement("CLIENT: Dang lang nghe tu dong...");
                } else {
                    listModelNhan.addElement("CLIENT ERROR: Khong mo cong nhan duoc " + CONG_NHAN_CLIENT + ".");
                    btnNhan.setEnabled(false);
                }
            }
        });
    }

    private void initComponents() {
        add(new JLabel("Ip Cua may can ket noi den:")).setBounds(20, 20, 180, 25);
        txtIp = new JTextField("127.0.0.1"); 
        txtIp.setBounds(200, 20, 80, 25);
        add(txtIp);

        add(new JLabel("Cong:")).setBounds(290, 20, 50, 25);
        txtPort = new JTextField("50001");
        txtPort.setBounds(330, 20, 60, 25);
        add(txtPort);

        txtGoi = new JTextField("Xin chao Server!"); 
        txtGoi.setBounds(70, 60, 250, 25);
        add(txtGoi);
        
        btnGoi = new JButton("Goi");
        btnGoi.setBounds(330, 60, 80, 25);
        add(btnGoi);

        listModelNhan = new DefaultListModel<>();
        listModelNhan.addElement("Log Nhan Du Lieu (Cong 1260):");
        list1 = new JList<>(listModelNhan);
        JScrollPane scrollPane = new JScrollPane(list1);
        scrollPane.setBounds(20, 100, 450, 200);
        add(scrollPane);
        
        btnNhan = new JButton("Nhan");
        btnNhan.setBounds(380, 315, 80, 35);
        btnNhan.setEnabled(false);
        add(btnNhan);
    }

    private void addListeners() {
        btnGoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String msg = txtGoi.getText();
                final String ip = txtIp.getText();
                final String portText = txtPort.getText();
                
                try {
                    final int port = Integer.parseInt(portText);
                    
                    new Thread(() -> {
                        gn.Goi(msg, ip, port);
                        
                        SwingUtilities.invokeLater(() -> {
                             listModelNhan.addElement("-> Da gui: '" + msg + "' toi " + ip + ":" + port);
                             txtGoi.setText(""); 
                        });
                        
                    }).start();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FClient.this, "Cong phai la so nguyen.", "Loi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnNhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModelNhan.addElement("Chuc nang Nhan da duoc chuyen sang che do tu dong (Thread).");
            }
        });
    }

    public static void main(String[] args) {
        FClient fClient = new FClient();
        fClient.setVisible(true);
    }
}