import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class DesktopClient {
    public static JFrame setup = new JFrame("bit.wav (Desktop Client)");
    public static String songRequest;
    public static ProcessBuilder pb;
    public static Process start;

    private static void gameLauncher() {
        Font settingFonts = new Font("Nexa Bold", Font.PLAIN, 50);
        Font font2 = new Font ("Nexa Regular", Font.BOLD, 16);
        JLabel title = new JLabel("bit.wav",  SwingConstants.CENTER);
        JLabel label2 = new JLabel("Enter the name of the file to be played:");
        TextField field = new TextField(35);

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    songRequest = field.getText();
                    boolean check = new File("assets/", songRequest).exists();

                    if (check) {
                        pb = new ProcessBuilder("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "assets/" + songRequest);
                    } else {
                        // Code for server retrieval
                    }

                    try {
                        start = pb.start();
                    } catch (IOException x){

                    }
                }
            }
        });

        setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setup.getContentPane().setBackground(new Color(196,196,196));
        setup.setLayout(new FlowLayout());
        setup.getRootPane().setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.BLACK));
        title.setFont(settingFonts);
        setup.add(Box.createVerticalStrut(80));
        setup.add(title);
        setup.add(Box.createHorizontalStrut(1000));
        label2.setFont(font2);
        setup.add (label2);
        setup.add(Box.createHorizontalStrut(5));
        field.setFont (new Font ("Nexa Regular", Font.PLAIN, 16));
        setup.add(field);

        setup.pack();
        setup.setSize(860, 200);
        setup.setVisible(true);
        setup.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        gameLauncher();
    }
}

