package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import java.net.ConnectException;

import static com.example.Const.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, TCP_PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            System.out.println("Connection established");

            out.writeLong(INIT_BALL_COUNTER);
            System.out.println("Send init ball:" + INIT_BALL_COUNTER);

            while (true) {
                long entry = in.readLong();
                System.out.println("Receive counter: " + entry);
                if (entry++ == OVERFLOW_STATUS) break;
                out.writeLong(entry);
                System.out.println("Send counter: " + entry);
            }

            System.out.println("Receive string: " + br.readLine());

        } catch (ConnectException e) {
            System.err.println("Server " + SERVER_IP + " is not available on TCP port " + TCP_PORT);
        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println("Connection closed");
    }
}