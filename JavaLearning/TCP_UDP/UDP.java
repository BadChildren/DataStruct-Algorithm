package com.javalearning.datastruct;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class Client{
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		while((line=br.readLine())!=null){
			
			if("886".equals(line))
				break;
			byte[] buff = line.getBytes();
			
			DatagramPacket dp = new DatagramPacket(buff, buff.length, InetAddress.getByName("127.0.0.1"), 10001);
			ds.send(dp);
			
		}
		ds.close();
	}
	
}

class Server{
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket(10001);
		while(true){
			byte[] buff = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buff,buff.length);
			ds.receive(dp);
			
			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(),0,dp.getLength());
			System.out.println("从ip="+ip+"获得过来的数据："+data);
		}
	}
}

public class UDP {

	public static void main(String[] args) {
		Client c = new Client();
		Server s = new Server();

	}

}
