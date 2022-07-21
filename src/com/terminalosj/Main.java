package com.terminalosj;
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
import org.apache.commons.io.FileUtils;

import javax.swing.JFrame;
import com.terminalosj.gui.GUI;

public class Main extends JFrame {
    // Managing Scripts
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        final String os = System.getProperty("os.name");
        cls();
        String home = System.getProperty("user.home");
        File b = new File(home + "/LaunchTerminalOS.bat");
        if (b.exists() && !b.isDirectory()) {
        } else {
            if (os.contains("Windows")) {
                System.out.println("BATCH Launch Script not found...");
                launchscripts();
            } else {
            }
        }
        File s = new File(home + "/Desktop/LaunchTerminalOS.sh");
        if (s.exists() && !s.isDirectory()) {
        } else {
            if (os.contains("Windows")) {
            } else {
                System.out.println("SH Launch Script not found...");
                launchscripts();
            }
        }
        methodlist();
    }
    static void methodlist() throws IOException, InterruptedException, URISyntaxException {
        startup();
        login();
        mainmenu();
    }
    static void launchscripts() throws URISyntaxException, IOException, InterruptedException {
        String homeFolder = System.getProperty("user.home");
        final String os = System.getProperty("os.name"); //detect OS
        if (os.contains("Windows")) {
            try { //create startup scripts for windows
                String jarPath = Main.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            String jarPath2 = jarPath.substring(1);
                File logo = new File(homeFolder + "\\LaunchTerminalOS.bat");
                if (logo.createNewFile()) {
                } else {
                    System.out.println("BATCH Launch File created in users home directory");
                }
            } catch (IOException e) {
                System.out.println("Failed to create startup script");
                e.printStackTrace();
            }
            // get JAR directory
            String jarPath = Main.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            String jarPath2 = jarPath.substring(1);
            Path batfileName = Path.of(homeFolder + "\\LaunchTerminalOS.bat");
            String content  = "@echo off\ntitle TerminalOS\njava -jar \"" + jarPath2 + "\"\npause";
            Files.writeString(batfileName, content);
            String actual = Files.readString(batfileName);

        } else {
            try { //create startup script for mac/linux
                File logo = new File(homeFolder + "/Desktop/LaunchTerminalOS.sh");
                if (logo.createNewFile()) {
                    System.out.println("SH Launch File created on users Desktop.");
                } else {
                }
            } catch (IOException e) {
                System.out.println("Failed to create setup file");
                e.printStackTrace();
            }
            // get JAR directory
            String jarPath0 = Main.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI()
                    .getPath();
            String jarPath = jarPath0.replaceAll("\\s", "\\\\ ");
            Path shfileName = Path.of(homeFolder + "/Desktop/LaunchTerminalOS.sh");
            String content  = "#!/bin/sh\necho -n -e \"\\033]0;TerminalOS\\007\"\njava -jar " + jarPath;
            Files.writeString(shfileName, content);
            String actual = Files.readString(shfileName);
        }
        System.exit(0);

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

    }

    //Main Menu Application
    static void mainmenu() throws IOException, InterruptedException, URISyntaxException { // Main Menu Method
        cls();
        menulogo();
        System.out.println("\n What would you like to do?\n\n");
        System.out.println(" A > Open Applications Folder");
        System.out.println(" S > " + Color.RED_BRIGHT + "[EXPERIMENTAL]" + Color.RESET + " Settings Menu\n");
        System.out.println(" G > " + Color.RED_BACKGROUND_BRIGHT + " -- [ALPHA] -- " + Color.RESET + " Launch TerminalOS G\n");
        final String ops = System.getProperty("os.name");
        if (ops.contains("Windows")) {
            bapps();
        }
        apps();
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String rawuserinput = Input.next(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        String openapps = "A";
        String Settings = "S";
        String TOSG = "G";
        if (userinput.equals(openapps)) {
            File file = new File ("./TerminalOS/Applications");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            cls();
        }
        if (userinput.equals(Settings)) {
            Settings();
        }
        if (userinput.equals(TOSG)) {
            GUI bGUI = new GUI();
            bGUI.bGUI();
        } else { // this launches user selected jar / bat
            if (userinput.endsWith(".bat")) {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    String currentDirectory = System.getProperty("user.dir");
                    new ProcessBuilder("cmd", "/c", "call \"" + currentDirectory + "\\TerminalOS\\Applications\\" + userinput + "\"").inheritIO().start().waitFor();
                    new ProcessBuilder("cmd", "/c", "title TerminalOS").inheritIO().start().waitFor();
                    System.out.print(Color.RESET);
                }
            }
            if (userinput.endsWith(".jar")) {
                cls();
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "java -jar \"./TerminalOS/Applications/" + userinput + "\"").inheritIO().start().waitFor();
                } else {
                    ProcessBuilder processBuilder = new ProcessBuilder();
                    processBuilder.command("bash", "-c", "java -jar ./TerminalOS/Applications/" + userinput).inheritIO().start().waitFor();
                }
            }
        }
        mainmenu();
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
    // Built in System Information application..
    static void SystemInfo() throws IOException, InterruptedException, URISyntaxException {
        cls();
        final String os = System.getProperty("os.name"); //detect OS
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        String cpuid = cpu.getProcessorIdentifier().getName();
        double diskSizeB = new File("/").getTotalSpace();
        String userName = System.getProperty("user.name");
        double memorySizeB = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        //memory math
        double memorySizeDivide = 1024;
        double memorySizeK = memorySizeB / memorySizeDivide;
        double memorySizeM = memorySizeK / memorySizeDivide;
        double memorysizeG = memorySizeM / memorySizeDivide;
        //disk math
        double diskSizeK = diskSizeB / memorySizeDivide;
        double diskSizeM = diskSizeK / memorySizeDivide;
        double diskSizeG = diskSizeM / memorySizeDivide;
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        System.out.println("       .7???????????" + Color.CYAN_BRIGHT + "    CPU Model: " + cpuid  + Color.RESET);
        System.out.println("        !!!!!!!!????" + Color.CYAN_BOLD_BRIGHT + "        - Core Count: " + Runtime.getRuntime().availableProcessors() + Color.RESET);
        System.out.println("               .7???" + Color.MAGENTA_BRIGHT + "    System Memory: " + Math.round(memorySizeM) + " MB" + Color.RESET);
        System.out.println("^^^^    :^^:   .7???" + Color.MAGENTA_BOLD_BRIGHT + "        - " + Math.round(memorysizeG) + " GB" + Color.RESET);
        System.out.println("????.  .????.  .????" + Color.YELLOW_BRIGHT + "    System Storage: " + Math.round(diskSizeM) + " MB" +Color.RESET);
        System.out.println("???7.   :^^^    ^^^^" + Color.YELLOW_BRIGHT + "        - " + Math.round(diskSizeG) + " GB" + Color.RESET);
        System.out.println("???7.               "+ Color.GREEN_BRIGHT + "    Host OS: " + operatingSystem.toString() + Color.RESET);
        System.out.println("????!!!!!!!!        ");
        System.out.println("???????????7.       ");
        seperator();
        if (os.contains("Windows")) {
            String homeFolder = System.getProperty("user.home");
            System.out.println("TerminalOS Directory: " + homeFolder + "\\TerminalOS");
        } else {
            String homeFolder = System.getProperty("user.home");
            System.out.println("TerminalOS Directory: " + homeFolder + "./TerminalOS");
        }
        System.out.println("Current User: " + userName);
        seperator();
        logo();
        System.out.println("\n TerminalOS Version: " + Color.BLUE_BRIGHT + "TerminalOS v0.1.3 - Git Update" + Color.RESET);

        Scanner Input = new Scanner(System.in);  // User Input
        smolpause();
        System.out.println("\n  Press Enter to go back to Settings Menu > ");
        String rawuserinput = Input.nextLine(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        Settings();
    }


    // Built in Settings application..
    static void Settings() throws IOException, URISyntaxException, InterruptedException {
        cls();
        settingslogo();
        System.out.println("\n M > Return to Main Menu\n\n TerminalOSJ Settings:\n\n P > Reset TerminalOS Password\n S > System Information");
        Scanner Input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n  Listening > ");
        String rawuserinput = Input.nextLine(); // Read user input
        String userinput = rawuserinput.replaceAll("\\s", "\\\\ ");
        String mainmenu = "M";
        String resetpass = "P";
        String SystemInfo = "S";
        if (userinput.equals(mainmenu)) {
            mainmenu();
        }else if (userinput.equals(resetpass)) {
            //String fileName = "./TerminalOSJ/Settings/login.txt";
            String homeFolder = System.getProperty("user.home");
            PrintWriter writepass = new PrintWriter(homeFolder + "/TerminalOS-Data/Settings/login.txt", "UTF-8");
            writepass.print("");
            writepass.close();
            login();
        }else if (userinput.equals(SystemInfo)) {
            SystemInfo();
        }else{
            Settings();
        }
    }

    // ALPHA GUI


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
}
