package skinDetector;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class TrainningDataCreation {
	
	   private BufferedImage image;
	   private BufferedImage mask;
	   int width;
	   int height;
	   private int skin[][][]=new int[256][256][256];
	   private int nonSkin[][][]=new int[256][256][256];
	   
	   public void rgbCalculation(String imageFile , String maskFile) {
	      try {
	    	  
	    	// System.out.println(imageFile);
	         File imageInput = new File(imageFile);
	         File maskInput  = new  File(maskFile);
	         
	         image = ImageIO.read(imageInput);
	         mask  =  ImageIO.read(maskInput);
	         
	         width = image.getWidth();
	         height = image.getHeight();
	         
	      //   int count = 0;
	         
	         for(int i=0; i<height; i++) {
	         
	            for(int j=0; j<width; j++) {
	            	
	            	Color c = new Color(mask.getRGB(j, i));	
	            	Color cImage= new Color(image.getRGB(j, i));	
	            	
	            	if(skinOrNonSkin(c)) {
	            		
	            		skin[cImage.getRed()][cImage.getGreen()][cImage.getBlue()]++;
	            	}
	            	
	            	else {
	            		nonSkin[cImage.getRed()][cImage.getGreen()][cImage.getBlue()]++;
	            	}
	            }
	         }

	      } catch (Exception e) {
	    	  
	    	  System.out.print(e.toString());
	      }
	   }
	   
	   public int[][][] getSkin() {
			// TODO Auto-generated method stub
			return skin;
		}
	   
	   public boolean skinOrNonSkin(Color cl) {
		   
		   if(cl.getBlue() >=250 && cl.getGreen()>=250 && cl.getRed()>=250) {
			   return false;
		   }
		   
		   else {
			   return true;
		   }
	   }
	   
	   public void controller() {
		   
		   File imageFolder = new File("D:\\\\Eclipse\\\\skinDetector\\\\ibtd");
		   File[] listOfImageFiles = imageFolder.listFiles();
		   
		   File maskFolder = new File("D:\\\\Eclipse\\\\skinDetector\\\\ibtd\\\\mask");
		   File[] listOfMaskFiles = maskFolder.listFiles();
		   
		/*  for (File file : listOfImageFiles) {
			    if (file.isFile()) {
			        System.out.println(file.getName());
			    }
			} 
		   
		   System.out.println(listOfMaskFiles.length);*/

		   for (int i=0 ; i<listOfMaskFiles.length ; i++) {
			   
			  // System.out.println(listOfImageFiles[i].getName());
			   rgbCalculation(listOfImageFiles[i].getAbsolutePath().toString() , listOfMaskFiles[i].getAbsolutePath().toString());
		   }
	   
	}

	public int[][][] getNonSkin() {
		// TODO Auto-generated method stub
		return nonSkin;
	}

}
