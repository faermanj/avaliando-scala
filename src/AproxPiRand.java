import java.util.Random;

public class AproxPiRand {
	public static void main(String[] args) throws Exception {
		Random rand = new Random();
		double cnt =0;
		double in = 0;
		double pi = 0;
		while(true){
			double x = ((rand.nextDouble()*2)-1);
			double y = ((rand.nextDouble()*2)-1);
			double dist = Math.sqrt(x*x + y*y);
			if(dist < 1.0) in++;
			cnt++;
			pi = 4.0 * (in/cnt);
			System.out.println("Pi is now: "+pi);

		}
	}
}