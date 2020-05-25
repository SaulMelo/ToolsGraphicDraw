/*------------------------------------------------------------------------------
 * Drawing Hermite and Bezier Curves
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 */
package graphics2;

public class Curves {

    public static void main(String[] args) {

        int image_width = 600;
        int image_height = 600;
        double Step = 0.001;
        
        System.out.println("--------------------------------------------");
        System.out.println("Processing Hermite Curve ...");
        Hermite hermite = new Hermite(image_width, image_height, 0, 0, 0);
        
        int[] P0  = { 128, 495 };
        int[] Pi0 = { 12,  120 };
        int[] P1  = { 313, 493 };
        int[] Pi1 = { 474, 124 };
        
        hermite.steps(P0, P1, Pi0, Pi1, Step);
        hermite.write("C:\\Users\\saulg\\Desktop\\hermite.png");
        
        System.out.println("--------------------------------------------");
        
        System.out.println("Processing Bezier Curve ...");
        Bezier bezier = new Bezier(image_width, image_height, 0, 0, 0);
        
        int[] Point0 = { 93,  239 };
        int[] Point1 = { 207, 150 };
        int[] Point2 = { 150, 350 };
        int[] Point3 = { 339, 249 };
        
        bezier.steps(Point0, Point1, Point2, Point3, Step);
        bezier.write("C:\\Users\\saulg\\Desktop\\bezier.png");      
        
        
        System.out.println("--------------------------------------------");
        
        System.out.println("Processing Interpolated Curve ...");
        InterpolatedCurve interpolated = new InterpolatedCurve(image_width, image_height, 0, 0, 0);

        int[] Point00 = { 128, 495 };
        int[] Point01 = { 12, 120 };
        int[] Point02 = { 313, 493 };
        int[] Point03 = { 474, 124 };
        
        /*
        int[] Point00 = { 93,  239 };
        int[] Point01 = { 207, 150 };
        int[] Point02 = { 150, 350 };
        int[] Point03 = { 339, 249 }; */
        
        interpolated.steps(Point00, Point01, Point02, Point03, Step);
        interpolated.write("C:\\Users\\saulg\\Desktop\\interpolatedCurve.png");     
        
    }
}