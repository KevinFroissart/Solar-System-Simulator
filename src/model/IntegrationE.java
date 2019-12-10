package model;

public interface IntegrationE {

    public static void eulerExplicite (Objet objA, Objet objB, Systeme sys){
        double xA = objA.getPos().getPosX();
        double yA = objA.getPos().getPosY();
        double xB = objB.getPos().getPosX();
        double yB = objB.getPos().getPosY();
        double distance = Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
        double force = sys.getG()* (objA.getMasse() * objB.getMasse()) / Math.pow(distance, 2);
        double acc = force / objA.getMasse();
        double dirX = (xB - xA) / distance;
        double dirY = (yB - yA) / distance;
        double vitX = objA.getVitesse().getPosX() + dirX * acc;
        double vitY = objA.getVitesse().getPosY() + dirY * acc;
        objA.setVit(new Vecteur(vitX, vitY));
    }

    public static void eulerImplicite(Objet objA, Objet objB, Systeme sys){
        //TODO
    }

    public static void leapfrog(Objet objA, Objet objB, Systeme sys){
        //TODO
    }

    public static void RK4(Objet objA, Objet objB, Systeme sys){
        //TODO
    }

}
