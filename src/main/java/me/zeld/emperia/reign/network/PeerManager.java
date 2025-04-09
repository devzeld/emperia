package me.zeld.emperia.reign.network;

import me.zeld.emperia.reign.service.*;

import java.io.*;
import java.net.*;

public class PeerManager implements Runnable {

    private Reign reign;
    private static PeerManager instance;
    private boolean active;

    // Singleton Pattern
    private PeerManager() {
    }

    public static PeerManager getInstance() {
        if (instance == null) {
            instance = new PeerManager();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(6830);
            while (active) {
                Socket client = server.accept();

                (new Thread(new CommandManager(client))).start();
            }
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Reign getReign() {
        return reign;
    }

    public void setReign(Reign reign) {
        this.reign = reign;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
