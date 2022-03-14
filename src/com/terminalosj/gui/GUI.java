package com.terminalosj.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import javax.swing.plaf.LayerUI;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class GUI extends JFrame {


    public void bGUI() {
        JFrame window = new JFrame("TerminalOS G");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(new JLabel("Hello World"), BorderLayout.CENTER);
        window.setSize(new Dimension(1280, 720));
        window.setMinimumSize(new Dimension(1280, 720));
        UIManager.put("MenuBar.background", Color.CYAN);
        UIManager.put("MenuBar.opaque", true);
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
        BackgroundMenuBar menuBar = new BackgroundMenuBar();
        menuBar.add(createTOSMenu());
        menuBar.setFocusable(true);
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

