/*
 * Created on 27 July 2020
 * @Mercutio3 on GitHub
 */

//Creating and printing original image
var img = new SimpleImage("duke_blue_devil.png");
print(img);

//Turning blue devil green and printing
for(var pixel of img.values()){
    if(pixel.getBlue() > 200 && pixel.getRed() <255){
        pixel.setRed(0);
        pixel.setGreen(255);
        pixel.setBlue(100);
    }
}

print(img);