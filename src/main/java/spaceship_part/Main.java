package spaceship_part;


public class Main {
    public static void main(String[] args){
        Spaceship spaceship = new Spaceship("Golden Heart", Engines.FOTON_ENGINE.toString());
        spaceship.move();

        CrewMan crewMan = new CrewMan("Sonya", new StrangePhysicalPrincipleRelation(), spaceship);
        System.out.println("Sonya is feeling " + crewMan.getFeeling());

        CrewMan crewMan1 = new CrewMan("Sasha", new OwnWillRelation(), spaceship);
        System.out.println("Sasha is feeling " + crewMan1.getFeeling());

        System.out.println(spaceship.getCrew());
    }
}
