package me.zeld.emperia.reign.network;

import me.zeld.emperia.reign.service.Warehouse;

import java.io.*;
import java.net.*;

public class CommandManager implements Runnable{
    private Socket client;

    public CommandManager(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String command = in.readLine();

            //TODO: Handle commands
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
}