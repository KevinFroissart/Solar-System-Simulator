package model;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass extends TimerTask {

	protected int temps;
	protected static double dt = 25;
	protected static double fa = 1;
	static Date uDate;
	Date dateFin;
	Date duree;
	long seconde;

	@Override
	public void run() {
		System.out.println("Timer task started at:"+new Date());
		while(true) {
			try {
				Thread.sleep((long) (dt/fa));
				completeTask();
				//position
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void completeTask() {
		//System.out.println(new Date());
		dateFin = new Date (System.currentTimeMillis());
		Date duree = new Date (System.currentTimeMillis()); //Pour calculer la différence
		duree.setTime (dateFin.getTime () - uDate.getTime());  //Calcul de la différence
		seconde++;
		long min = seconde / 60;
		long heures = min / 60;
		long secondes = seconde%60;

		System.out.println (heures + ":" + min + ":" + secondes);
	}

	public void start(){
		TimerTask timerTask = new TimerClass();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 1);
		System.out.println("TimerTask started");
		try {
			uDate = new Date (System.currentTimeMillis ());
			while(true)
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*public static void main(String args[]){
		TimerTask timerTask = new TimerClass();
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 1);
		System.out.println("TimerTask started");
		try {
			uDate = new Date (System.currentTimeMillis ());
			while(true)
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}*/
}