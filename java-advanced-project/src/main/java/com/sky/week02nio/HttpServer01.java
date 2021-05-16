package com.sky.week02nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true){
            Socket socket = serverSocket.accept();
            service(socket);
        }

    }
    public static void service(Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.0 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hello,nio1";
            System.out.println("Hello,nio1");
            printWriter.println("Content-Length："+body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
