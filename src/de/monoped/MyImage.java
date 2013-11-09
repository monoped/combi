package de.monoped;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: bernd
 * Date: 09.11.13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class MyImage extends JPanel {
    BufferedImage image;
    float           opacity;

    MyImage(File file, float opa) throws IOException
    {
        image = ImageIO.read(file);

        setBounds(0, 0, image.getWidth(), image.getHeight());
        setOpaque(true);
        opacity = opa;
    }

    MyImage(File file) throws IOException
    {
        this(file, 1);
    }

    void setOpacity(float opa)
    {
        opacity = opa;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gr = (Graphics2D)g;

        gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        gr.drawImage(image, 0, 0, this);
    }

}
