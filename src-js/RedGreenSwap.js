/*
 * Created on 26 July 2020
 * @Mercutio3 on GitHub
 */

// Creating and printing original image
var img = new SimpleImage("smalllion.jpg");
print(img);

function swapRedGreen(x, y){
    var pxl = img.getPixel(x, y);
    var pxlR = pxl.getRed();
    var pxlG = pxl.getGreen();
    pxl.setRed(pxlG);
    pxl.setGreen(pxlR);
}

// Change colors and print new image
for(var pixel of img.values()){
    swapRedGreen(pixel.getX(), pixel.getY());
}
print(img);