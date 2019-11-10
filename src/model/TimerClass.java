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
	
    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        completeTask();
    }

    private void completeTask() {
        while(true) {
		//System.out.println(new Date());
		dateFin = new Date (System.currentTimeMillis());
        Date duree = new Date (System.currentTimeMillis()); //Pour calculer la différence
		duree.setTime (dateFin.getTime () - uDate.getTime ());  //Calcul de la différence
    	long secondes = duree.getTime () / 1000;
    	long min = secondes / 60;
    	long heures = min / 60;
    	long mili = duree.getTime () % 1000;
    	secondes %= 60;
    	System.out.println (heures + ":" + min + ":" + secondes + ":" + mili);
		}
    }
    
    public static void main(String args[]){
        TimerTask timerTask = new TimerClass();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 25);
        System.out.println("TimerTask started");
        try {
        	uDate = new Date (System.currentTimeMillis ());
        	while(true)
            Thread.sleep((long) (dt/fa));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    }
}