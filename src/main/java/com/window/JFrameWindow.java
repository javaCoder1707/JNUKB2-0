package com.window;

import javax.swing.*;
import java.awt.*;

public class JFrameWindow {
    public static void createWindow(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("JavaNewsUkBot");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(200, 100));
        frame.pack();
        frame.setVisible(true);
    }
}
