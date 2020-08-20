/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping;

import com.utils.OSUtil;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author PhanDuy
 */
public class DesignTest {

    public static void main(String args[]) throws Exception {
        BufferedImage img1 = ImageIO.read(new File("3.jpg"));
        BufferedImage img2 = ImageIO.read(new File("1.png"));
        BufferedImage joinedImg = joinBufferedImage(img1, img2);
        ImageIO.write(joinedImg, "png", new File("output.png"));

        OSUtil.showImage(new File("output.png"));
    }

    public static void drawString(Graphics2D g2, String str, BufferedImage img2, Font font, int x, int y) {

        BufferedImage img = img2.getSubimage(x, y, 100, 30);

        FontRenderContext frc = new FontRenderContext(null, true, true);
        Font newFont = font.deriveFont(Font.PLAIN, 18);
        GlyphVector gv = newFont.createGlyphVector(frc, str);
        g2.setColor(new Color(8, 0, 0));

        g2.setStroke(new BasicStroke(10.0f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.drawGlyphVector(gv, x, y);
    }

    public static BufferedImage joinBufferedImage(BufferedImage img1,
            BufferedImage img2) {
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
        g2.drawImage(img2, width / 2 - width / 8, height / 2 - height / 8, width / 4, height / 4, null);
        g2.drawImage(img2, width / 2 + width / 8, height / 4, width / 8, height / 8, null);
        g2.dispose();
        return newImage;
    }
}
