package com.javalearning.datastruct;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class PicClientBy{
	
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

class PicServerThread implements Runnable{
	
	private Socket client;
	public PicServerThread(Socket client){
		this.client = client;
	}
	public void run()
	{
		try{
			InputStream in =client.getInputStream();
			
			FileOutputStream fos = new FileOutputStream("server.JPG");
			
			byte[] buf = new byte[1024];
			
			int len =0;
			while((len=in.read(buf))!=-1){
				fos.write(buf,0,len);
				
				//System.out.println(len);
			}
			OutputStream out = client.getOutputStream();
			
			out.write("上传成功".getBytes());
			
			fos.close();
			client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}


class PicServerBy{
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(10007);
		
		while(true){
			Socket clinet = server.accept();
			new Thread(new PicServerThread(clinet)).start();
		}		
		
		//server.close();
	}
}

public class PicTCPByThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
