/*------------------------------------------------------------------------------
 * Hermite Curve: Drawing Curve using Hermite Algorithm
 * Advanced Computer Graphics
 * Written by: Kevin T. Duraj
 */
package graphics2;

/*------------------------------------------------------------------------------
 * Class Hermite using Bresenham between points
 */
public class Hermite extends Bresenham {

    public Hermite(int width_p, int height_p, int r, int g, int b) {

        super(width_p, height_p, r, g, b);
    }
    /*--------------------------------------------------------------------------
     *  Compute Hemite Cubic Points Derivatives 
     *  +-----------------+   +-----------+
     *  |  2  -2   1   1  |   |  x0   y0  |
     *  | -3   3  -2  -1  | * |  x1   y1  | * [t^3 t^2 t 1] = [x, y]
     *  |  0   0   1   0  |   |  x'0  y'0 |
     *  |  1   0   0   0  |   |  x'1  y'1 |
     *  +-----------------+   +-----------+
     -------------------------------------------------------------------------*/
    
    public int[] cubic(int[] P0, int[] P1, int[] Pi0, int[] Pi1) {   
        
        /*------------------ Firt Column --------------------------*/
        int C3x = (2*P0[0])  + (-2*P1[0]) + (1*Pi0[0])  + (1*Pi1[0]); 
        int C2x = (-3*P0[0]) + (3*P1[0])  + (-2*Pi0[0]) + (-1*Pi1[0]); 
        int C1x = (0)        + (0)        + (1*Pi0[0])  + (0); 
        int C0x = (1*P0[0])  + (0)        + (0)         + (0);   

        /*------------------ Second  Column -----------------------*/
        int C3y = (2*P0[1])  + (-2*P1[1]) + (1*Pi0[1])  + (1*Pi1[1]); 
        int C2y = (-3*P0[1]) + (3*P1[1])  + (-2*Pi0[1]) + (-1*Pi1[1]); 
        int C1y = (0)     + (0)     + (1*Pi0[1])  + (0); 
        int C0y = (1*P0[1])  + (0)     + (0)      + (0); 
 
        /*---------------------------------------------------------*/

        int array[] = {C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y};
        return array;

    }
    /*------------------------------------------------------------------------
     * Create Steps Method
     * Increasing steps make curve smooth
     * Calculate "z" as 3rd dimension
     */ 
    
    public void steps(int[] P0, int[] P1, int[] Pi0, int[] Pi1, double step)
    {    
        // Compute Vector
        Pi0[0] = Pi0[0] - P0[0];
        Pi0[1] = Pi0[1] - P0[1];
        Pi1[0] = Pi1[0] - P1[0];
        Pi1[1] = Pi1[1] - P1[1];
        
        int array[] = cubic(P0, P1, Pi0, Pi1);
        int x, y, z;
        int C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y;
        
        C0x = array[0];
        C0y = array[1];
        C1x = array[2];
        C1y = array[3];
        C2x = array[4];
        C2y = array[5];
        C3x = array[6];
        C3y = array[7];
        
        int array2[][] = new int [(int)(1/step)][2];
        int i=0;
        
        for (double u = 0.00; u < 1; u += step) {
            x = (int) (C0x + C1x * u + C2x * u * u + C3x * u * u * u);
            y = (int) (C0y + C1y * u + C2y * u * u + C3y * u * u * u);
            
            array2[i][0] = x;
            array2[i][1] = y;
            i++;
            
            set_pixel(x, y, 255, 0, 0);
               
        }
        
        int j;
        for (j = 0; j < (int) (1 / step) - 1; j++) {

            bresenhamLine( array2[j][0]
                         , array2[j][1]
                         , array2[j + 1][0]
                         , array2[j + 1][1]
                         , 255, 0, 0);
        }
      
        // Draw the last line to the end point

        bresenhamLine(  array2[j][0]
         , array2[j][1]
         , P1[0]
         , P1[1]
         , 255, 0, 0);   
        

    }
    
     /*--------------------------------------------------------------------------
     *  Compute Interpolated Cubic Points
     *  +-----------------+   +-----------+
     *  | -4.5   13.5  -13.5   4.5 |   |  x0   y0  |
     *  |  9    -22.5   18    -4.5 | * |  x1   y1  | * [t^3 t^2 t 1] = [x, y]
     *  | -5.5   9     -4.5    1   |   |  x2   y2  |
     *  |  1     0      0       0  |   |  x3   y3  |
     *  +-----------------+   +-----------+
     -------------------------------------------------------------------------*/
    
    /**public int[] cubicInterpolatedCurve(int[] P0, int[] P1, int[] P2, int[] P3) {   
        
        /*------------------ Firt Column --------------------------*/
    /**    int C3x = (int) ((-4.5*P0[0])  + (13.5*P1[0])  + (-13.5*P2[0])  + (4.5*P3[0])); 
        int C2x = (int) ((9*P0[0])     + (-22.5*P1[0]) + (18*P2[0])     + (-4.5*P3[0])); 
        int C1x = (int) ((-5.5*P0[0])  + (9*P1[0])     + (-4.5*P2[0])   + (1*P3[0])); 
        int C0x = (int) ((1*P0[0])     + (0)           + (0)            + (0));   

        /*------------------ Second  Column -----------------------*/
    /**    int C3y = (int) ((-4.5*P0[1])  + (13.5*P1[1])  + (-13.5*P2[1])  + (4.5*P3[1])); 
        int C2y = (int) ((9*P0[1])     + (-22.5*P1[1]) + (18*P2[1])     + (-4.5*P3[1])); 
        int C1y = (int) ((-5.5*P0[1])  + (9*P1[1])     + (-4.5*P2[1])   + (1*P3[1])); 
        int C0y = (int) ((1*P0[1])     + (0)           + (0)            + (0));   
 
        /*---------------------------------------------------------*/

    /**    int array[] = {C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y};
        return array;

    }
    /*------------------------------------------------------------------------
     * Create Steps Method
     * Increasing steps make curve smooth
     * Calculate "z" as 3rd dimension
     */ 
    /**
    public void stepsInterpolatedCurve(int[] P0, int[] P1, int[] P2, int[] P3, double step)
    {    

        int array[] = cubicInterpolatedCurve(P0, P1, P2, P3);
        int x, y, z;
        int C0x, C0y, C1x, C1y, C2x, C2y, C3x, C3y;
        
        C0x = array[0];
        C0y = array[1];
        C1x = array[2];
        C1y = array[3];
        C2x = array[4];
        C2y = array[5];
        C3x = array[6];
        C3y = array[7];
        
        int array2[][] = new int [(int)(1/step)][2];
        int i=0;
        
        for (double u = 0.00; u < 1; u += step) {
            x = (int) (C0x + C1x * u + C2x * u * u + C3x * u * u * u);
            y = (int) (C0y + C1y * u + C2y * u * u + C3y * u * u * u);
            
            array2[i][0] = x;
            array2[i][1] = y;
            i++;
            
            set_pixel(x, y, 255, 0, 0);
               
        }
        
        int j;
        for (j = 0; j < (int) (1 / step) - 1; j++) {

            bresenhamLine( array2[j][0]
                         , array2[j][1]
                         , array2[j + 1][0]
                         , array2[j + 1][1]
                         , 255, 0, 0);
        }
      
        // Draw the last line to the end point

        bresenhamLine(  array2[j][0]
         , array2[j][1]
         , P1[0]
         , P1[1]
         , 255, 0, 0);   
        

    } */
    
}
    
/*----------------------------------------------------------------------------*/