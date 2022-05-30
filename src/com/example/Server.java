package com.example;

import java.io.PrintStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

import static com.example.Const.*;

public class Server {
    public static void main(String[] args) {

        System.out.println("Server wait connection");

        try (Socket socket = new ServerSocket(TCP_PORT).accept();
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             PrintStream ps = new PrintStream(out)) {

            System.out.println("Connection established");

            while (true) {
                long entry = in.readLong();
                System.out.println("Receive counter: " + entry);
                if (entry++ > MAX_BALL_COUNTER) break;
                out.writeLong(entry);
                System.out.println("Send counter: " + entry);
            }

            out.writeLong(OVERFLOW_STATUS);
            System.out.println("Send OVERFLOW status: " + OVERFLOW_STATUS);

            ps.println(OVERFLOW_MESSAGE);
            System.out.println("Send string: " + OVERFLOW_MESSAGE);

        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println("Connection closed");
    }
}