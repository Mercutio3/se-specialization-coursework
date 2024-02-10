// Experimenting with variables and creating SimpleImage
var x = 5;
var y = 11;
var z = x*y / 2;
print("x = "+x);
print("y = "+y);
print("z = "+z);
y = x;
print(x);
print(y);
var chapelImg = new SimpleImage("usain.jpg");
print(chapelImg);

//Experimenting with SimpleImage's methods
var img1w = chapelImg.getWidth();
print("chapelImg's width is: "+img1w);
var img1h = chapelImg.getHeight();
print("chapelImg's height is: "+img1h);
print(chapelImg.getRed(0,0)+", "+chapelImg.getGreen(0,0)+", "+chapelImg.getBlue(0,0));
var zerozeroPxl = chapelImg.getPixel(0,0);
print(zerozeroPxl);

//Experimenting with functions + extra challenges
function square(x){
    return x * x;
}
var a = square(4);
print(a);
print(square(5));
print(square(11));

function cube(x){
    return x * x * x;
}
var b = cube(4);
print(b);

function addThreeNums(x, y, z){
    return x + y + z;
}
var c = addThreeNums(1, 2, 3);
print(c);