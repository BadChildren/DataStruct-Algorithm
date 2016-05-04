package com.javalearning.datastruct;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class TCPServer{
    public static void main(String[] args)throws IOException{
        ServerSocket listen = new ServerSocket(5050);
        //服务端没有流对象，只有拿客户端的流对象才能进行通信
        Socket server  = listen.accept();
        
        InputStream in = server.getInputStream();//源是网络流
        OutputStream out = server.getOutputStream();
        String ip = server.getInetAddress().getHostAddress();
        byte[] buf = new byte[1024];
        System.out.println(ip+":"+new String(buf,0,in.read(buf)));
        
//        //char c = (char)in.read();
//        String c = new String(buf,0, in.read(buf));
//        System.out.println("收到:" + c);
        out.write("server".getBytes());
        
        out.close();
        in.close();
        server.close();
        listen.close();
    }
}

class TCPClient{
    public static void main(String[] args)throws IOException{
        Socket client = new Socket("127.0.0.1" , 5050);
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        
        out.write("TcP GEN MEN LAILE ".getBytes());
        //char c = (char)in.read();//socker的read方法也是阻塞式
        byte[] buf = new byte[1024];
        String c = new String(buf,0,in.read(buf));
        System.out.println("收到:" + c);
        out.close();
        in.close();
        client.close();
    }
}



public class TCP {

	public static void main(String[] args){
		
	}
}
