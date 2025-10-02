package client_server_app;

import java.net.*;
import java.io.*;

public class GoiNhan {
    ServerSocket Sserver;

    public void MoCong(int cong) {
        try {
            Sserver = new ServerSocket(cong);
            System.out.println("Mo cong thanh cong: " + cong);
        } catch(Exception tb1) {
            System.out.println("KHONG MO CONG DUOC: " + cong);
            tb1.printStackTrace(); 
            Sserver = null;
        } 
    }

    public void Goi(String tb, String Ip, int cong) {
        try {
            Socket Sclient = new Socket(Ip, cong);
            PrintWriter out = new PrintWriter(Sclient.getOutputStream(), true);
            out.println(Sclient.getLocalAddress().getHostAddress() + ":" + tb);
            out.close();
            Sclient.close();
            System.out.println("Da gui thanh cong toi: " + Ip + ":" + cong);
        } catch(Exception tb2) {
            System.out.println("KHONG GOI DUOC toi: " + Ip + ":" + cong);
            tb2.printStackTrace();
        }  
    }

    public String Nhan() {
        try {
            if (Sserver == null) return null;
            Socket nhan = Sserver.accept();
            InputStreamReader in = new InputStreamReader(nhan.getInputStream());
            BufferedReader buff = new BufferedReader(in);
            String thongBao = buff.readLine();
            nhan.close();
            return thongBao;
        } catch(Exception tb3) {
            System.out.print("Khong nhan duoc"); 
            return null;
        }
    }
}