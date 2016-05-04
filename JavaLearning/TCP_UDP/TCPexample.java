package com.javalearning.datastruct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class TcpClient1{
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket client = new Socket("127.0.0.1", 10001);
		//�����ȡ�������ݵ����ݵ�������
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		//����Ŀ�ģ�������д�뵽socket����������������
		//BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
		//InputStream in = client.getInputStream();
		//����һ��socket��ȡ������ȡ����˷��ش�д��Ϣ
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		String line = null;
		while((line = bufr.readLine())!=null){
//			bufout.write(line);
//			bufout.newLine();
//			bufout.flush();
			printWriter.println(line);
			if("over".equals(line)){
				break;
			}
			
			String  str = bufIn.readLine();
			System.out.println("server:"+str);
		}
		bufr.close();
		client.close();
	}
}

class TcpServer1{
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10001);
		
		Socket s = server.accept();
		
		//��ȡsocket��ȡ��������
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//Ŀ�ģ�socket�����������д����д�뵽socket������������͸��ͻ���
		BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		String line = null;
		while((line=bufIn.readLine())!=null){
			bufout.write(line.toUpperCase());
			System.out.println(line);
			bufout.newLine();
			bufout.flush();
			
		}
		s.close();
		server.close();
	}
}
public class TCPexample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
