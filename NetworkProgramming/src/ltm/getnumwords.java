package ltm;

import java.io.File;

public class getnumwords {
	public static void main(String[] args) {
		File sf=new File("C:\\Users\\DELL\\Desktop\\Gtnm_Design\\Figma\\booking.png");
		if(!sf.exists()){
			System.out.println("not exist");
		}else{
			System.out.println("exist");
		}
		
	}

}
