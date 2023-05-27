package com.ufcity.semantic;

import com.ufcity.semantic.semantic.SemanticImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ufcity.semantic.Main.version;
import static ufcitycore.mqtt.ConnectionData.*;

public class Menu {

    public static int check(String[] params){
        String sa=null, sp="3030", semantic_password="admin", semantic_login="admin";
        int qtArgs = params.length;
        if(qtArgs == 0) {
            System.out.println("Invalid parameters. Type -h (or --help) for help.");
            return 1;
        }
        if(qtArgs == 1){
            if(params[0].equals("-h") || params[0].equals("--help")){
                System.out.println("-fa \t--fog-address         \tAddress to fog computing.");
                System.out.println("-fp \t--fog-port            \tPort to edge computing.");
                System.out.println("-sa \t--semantic-address    \tAddress to semantic server.");
                System.out.println("-sp \t--semantic-port       \tPort to semantic server.");
                System.out.println("-sl \t--semantic-login      \tLogin to semantic server.");
                System.out.println("-ss \t--semantic-password   \tPassword to semantic server.");
                System.out.println("-v  \t--version             \tVersion of this system.");
            } else if (params[0].equals("-v") || params[0].equals("--version")) {
                System.out.println("Version: "+Main.version);
            } else {
                System.out.println("Invalid parameters. Type -h (or --help) for help.");
            }
            return 1;
        }
        if(qtArgs % 2 != 0){
            System.out.println("Invalid parameters. Type -h (or --help) for help.");
            return 1;
        }else{
            int i = 0;
            while (i < qtArgs){
                switch (params[i]) {
                    case "-fa", "--fog-address" -> setInnerHost(params[i + 1]);
                    case "-fp", "--fog-port" -> setInnerPort(params[i + 1]);
                    case "-sa", "--semantic-address" -> sa = params[i + 1];
                    case "-sp", "--semantic-port" -> sp = params[i + 1];
                    case "-sl", "--semantic-login" -> semantic_login = params[i + 1];
                    case "-ss", "--semantic-password" -> semantic_password = params[i + 1];
                }
                i = i + 2;
            }
            if(sa != null) {
                System.out.println(">> Connecting semantic server! Semantic server address: "+sa+":"+sp);
                Main.semantic = new SemanticImpl(sa, sp, semantic_login, semantic_password);
            }
            return 0;
        }
    }

    public static String[] ReaderConfig() throws IOException {
        String path = new File("ufcity-semantic.config").getAbsolutePath();
//        System.out.println(path);
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        List<String> args = new ArrayList<>();
        String line = "";
        while (true) {
            line = buffRead.readLine();
            if (line != null) {
                String[] l = line.split(":");
                args.add(l[0].trim());
                args.add(l[1].trim());
            } else {
                buffRead.close();
                System.out.println(Arrays.toString(args.toArray(new String[0])));
                return args.toArray(new String[0]);
            }
        }
    }

}
