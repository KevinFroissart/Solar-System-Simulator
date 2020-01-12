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
        
        if(objA.getType().equals("Vaisseau")) {
        	if(objB.getType().equals("Fixe")) objA.setAttractionSoleil((sys.getG() * objA.getMasse() * objB.getMasse()) / Math.pow(distance, 2));
        	if(objB.getType().equals("Simulé")) objA.setAttractionPlanete((sys.getG() * objA.getMasse() * objB.getMasse()) / Math.pow(distance, 2));
        	objA.setAttraction((objA.getAttractionPlanete() + objA.getAttractionSoleil()) / 2);
        }
		
        double dirX = (xB - xA) / distance;
        double dirY = (yB - yA) / distance;
        double vitX = objA.getVitesse().getPosX() + dirX * acc;
        double vitY = objA.getVitesse().getPosY() + dirY * acc;
        objA.setVit(new Vecteur(vitX, vitY));

    }

    public static void leapfrog(Objet objA, Objet objB, Systeme sys){
        double xA = objA.getPos().getPosX();
        double yA = objA.getPos().getPosY();
        double xB = objB.getPos().getPosX();
        double yB = objB.getPos().getPosY();
        double distance = Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
        double force = sys.getG()* (objA.getMasse() * objB.getMasse()) / Math.pow(distance, 2);
        Vecteur AB = new Vecteur(objA.getPos(), objB.getPos());
        double norme = AB.getNorme();
        Vecteur acc = new Vecteur(AB.getPosX() / norme * force, AB.getPosY() / norme * force);

        Vecteur demiVit = new Vecteur(
                objA.getVitesse().getPosX() + (sys.getDt() * acc.getPosX() / 2),
                objA.getVitesse().getPosY() + (sys.getDt() * acc.getPosY() / 2)
        );

        objA.setPos(new Vecteur(objA.getPos().getPosX() + sys.getDt() * demiVit.getPosX(), objA.getPos().getPosY() + sys.getDt() * demiVit.getPosY()));
        objA.setVit(new Vecteur(objA.getPos().getPosX() + sys.getDt() * acc.getPosX(), objA.getPos().getPosY() + sys.getDt() * acc.getPosY()));
    }

    public static void RK4(Objet objA, Objet objB, Systeme sys){
        //TODO
    }

}
