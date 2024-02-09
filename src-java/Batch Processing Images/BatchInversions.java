
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;

public class BatchInversions {
    public ImageResource makeInvert(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }
    public void makeInvertMultiple(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource inverseIr = makeInvert(ir);
            String fName = f.getName();
            String fNewName = "Inverted of "+fName;
            inverseIr.setFileName(fNewName);
            inverseIr.draw();
            inverseIr.save();
        }
    }
    public static void main(String[] args){
        BatchInversions test = new BatchInversions();
        test.makeInvertMultiple();
    }
}
