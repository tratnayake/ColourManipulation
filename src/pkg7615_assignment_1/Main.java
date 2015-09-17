/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg7615_assignment_1;

import guzdial.*;
import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thilinaratnayake
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       
            // TODO code application logic here
            System.out.println("Program Start");
            
            //boolean loopCond = true;
                
            //Loop through each option.
                //while (loopCond){
                System.out.println("Pick a file");
                //1. Pick A File
                String saveDir = FileChooser.getMediaDirectory();
                String fileName = FileChooser.pickAFile();
                System.out.println("The file is "+fileName);

                System.out.println(fileName);

                //2. Grab the picture Object
                Picture pictureObj = new Picture(fileName);
                
                //Prompt user for input
                System.out.println("Enter in your option for the picture \n [1] for a fake sunset [2] for greyscale and [3] for negative");

                Scanner scan = new Scanner(System.in);
                String userInput = scan.nextLine();
                
                //Prints out the user's chosen option - debugging.
                //System.out.println(userInput);

                switch(userInput){
                    case "1":
                        System.out.println("Fake Sunset chosen!");
                        fakeSunset(pictureObj);
                        break;
                    case "2":
                        System.out.println("Greyscale chosen!");
                        greyscale(pictureObj);
                        break;
                    case "3":
                        System.out.println("Negative chosen!");
                        negative(pictureObj);
                        break;
                }
                //Close the program.
                System.exit(0);
            //}      
    }
    
    
   
    private static void fakeSunset(Picture pictureObj){
        //Show the original picture
        pictureObj.show();
        //Wait for input to continue
        System.out.println("Hit any key to continue adding filter to picture");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        
        increaseColour("Red",pictureObj,1.2);
        decreaseColour("Green",pictureObj,1.2);
        decreaseColour("Blue",pictureObj,1.2);
        
        //Repaint the picture object
        pictureObj.repaint();
        
        System.out.println("Image manipulation complete, press any key to close the program.");
        Scanner scan2 = new Scanner(System.in);
        String s2 = scan2.nextLine();
        //Show the final filtered image.
        pictureObj.show();
    }
    
    //Method to turn a (colored) image to greyscale
    private static void greyscale(Picture pictureObj){
        //Show the original picture
        pictureObj.show();
        //Wait for input to continue
        System.out.println("Hit any key to continue adding filter to picture");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        
        Pixel[] pixelArray = pictureObj.getPixels();
        
        for(Pixel pixelObj : pixelArray){
            //Get initial values
            int redValue = pixelObj.getRed();
            int greenValue = pixelObj.getGreen();
            int blueValue = pixelObj.getBlue();
            
            //Alter the RGB values of the pixel in order to process it to a greyscale.
            //0.299 x R / 0.587 x G / 0.114 x B algorithm for greyscale and human perception.
            int grey = (int) (0.299 * redValue + 0.587 * greenValue + 0.114 * blueValue);
            
            //Assigns the new RGB pixel values
            pixelObj.setRed(grey);
            pixelObj.setGreen(grey);
            pixelObj.setBlue(grey);
        }
        //Updates the newly manipulated pixels for the image.
        pictureObj.repaint();
        System.out.println("Image manipulation complete, press any key to close the program.");
        Scanner scan2 = new Scanner(System.in);
        String s2 = scan2.nextLine();
        //Display all the changes made to the photo
        pictureObj.show();
        
    }
    
    private static void negative(Picture pictureObj){
        //Show the original picture
        pictureObj.show();
        //Wait for input to continue
        System.out.println("Hit any key to continue adding filter to picture");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        
        Pixel[] pixelArray = pictureObj.getPixels();
        
        for(Pixel pixelObj : pixelArray){
            //Get initial values
            int redValue = pixelObj.getRed();
            int greenValue = pixelObj.getGreen();
            int blueValue = pixelObj.getBlue();
            
            //Get inverse values and assign the pixels;
            pixelObj.setRed(getInverse(redValue));
            pixelObj.setGreen(getInverse(greenValue));
            pixelObj.setBlue(getInverse(blueValue));
       
        }
        //Update all the pixels in the newly fileterd image.
        pictureObj.repaint();
        System.out.println("Image manipulation complete, press any key to close the program.");
        Scanner scan2 = new Scanner(System.in);
        String s2 = scan2.nextLine();
        //Show the final filtered image.
        pictureObj.show();
    }
    
    public static int getInverse(int colorValue){
        if(colorValue < 127){
            int difference = 127 - colorValue;
            int newValue = 127 + difference;
            return newValue;
        }
        else{
            int difference = colorValue - 127;
            int newValue = 127 - difference;
            return newValue;
        }
    }
    //Supporting method that does all the increasing of a specific colour.
    private static void increaseColour(String colour, Picture pictureObj, double amount){
      
        Pixel[] pixelArray = pictureObj.getPixels();
        
        for (Pixel pixelObj : pixelArray){
            //Red
            if (colour.equals("Red")){
                int colourValue = pixelObj.getRed();
                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue *= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue > 255) {
                    colourValue = 255;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setRed(colourValue);
            }
            
            //Green
            if (colour.equals("Green")) {
                int colourValue = pixelObj.getGreen();
                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue *= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue > 255) {
                    colourValue = 255;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setGreen(colourValue);
            }
            
            //Blue
            if (colour.equals("Blue")) {
                int colourValue = pixelObj.getBlue();

                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue *= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue > 255) {
                    colourValue = 255;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setBlue(colourValue);
            }
        } 
    }   
    //Supporting method that does all the increasing of a specific colour.
    private static void decreaseColour(String colour, Picture pictureObj, double amount){
      
        Pixel[] pixelArray = pictureObj.getPixels();
        
        for (Pixel pixelObj : pixelArray){
            //Red
            if (colour.equals("Red")){
                int colourValue = pixelObj.getRed();
                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue /= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue < 0) {
                    colourValue = 0;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setRed(colourValue);
            }
            
            //Green
            if (colour.equals("Green")) {
                int colourValue = pixelObj.getGreen();
                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue /= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue < 0) {
                    colourValue = 0;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setGreen(colourValue);
            }
            
            //Blue
            if (colour.equals("Blue")) {
                int colourValue = pixelObj.getBlue();

                //System.out.println("Initial red Value is " + redValue);
                //Increment the value of the pixel by 100;
                colourValue /= amount;
                //Check the value, if it's more than 255 max it.
                if (colourValue < 0) {
                    colourValue = 0;
                }
                //System.out.println("New Red Value is " + redValue);
                //Set the pixel;    
                pixelObj.setBlue(colourValue);
            }
        }
    }  
    
}
