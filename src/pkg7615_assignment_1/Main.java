/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg7615_assignment_1;

import guzdial.*;
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
            
            boolean loopCond = true;
                
            //Loop through each option.
                while (loopCond){
                System.out.println("Pick a file");
                //1. Pick A File
                String saveDir = FileChooser.getMediaDirectory();
                String fileName = FileChooser.pickAFile();
                System.out.println("The file is "+fileName);

                System.out.println(fileName);

                //2. Grab the picture Object
                Picture pictureObj = new Picture(fileName);
                
                //Prompt user for input
                System.out.println("Enter in your option for the picture \n [1] for a fake sunset [2] for greyscale and [3] for somethingelse");

                Scanner scan = new Scanner(System.in);
                String userInput = scan.nextLine();

                System.out.println(userInput);

                switch(userInput){
                    case "1":
                        System.out.println("Fake Sunset chosen!");
                        fakeSunset(pictureObj);
                        break;
                    case "2":
                        System.out.println("Greyscale chosen!");
                        break;
                    case "3":
                        System.out.println("Option 3 chosen!");
                        break;
                }
            }      
    }
    
   
    private static void fakeSunset(Picture pictureObj){
        //Show the original picture
        pictureObj.show();
        //Wait for input to continue
        System.out.println("Hit any key to continue adding filter to picture");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        increaseColour("Red",pictureObj,3);
        pictureObj.show();
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
        //Repaint the image with new values.
        pictureObj.repaint();  
    }   
}
