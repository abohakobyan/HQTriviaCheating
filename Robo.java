package Core;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	
public class Robo {
	public static int H;
	public static int W;
	public static int Hl;
	public static int Wl;
		public static void TakeScreenShot(int hl, int wl, int h, int w, String file) {
			H = h;
			W = w;
			Hl = hl;
			Wl = wl;
			Robot robot = null;
			 try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			BufferedImage i = null;
			i = robot.createScreenCapture(new Rectangle(Hl,Wl,H,W));
	
			int randomNum = ThreadLocalRandom.current().nextInt(0, 2147483645 + 1);
			String IMG = String.valueOf(randomNum);
			File History = new File("D:\\testfiles\\"+IMG+".png");
			File image = new File(file);
			try {
				ImageIO.write(i, "png", image);
				ImageIO.write(i, "png", History);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		
}
