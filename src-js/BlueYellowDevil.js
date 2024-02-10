// Creating and printing original image
var img = new SimpleImage("duke_blue_devil.png");
print(img);

// Changing and printing new image
for (var pixel of img.values()){
    if(pixel.getRed() < 255){
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
}

print(img);
