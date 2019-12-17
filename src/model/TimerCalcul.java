package model;

import controller.AffichageControl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCalcul extends TimerTask {

    Timer timer = new Timer(true);
    List<Objet> listeObjet;
    SystemLoader sl;
    Systeme sys;
    AffichageControl ac;

    public TimerCalcul(AffichageControl ac) {
        this.ac = ac;
        sl = ac.getModel();
        listeObjet = sl.objectInit();
        sys = ac.getSysteme();
    }

    @Override
    public void run() {
        for(Objet o : listeObjet) {
            for(Objet o2 : listeObjet) {
                if(o.getType().matches("Simulé") && o2.getType().equals("Fixe")) {
                    IntegrationE.eulerExplicite(o, o2, sys);
                }
                if(o.getType().equals("Vaisseau") && !o2.getType().equals("Vaisseau")) {
                    IntegrationE.eulerExplicite(o, o2, sys);
                }
            }
            ac.pos(o);
        }

    }

    public void start() {
        timer.scheduleAtFixedRate(this, 0, (long) ((sys.getDt()/sys.getFa())*1000));
    }
}
