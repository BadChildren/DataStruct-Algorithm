package com.javalearning.datastruct;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class PicClient{
	
	public static void  main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1", 10007);
		
		OutputStream out= client.getOutputStream();
		
		FileInputStream in = new FileInputStream("C:\\Users\\Dandy\\Desktop\\最近\\简历\\xudandan.JPG");
		
		int len = 0;
		byte[] buf = new byte[1024];
		
		while((len = in.read(buf))!=-1){
			out.write(buf, 0, len);
			//System.out.println(len);
		}
		//告诉客户端数据已写完
		client.shutdownOutput();
		
		InputStream ins = client.getInputStream();
		
		byte[] buff = new byte[1024];
		int num = ins.read(buff);
		//System.out.println(num);
		System.out.println(new String(buff,0,num));
		in.close();
		client.close();
	}	
}

class PicServer{
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10007);
		
		Socket clinet = server.accept();
		
		InputStream in =clinet.getInputStream();
		
		FileOutputStream fos = new FileOutputStream("server.JPG");
		
		byte[] buf = new byte[1024];
		
		int len =0;
		while((len=in.read(buf))!=-1){
			fos.write(buf,0,len);
			//System.out.println(len);
		}
		OutputStream out = clinet.getOutputStream();
		
		out.write("上传成功".getBytes());
		
		fos.close();
		clinet.close();
		server.close();
	}
}

public class PicTCP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
