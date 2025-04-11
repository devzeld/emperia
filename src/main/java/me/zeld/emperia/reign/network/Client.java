package me.zeld.emperia.reign.network;

import me.zeld.emperia.reign.service.*;
import me.zeld.emperia.reign.util.type.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void start(String[] args) {
        System.out.println(delete("Earthland"));
        System.out.println(create("Earthland"));
        System.out.println(getReigns());

    }

    public static Reign create(String name) {
        try {
            Socket socket = new Socket("192.168.4.5", 6830);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("CREATE");
            out.println(name + "\n");
            out.flush();

            String response = in.readLine();
            if (response.equalsIgnoreCase("OK")) {

                int gold = Integer.parseInt(in.readLine());
                Reign reign = new Reign(name, gold);
                System.out.println(name + " has been created");

                response = in.readLine();

                final int NUM_TERRAIN = Integer.parseInt(response);

                System.out.println(response);

                for (int i = 0; i < NUM_TERRAIN; i++) {
                    response = in.readLine();
                    System.out.println(response);

                    reign.addTerrain(switch (response) {
                        case "FIELD" -> Terrain.create(TerrainType.FIELD);
                        case "MOUNTAIN" -> Terrain.create(TerrainType.MOUNTAIN);
                        case "VILLAGE" -> Terrain.create(TerrainType.FOREST);
                        default -> null;
                    });
                }

                socket.close();
                out.close();
                in.close();
                return reign;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void build(Reign reign, TerrainType tType, BuildingType bType) {
        try {
            Socket socket = new Socket("192.168.4.60", 6830);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("BUILD");
            out.println(tType);
            out.println(bType);
            out.flush();

            String response = in.readLine();

            System.out.println(response);
            if (response.equalsIgnoreCase("OK")) {
                Terrain terrain = Terrain.create(tType);
                terrain.build(new Building(bType, 20));
                reign.addTerrain(terrain);


                socket.close();
                out.close();
                in.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean delete(String name) {
        try {
            Socket socket = new Socket("192.168.4.5", 6830);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("DELETE");
            out.println(name);
            out.flush();

            String response = in.readLine();

            if (response.equalsIgnoreCase("OK")) {
                System.out.println("bye");

                socket.close();
                out.close();
                in.close();
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static Vector<String> getReigns() {
        Vector<String> reigns = new Vector<>();
        try {
            Socket socket = new Socket("192.168.4.5", 6830);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("GET_REIGNS");
            out.println("");
            out.flush();

            String response = in.readLine();

            while(!response.isEmpty()) {
                reigns.add(response);

                response = in.readLine();
            }

            socket.close();
            out.close();
            in.close();

            return reigns;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}