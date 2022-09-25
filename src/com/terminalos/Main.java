package com.terminalos;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import java.awt.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import oshi.software.os.OperatingSystem;

import javax.swing.JFrame;

public class Main {
    // Managing Scripts
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        startup();
        login();
        mainmenu();
    }
    static void startup() throws IOException, InterruptedException {

        final String os = System.getProperty("os.name"); //detect OS
        if (os.contains("Mac")) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", "printf '\\e[8;40;150t'").inheritIO().start().waitFor();
        }
        cls();
        animlogoTOSlogo2();
        System.out.println("\n Booting...\n");
        loadingbar();
        cls();

        //perform first time setup or replace missing files
        String homeFolder = System.getProperty("user.home");
        new File(homeFolder + "/TerminalOS-Data/Applications").mkdirs();
        new File(homeFolder + "/TerminalOS-Data/SystemApps").mkdirs();
        new File(homeFolder + "/TerminalOS-Data/Resources").mkdirs();
        new File(homeFolder + "/TerminalOS-Data/Settings").mkdirs();
        try { //create login txt
            File login = new File(homeFolder + "/TerminalOS-Data/Settings/login.txt");
            if (login.createNewFile()) {
                System.out.println("File created: " + login.getName());
            } else {
                System.out.println("Login File Already Exists...");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        cls();

        // Set TerminalOS version
        PrintWriter writepass = new PrintWriter(homeFolder + "/TerminalOS-Data/Resources/version.txt", "UTF-8");
        writepass.println(Info.VERSION);
        writepass.close();
    }

    // Built in Login Application..
    static void login() throws IOException, InterruptedException, URISyntaxException {
        String homeFolder = System.getProperty("user.home");
        new File(homeFolder + "/TerminalOS-Data/Settings").mkdirs();
        try { //create login txt
            File login = new File(homeFolder + "/TerminalOS-Data/Settings/login.txt");
            if (login.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        cls();
        BufferedReader brTest = new BufferedReader(new FileReader(homeFolder + "/TerminalOS-Data/Settings/login.txt"));
        String pass = brTest.readLine();
        logoTOSlogo();
        if (pass == null) {
            System.out.println("\n\n Welcome to the Password Creation Menu\n Please enter your desired password:\n");
            Scanner Input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("  Listening > ");
            String inputpass = Input.nextLine(); // Read user input
            PrintWriter writepass = new PrintWriter(homeFolder + "/TerminalOS-Data/Settings/login.txt", "UTF-8");
            writepass.println(inputpass);
            writepass.close();
            cls();
            System.out.print(Color.GREEN_BRIGHT);
            logoTOSlogo();
            System.out.println(" \n\n-- Password created --" + Color.RESET);
            onesecondpause();
            onesecondpause();
            login();
        } else {
            System.out.println("\n\n Please enter your password:\n");
            Scanner Input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("  Listening > ");
            String inputpass = Input.nextLine(); // Read user input
            if (inputpass.equals(pass)) {
                cls();
                System.out.print(Color.GREEN_BRIGHT + "\r");
                logoTOSlogo();
                System.out.println(" \n\n-- Access Granted --" + Color.RESET);
                onesecondpause();
                onesecondpause();
                mainmenu();
            } else {
                cls();
                System.out.print(Color.RED_BRIGHT);
                logoTOSlogo();
                System.out.println(" \n\n-- Access Denied --" + Color.RESET);
                onesecondpause();
                onesecondpause();
                login();
            }
        }
    }
    //Main Menu Application
    static void mainmenu() throws IOException, InterruptedException, URISyntaxException { // Main Menu Method
        cls();
        menulogo();
        System.out.println("\n What would you like to do?\n");
        System.out.println(" Built in Applications:\n");
        System.out.println(" S > Settings");
        System.out.println(" A > AMGR (Application Manager)\n");
        System.out.println(" Functions:\n");
        System.out.println(Color.RED + " X > Exit TerminalOS" + Color.RESET);
        final String ops = System.getProperty("os.name");
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String rawuserinput = Input.next(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        String openapps = "A";
        String Settings = "S";
        String Exit = "X";
        if (userinput.equals(openapps)) {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                String homeFolder = System.getProperty("user.home");
                new ProcessBuilder("cmd", "/c", "java -jar \"" + homeFolder + "\\TerminalOS-Data\\SystemApps\\AMGR.jar\"").inheritIO().start().waitFor();
            } else {
                String homeFolder = System.getProperty("user.home");
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", "java -jar " + homeFolder + "/TerminalOS-Data/SystemApps/AMGR.jar").inheritIO().start().waitFor();
            }
            //String homeFolder = System.getProperty("user.home");
            //File file = new File (homeFolder + "/TerminalOS-Data/Applications");
            //Desktop desktop = Desktop.getDesktop();
            //desktop.open(file);
            //cls();
            //mainmenu();
        } else if (userinput.equals(Settings)) {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                String homeFolder = System.getProperty("user.home");
                new ProcessBuilder("cmd", "/c", "java -jar \"" + homeFolder + "\\TerminalOS-Data\\SystemApps\\Settings.jar\"").inheritIO().start().waitFor();
            } else {
                String homeFolder = System.getProperty("user.home");
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", "java -jar " + homeFolder + "/TerminalOS-Data/SystemApps/Settings.jar").inheritIO().start().waitFor();
            }
        } else if (userinput.equals(Exit)) {
            cls();
            System.exit(0);
        } else {
            mainmenu();
        }
    }
    // Built in System Information application..

    // -- LIBRARIES --
    // big ASCII

    // small ASCII
    public static void logo() {
        System.out.println(" _____                   _             _ _____ _____ ");
        System.out.println("|_   _|                 (_)           | |  _  /  ___|");
        System.out.println("  | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        System.out.println("  | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        System.out.println("  | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        System.out.println("  \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
    }
    public static void logoTOSlogo() {
        System.out.println("       .7???????????");
        System.out.println("        !!!!!!!!????     _____                   _             _ _____ _____ ");
        System.out.println("               .7???    |_   _|                 (_)           | |  _  /  ___|");
        System.out.println("^^^^    :^^:   .7???      | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        System.out.println("????.  .????.  .????      | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        System.out.println("???7.   :^^^    ^^^^      | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        System.out.println("???7.                     \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
        System.out.println("????!!!!!!!!        ");
        System.out.println("???????????7.       ");
    }
    public static void menulogo() {
        System.out.println("  __  __                  ");
        System.out.println(" |  \\/  |                 ");
        System.out.println(" | \\  / | ___ _ __  _   _ ");
        System.out.println(" | |\\/| |/ _ \\ '_ \\| | | |");
        System.out.println(" | |  | |  __/ | | | |_| |");
        System.out.println(" |_|  |_|\\___|_| |_|\\__,_|");
    }
    public static void loadingbar() {
        System.out.print("\r [                                 ]");
        smolpause2();
        System.out.print("\r [-                                ]");
        smolpause2();
        System.out.print("\r [--                               ]");
        smolpause2();
        System.out.print("\r [---                              ]");
        smolpause2();
        System.out.print("\r [----                             ]");
        smolpause2();
        System.out.print("\r [-----                            ]");
        smolpause2();
        System.out.print("\r [------                           ]");
        smolpause2();
        System.out.print("\r [-------                          ]");
        smolpause2();
        System.out.print("\r [-------                          ]");
        smolpause2();
        System.out.print("\r [--------                         ]");
        smolpause2();
        System.out.print("\r [---------                        ]");
        smolpause2();
        System.out.print("\r [----------                       ]");
        smolpause2();
        System.out.print("\r [-----------                      ]");
        smolpause2();
        System.out.print("\r [------------                     ]");
        smolpause2();
        System.out.print("\r [-------------                    ]");
        smolpause2();
        System.out.print("\r [--------------                   ]");
        smolpause2();
        System.out.print("\r [---------------                  ]");
        smolpause2();
        System.out.print("\r [----------------                 ]");
        smolpause2();
        System.out.print("\r [-----------------                ]");
        smolpause2();
        System.out.print("\r [------------------               ]");
        smolpause2();
        System.out.print("\r [-------------------              ]");
        smolpause2();
        System.out.print("\r [--------------------             ]");
        smolpause2();
        System.out.print("\r [---------------------            ]");
        smolpause2();
        System.out.print("\r [----------------------           ]");
        smolpause2();
        System.out.print("\r [-----------------------          ]");
        smolpause2();
        System.out.print("\r [------------------------         ]");
        smolpause2();
        System.out.print("\r [-------------------------        ]");
        smolpause2();
        System.out.print("\r [--------------------------       ]");
        smolpause2();
        System.out.print("\r [---------------------------      ]");
        smolpause2();
        System.out.print("\r [----------------------------     ]");
        smolpause2();
        System.out.print("\r [-----------------------------    ]");
        smolpause2();
        System.out.print("\r [------------------------------   ]");
        smolpause2();
        System.out.print("\r [-------------------------------  ]");
        smolpause2();
        System.out.print("\r [-------------------------------- ]");
        smolpause2();
        System.out.print("\r [---------------------------------]");
        smolpause2();
        System.out.println("\n Done");
        onesecondpause();
    }
    public static void animlogo() {
        smolpause();
        System.out.println(" _____                   _             _ _____ _____ ");
        smolpause();
        System.out.println("|_   _|                 (_)           | |  _  /  ___|");
        smolpause();
        System.out.println("  | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        smolpause();
        System.out.println("  | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        smolpause();
        System.out.println("  | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        smolpause();
        System.out.println("  \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
    }
    public static void animlogoTOSlogo2() {
        System.out.println("       .7???????????");
        smolpause2();
        System.out.println("        !!!!!!!!????     _____                   _             _ _____ _____ ");
        smolpause2();
        System.out.println("               .7???    |_   _|                 (_)           | |  _  /  ___|");
        smolpause2();
        System.out.println("^^^^    :^^:   .7???      | | ___ _ __ _ __ ___  _ _ __   __ _| | | | \\ `--. ");
        smolpause2();
        System.out.println("????.  .????.  .????      | |/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` | | | | |`--. \\");
        smolpause2();
        System.out.println("???7.   :^^^    ^^^^      | |  __/ |  | | | | | | | | | | (_| | \\ \\_/ /\\__/ /");
        smolpause2();
        System.out.println("???7.                     \\_/\\___|_|  |_| |_| |_|_|_| |_|\\__,_|_|\\___/\\____/ ");
        smolpause2();
        System.out.println("????!!!!!!!!        ");
        smolpause2();
        System.out.println("???????????7.       ");
    }
    static void settingslogo() {
        System.out.println("   _____      _   _   _                 ");
        System.out.println("  / ____|    | | | | (_)                ");
        System.out.println(" | (___   ___| |_| |_ _ _ __   __ _ ___ ");
        System.out.println("  \\___ \\ / _ \\ __| __| | '_ \\ / _` / __|");
        System.out.println("  ____) |  __/ |_| |_| | | | | (_| \\__ \\");
        System.out.println(" |_____/ \\___|\\__|\\__|_|_| |_|\\__, |___/");
        System.out.println("                               __/ |    ");
        System.out.println("                              |___/     ");
    }
    static void seperator() {
        System.out.println("--------------------------------------------------------------------------------");
    }
    // functions and enums
    static void cls() throws IOException, InterruptedException {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
    static void onesecondpause() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void shortpause() {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void smolpause() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void smolpause2() {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void shortpause2() {
        try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void TOSlogo() {
        System.out.println("       .7???????????");
        System.out.println("        !!!!!!!!????");
        System.out.println("               .7???");
        System.out.println("^^^^    :^^:   .7???");
        System.out.println("????.  .????.  .????");
        System.out.println("???7.   :^^^    ^^^^");
        System.out.println("???7.");
        System.out.println("????!!!!!!!!");
        System.out.println("???????????7.");
    }
    public static void animmenulogo() {
        System.out.println("  __  __                  ");
        smolpause2();
        System.out.println(" |  \\/  |                 ");
        smolpause2();
        System.out.println(" | \\  / | ___ _ __  _   _ ");
        smolpause2();
        System.out.println(" | |\\/| |/ _ \\ '_ \\| | | |");
        smolpause2();
        System.out.println(" | |  | |  __/ | | | |_| |");
        smolpause2();
        System.out.println(" |_|  |_|\\___|_| |_|\\__,_|");
    }
    public static void apps() throws IOException {
        //Creating a File object for directory
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS-Data/Applications");
        FilenameFilter batFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".jar")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String imageFilesList[] = directoryPath.list(batFilefilter);
        System.out.println(" Installed JAR Apps:");
        for(String fileName : imageFilesList) {
            System.out.println("   " + fileName);
        }
    }
    public static void bapps() throws IOException {

        //Creating a File object for directory
        String homeFolder = System.getProperty("user.home");
        File directoryPath = new File(homeFolder + "/TerminalOS-Data/Applications");
        FilenameFilter batFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".bat")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String imageFilesList[] = directoryPath.list(batFilefilter);
        System.out.println(" Installed Batch Apps:");
        for(String fileName : imageFilesList) {
            System.out.println("   " + fileName);
        }
        System.out.println("");
    }
    public static void getVersion() throws IOException, InterruptedException {
        String homeFolder = System.getProperty("user.home");
        new File(homeFolder + "/TerminalOS-Data/Resources").mkdirs();
        try { //create version
            File login = new File(homeFolder + "/TerminalOS-Data/Resources/version.txt");
            if (login.createNewFile()) {
            } else {
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        BufferedReader brTest = new BufferedReader(new FileReader(homeFolder + "/TerminalOS-Data/Resources/version.txt"));
        String version = brTest.readLine();
        System.out.println("\n TerminalOS Version: " + Color.BLUE_BRIGHT + version + Color.RESET);
    }
    enum Color {
        //Color end string, color reset
        RESET("\033[0m"),

        // Regular Colors. Normal color, no bold, background color etc.
        BLACK("\033[0;30m"),    // BLACK
        RED("\033[0;31m"),      // RED
        GREEN("\033[0;32m"),    // GREEN
        YELLOW("\033[0;33m"),   // YELLOW
        BLUE("\033[0;34m"),     // BLUE
        MAGENTA("\033[0;35m"),  // MAGENTA
        CYAN("\033[0;36m"),     // CYAN
        WHITE("\033[0;37m"),    // WHITE

        // Bold
        BLACK_BOLD("\033[1;30m"),   // BLACK
        RED_BOLD("\033[1;31m"),     // RED
        GREEN_BOLD("\033[1;32m"),   // GREEN
        YELLOW_BOLD("\033[1;33m"),  // YELLOW
        BLUE_BOLD("\033[1;34m"),    // BLUE
        MAGENTA_BOLD("\033[1;35m"), // MAGENTA
        CYAN_BOLD("\033[1;36m"),    // CYAN
        WHITE_BOLD("\033[1;37m"),   // WHITE

        // Underline
        BLACK_UNDERLINED("\033[4;30m"),     // BLACK
        RED_UNDERLINED("\033[4;31m"),       // RED
        GREEN_UNDERLINED("\033[4;32m"),     // GREEN
        YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
        BLUE_UNDERLINED("\033[4;34m"),      // BLUE
        MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
        CYAN_UNDERLINED("\033[4;36m"),      // CYAN
        WHITE_UNDERLINED("\033[4;37m"),     // WHITE

        // Background
        BLACK_BACKGROUND("\033[40m"),   // BLACK
        RED_BACKGROUND("\033[41m"),     // RED
        GREEN_BACKGROUND("\033[42m"),   // GREEN
        YELLOW_BACKGROUND("\033[43m"),  // YELLOW
        BLUE_BACKGROUND("\033[44m"),    // BLUE
        MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
        CYAN_BACKGROUND("\033[46m"),    // CYAN
        WHITE_BACKGROUND("\033[47m"),   // WHITE

        // High Intensity
        BLACK_BRIGHT("\033[0;90m"),     // BLACK
        RED_BRIGHT("\033[0;91m"),       // RED
        GREEN_BRIGHT("\033[0;92m"),     // GREEN
        YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
        BLUE_BRIGHT("\033[0;94m"),      // BLUE
        MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
        CYAN_BRIGHT("\033[0;96m"),      // CYAN
        WHITE_BRIGHT("\033[0;97m"),     // WHITE

        // Bold High Intensity
        BLACK_BOLD_BRIGHT("\033[1;90m"),    // BLACK
        RED_BOLD_BRIGHT("\033[1;91m"),      // RED
        GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
        YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
        BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
        MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
        CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
        WHITE_BOLD_BRIGHT("\033[1;97m"),    // WHITE

        // High Intensity backgrounds
        BLACK_BACKGROUND_BRIGHT("\033[0;100m"),     // BLACK
        RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
        GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
        YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
        BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
        MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
        CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
        WHITE_BACKGROUND_BRIGHT("\033[0;107m");     // WHITE

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
    enum Info {
        VERSION("v0.1.3.1 Beta");

        private final String code;

        Info(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
