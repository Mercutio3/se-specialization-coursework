
/**
 * Write a description of BatchGrayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    public void makeGrayMultiple(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource grayIr = makeGray(ir);
            String fName = f.getName();
            String fNewName = "Grayscale of "+fName;
            grayIr.setFileName(fNewName);
            grayIr.draw();
            grayIr.save();
        }
    }
    public static void main(String[] args){
        BatchGrayscale test = new BatchGrayscale();
        test.makeGrayMultiple();
    }
}
