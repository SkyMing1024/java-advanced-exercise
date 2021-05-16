package com.sky.week02nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpServer01 {
    public static void main(String[] args) {

    }


    public static void service(Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
