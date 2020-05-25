/*
 * Drawing Line using Bresenham Algorithm
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 */
package graphics2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Bresenham Image Class
 */
public class Bresenham {

    int height;
    public int[][][] image;
    int width;

    public Bresenham(int width_p, int height_p, int r, int g, int b) {
        System.out.println("Superclass ...");
        this.width = width_p;
        this.height = height_p;
        image = new int[3][height][width];

        for (int i = 0; i < height; ++i) {

            for (int j = 0; j < width; ++j) {
                image[0][i][j] = (byte) r;
                image[1][i][j] = (byte) g;
                image[2][i][j] = (byte) b;

            }
        }
    }
    /*--------------------------------------------------------------------------
     * Drawing Circle using Bresenham Algorithm
     */

    public void bresenhamCircle(int x, int y, int radius, int r, int g, int b) {
        int i = 0;
        int j = radius;
        while (i <= j) {
            set_pixel(x + i, y - j, r, g, b);
            set_pixel(x + j, y - i, r, g, b);
            set_pixel(x + i, y + j, r, g, b);
            set_pixel(x + j, y + i, r, g, b);
            set_pixel(x - i, y - j, r, g, b);
            set_pixel(x - j, y - i, r, g, b);
            set_pixel(x - i, y + j, r, g, b);
            set_pixel(x - j, y + i, r, g, b);
            i++;
            j = (int) (Math.sqrt(radius * radius - i * i) + 0.5);
        }
    }
    /*--------------------------------------------------------------------------
     *  Private method set_pixel sets pixel in the image
     */

    protected void set_pixel(int x, int y, int r, int g, int b) {
        try {
            //System.out.println("y=" + x + " x=" + y);
            if (y > -1 && x > -1 && y < height && x < width) {
                image[0][y][x] = (byte) r;
                image[1][y][x] = (byte) g;
                image[2][y][x] = (byte) b;
            }
        } catch (Exception e) {
            System.err.println("Exception: y=" + y + " x=" + x);
        }
    }

    /*--------------------------------------------------------------------------
     * Drawing Line using Bresenham Algorithm
     */
    public void bresenhamLine(int x0, int y0, int x1, int y1, int r, int g, int b) {
        int delta_width = x1 - x0;
        int delta_height = y1 - y0;
        int dx0 = 0;
        int dy0 = 0;
        int dx1 = 0;
        int dy1 = 0;
        if (delta_width < 0) {
            dx0 = -1;
        } else if (delta_width > 0) {
            dx0 = 1;
        }
        if (delta_height < 0) {
            dy0 = -1;
        } else if (delta_height > 0) {
            dy0 = 1;
        }
        if (delta_width < 0) {
            dx1 = -1;
        } else if (delta_width > 0) {
            dx1 = 1;
        }
        int longest = Math.abs(delta_width);
        int shortest = Math.abs(delta_height);
        if (!(longest > shortest)) {
            longest = Math.abs(delta_height);
            shortest = Math.abs(delta_width);
            if (delta_height < 0) {
                dy1 = -1;
            } else if (delta_height > 0) {
                dy1 = 1;
            }
            dx1 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            set_pixel(x0, y0, r, g, b);
            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x0 += dx0;
                y0 += dy0;
            } else {
                x0 += dx1;
                y0 += dy1;
            }
        }
    }



    /*--------------------------------------------------------------------------
     *  Move image into BufferedImage object then write Image into the File
     */
    public void write(String filename) {
        System.out.println("Writing image into: " + filename);
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    int pixel = (image[0][i][j] << 16) | (image[1][i][j] << 8) | (image[2][i][j]);
                    bi.setRGB(j, i, pixel);
                }
            }
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println("Image write error");
        }
        System.out.println("Sucessfull");
    }
}
