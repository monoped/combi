package de.monoped;

import de.monoped.utils.Getopt;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Main {
    JFrame              frame;
    JLayeredPane        layeredPane;
    Dimension           screenDim = new Dimension(480, 272);
    final int           screenWidth = screenDim.width;
    final int           screenHeight = screenDim.height;
    javax.swing.Timer   timer;

    Main(String dir, java.util.List<String> list) throws IOException {
        frame = new JFrame("Combi");
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, screenWidth, screenHeight);
        frame.add(layeredPane);

        File    fdir = new File(dir);

        ArrayList<> files = new ArrayList<File>();

        for (ListIterator<String> it = list.listIterator(); it.hasNext();) {
            String  s = it.next();
            File    f = new File(fdir, s);

            if (f.canRead()) files.add(f);
        }
    }

    public static void main(String[] args) throws IOException {
        Getopt                  opts = new Getopt(args, "d:f:");
        String                  inputPath = null;
        String                  inputDir = System.getProperty("user.dir");
        int                     opt;
        java.util.List<String>  files;

        while ((opt = opts.getOption()) != -1)  {
            switch (opt) {
                case 'd': inputDir = opts.getOptarg(); break;
                case 'f': inputPath = opts.getOptarg(); break;
            }
        }

        if (inputPath != null)
            files = Files.readAllLines(Paths.get(inputPath), Charset.defaultCharset());
        else
            files = Arrays.asList(opts.getParms());

        new Main(inputDir, files).start();
    }

    private void start() {
        frame.setVisible(true);
    }
}

class MainComp extends JPanel {
    MainComp() {

    }

}
