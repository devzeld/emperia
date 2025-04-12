package me.zeld.emperia.reign.network;

import me.zeld.emperia.reign.service.Reign;

import java.io.*;
import java.net.*;

public class CommandManager implements Runnable{
    private Socket client;
    private final Reign reign;

    public CommandManager(Socket client, Reign reign) {
        this.client = client;
        this.reign = reign;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String command = in.readLine();

            //TODO: Handle commands
            switch (command) {
                case "SHOW_GOLD" -> out.println(reign.getGold());
                case "SHOW_WAREHOUSE" -> out.println(reign.getWarehouse());
            }

            out.flush();

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