// Create and print original image
var img = new SimpleImage("hilton.jpg");
var imgWidth = img.getWidth();
print(img);

// Create colorstripes and print new image
for (var pixel of img.values()){
    var xCoord = pixel.getX();
    if(xCoord <= imgWidth / 3){
        pixel.setRed(255);
    } else if(xCoord <= imgWidth / 3 + imgWidth / 3){
        pixel.setGreen(255);
    } else {
        pixel.setBlue(255);
    }
}

print(img);