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
		//定义读取键盘数据的数据的流对象
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		//定义目的，将数据写入到socket输出流，发给服务端
		//BufferedWriter bufout = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
		//InputStream in = client.getInputStream();
		//定义一个socket读取流，读取服务端返回大写信息
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
		
		//读取socket读取流中数据
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//目的，socket输出流，将大写数据写入到socket输出流，并发送给客户端
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
