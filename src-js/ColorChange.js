/*
 * Created on 25 July 2020
 * @Mercutio3 on GitHub
 */

// Creating image and printing it
var img = new SimpleImage(200,200);
print(img);

// Changing color to yellow and printing it again
for(var pixel of img.values()){
    var newPxl = 255 - pixel.getGreen();
    pixel.setGreen(newPxl);
}

for (var pixel of img.values()){
    var newPxl = 255 - pixel.getRed();
    pixel.setRed(newPxl);
}
print(img);

// Changing color to magenta and printing it again
for (var pixel of img.values()){
    pixel.setGreen(0);
    var newPxl = 255 - pixel.getBlue();
    pixel.setBlue(newPxl);
}
print(img);