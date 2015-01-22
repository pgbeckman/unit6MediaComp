import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method to keep blue */
    public void keepBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right 
     * */
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = width-1; col > width / 2; col--)
            {
                rightPixel = pixels[row][col];
                leftPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel bottomPixel = null;
        Pixel topPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorToptoBotHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel bottomPixel = null;
        Pixel topPixel = null;
        int height = pixels.length;
        for (int row = height - 1; row > height / 2; row--)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                bottomPixel = pixels[row][col];
                topPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel bottomPixel = null;
        Pixel topPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < height && row < width ; row++)
        {
            for (int col = 0; col < height && col < width; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[col][row];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorTemple()
    {
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorSnowman()
    {
        int mirrorPoint = 190;
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels[0].length;

        // loop through the rows
        for (int row = 0; row < 20; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 0; col < width; col++)
            {
                topPixel = pixels[mirrorPoint-row][col];      
                bottomPixel = pixels[mirrorPoint+row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, int startDestRow, int startDestCol, double shrinkFactor)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        int height = fromPixels.length;
        int width = fromPixels[0].length;
        for (int fromRow = 0, toRow = startDestRow; 
        fromRow < height &&
        toRow < height+startDestRow; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startDestCol; 
            fromCol < width &&
            toCol < width+startDestCol;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                    toPixel = toPixels[(int)(shrinkFactor*toRow)][(int)(shrinkFactor*toCol)];
                    toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(pixels[row][col].getColor());
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture and rotates it
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copyRotate(Picture fromPic, int startDestRow, int startDestCol, double degrees, double shrinkFactor)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        double radians = (Math.PI/180.0)*degrees;
        int height = fromPixels.length;
        int width = fromPixels[0].length;
        for (int fromRow = 0, toRow = startDestRow; 
        fromRow < height &&
        toRow < height+startDestRow; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startDestCol; 
            fromCol < width &&
            toCol < width+startDestCol;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                if(fromPixel.getRed()<230 &&
                   fromPixel.getGreen()<230 &&
                   fromPixel.getBlue()<230){
                    toPixel = toPixels[(int)(shrinkFactor*(toRow+(Math.sin(radians)*fromCol)))][(int)(shrinkFactor*(toCol+(Math.sin(radians)*(height-fromRow))))];
                    toPixel.setColor(fromPixel.getColor());
                }
            }
        }   
    }
    
    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture and changes transparency
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copyTransparent(Picture fromPic, int startDestRow, int startDestCol, double shrinkFactor, double transFactor)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        int setRed = 0;
        int setBlue = 0;
        int setGreen = 0;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        int height = fromPixels.length;
        int width = fromPixels[0].length;
        for (int fromRow = 0, toRow = startDestRow; 
        fromRow < height &&
        toRow < height+startDestRow; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startDestCol; 
            fromCol < width &&
            toCol < width+startDestCol;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[(int)(shrinkFactor*toRow)][(int)(shrinkFactor*toCol)];
                if(toPixel.getRed()+transFactor*(255-fromPixel.getRed())<=255){
                setRed = (int)(toPixel.getRed()+transFactor*(255-fromPixel.getRed()));
            }
            else{
                setRed = 255;
            }
            if(toPixel.getBlue()+transFactor*(255-fromPixel.getBlue())<=255){
                setBlue = (int)(toPixel.getBlue()+transFactor*(255-fromPixel.getBlue()));
            }
            else{
                setBlue = 255;
            }
            if(toPixel.getGreen()+transFactor*(255-fromPixel.getGreen())<=255){
                setGreen = (int)(toPixel.getGreen()+transFactor*(255-fromPixel.getGreen()));
            }
            else{
                setGreen = 255;
            }
                Color setToColor = new Color(setRed, setGreen, setBlue);
                toPixel.setColor(setToColor);
            }
        }   
    }
    
    /** reduces specificity of color palette
     * @param categoryWidth
     */
    public void posterize(int categoryWidth)
    {
        Pixel selectedPixel = null;
        Color setToColor = null;
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length-1; col++)
            {
                selectedPixel = pixels[row][col];
                int setRed = Math.round((selectedPixel.getRed())/categoryWidth)*categoryWidth;
                int setGreen = Math.round((selectedPixel.getGreen())/categoryWidth)*categoryWidth;
                int setBlue = Math.round((selectedPixel.getBlue())/categoryWidth)*categoryWidth;
                 if(setRed<255-categoryWidth &&
                    setGreen<255-categoryWidth &&
                    setBlue<255-categoryWidth){
                setToColor = new Color(setRed, setGreen, setBlue);
                selectedPixel.setColor(setToColor);
              }
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture canvas = new Picture("blankCanvas.jpg");
        Picture yak = new Picture("yak.jpg");
        Picture apple = new Picture("apple.jpg");
        Picture pear = new Picture("pear.jpg");
        canvas.copy(apple, 20, 20, .8);
        apple.posterize(50);
        canvas.copyRotate(apple, 110, 400, 15, .6);
        apple.posterize(100);
        canvas.copyRotate(apple, 400, 1200, 30, .4);
        apple.posterize(200);
        canvas.copyRotate(apple, 1500, 3500, 45, .2);
        canvas.copyTransparent(pear, 300, 300, .5, .3);
        canvas.copyTransparent(pear, 700, 900, .5, .16);
        canvas.copyTransparent(pear, 400, 1300, .5, .05);
        canvas.write("H:\\GitHub\\unit6MediaComp\\PictureLab\\images\\Collage.jpg");
        canvas.explore();
    }

} // this } is the end of class Picture, put all new methods before this
