package com.terminalosj;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {


    public void bGUI() {
        //instead of setName()



        JFrame window = new JFrame("TerminalOS G");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(new JLabel("Hello World"), BorderLayout.CENTER);
        window.setSize(new Dimension(1280, 720));
        window.setMinimumSize(new Dimension(1280, 720));
        window.pack();
        try {
            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./TerminalOS/Resources/wallpaper.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.setJMenuBar(createMenuBar());
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        //pack();
        //etc etc
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createTOSMenu());
        UIManager.put("MenuBar.background", Color.TRANSLUCENT);
        return menuBar;
    }

    private JMenu createTOSMenu() {
        JMenu fileMenu = new JMenu("TerminalOS");
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        return fileMenu;
    }
}
