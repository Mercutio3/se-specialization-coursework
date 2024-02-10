/*
 * Created on 13 August 2020
 * @Mercutio3 on GitHub
 */

function crop(image, width, height){
    var cropimage = new SimpleImage(width, height);
    for(var pixel of image.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        if(x<width && y<height){
            cropimage.setPixel(x, y, pixel);
        }
    }
    return cropimage;
}

function clearLastBits(colornum){
    var x = Math.floor(colornum/16)*16;
    return x;
}

function chop2hide(image){
    for(var pixel of image.values()){
        pixel.setRed(clearLastBits(pixel.getRed()));
        pixel.setGreen(clearLastBits(pixel.getGreen()));
        pixel.setBlue(clearLastBits(pixel.getBlue()));
    }
    return image;
}

function hide(image){
    for(var pixel of image.values()){
        pixel.setRed(pixel.getRed() / 16);
        pixel.setGreen(pixel.getGreen() / 16);
        pixel.setBlue(pixel.getBlue() / 16);
    }
    return image;
}

function combine(image, image2){
    var output = new SimpleImage(shownImg.getWidth(), shownImg.getHeight());
    for(var pixel of output.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        
        var shownPixel = image.getPixel(x, y);
        var hiddenPixel = image2.getPixel(x, y);
        
        pixel.setRed(shownPixel.getRed() + hiddenPixel.getRed());
        pixel.setGreen(shownPixel.getGreen() + hiddenPixel.getGreen());
        pixel.setBlue(shownPixel.getBlue() + hiddenPixel.getBlue());
    }
    return output;
}

var shownImg = new SimpleImage("usain.jpg");
var hiddenImg = new SimpleImage("smalllion.jpg");
shownImg = crop(shownImg, hiddenImg.getWidth(), hiddenImg.getHeight());
print(shownImg);
print(hiddenImg);
shownImg = chop2hide(shownImg);
hiddenImg = hide(hiddenImg);
var combinedImg = combine(shownImg, hiddenImg);
print(combinedImg);