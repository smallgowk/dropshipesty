/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import static com.ping.DesignTest.joinBufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author duy.phan
 */
public class ImageUtils {
    
    public static void combineImage(BufferedImage img1,
            BufferedImage img2, int itemSize, Point point) {
        BufferedImage joinedImg = joinBufferedImage(img1, img2, itemSize, point);
        try {
            ImageIO.write(joinedImg, "png", new File("output.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static BufferedImage joinBufferedImage(BufferedImage img1,
            BufferedImage img2, int itemSize, Point point) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, point.x, point.y, itemSize, itemSize, null);
        g2.dispose();
        return newImage;
    }
}
