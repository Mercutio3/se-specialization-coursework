// Add images
var panda = new SimpleImage("smallpanda.png");
var skyline = new SimpleImage("skyline.jpg");

function setBlack(x, y, picture){
    var pxl = picture.getPixel(x,y)
    pxl.setRed(0);
    pxl.setGreen(0);
    pxl.setBlue(0);
}

function addBorder(image, bwidth){
    for(var pixel of image.values()){
        var imgx = pixel.getX();
        var imgy = pixel.getY();
        if(pixel.getY()<=bwidth||pixel.getY()>=image.getHeight()-bwidth){
            setBlack(imgx, imgy, image);
        }
        if(pixel.getX()<=bwidth||pixel.getX()>=image.getWidth()-bwidth){
            setBlack(imgx, imgy, image);
        }
    }
}

// Call addBorder function to any image previously added, with parameter for width
addBorder(skyline, 10);
addBorder(panda, 5);

print(panda);
print(skyline);