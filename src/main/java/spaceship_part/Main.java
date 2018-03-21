package spaceship_part;

public class Main {
    public static void main(String[] args){
        Spaceship spaceship = new Spaceship("Golden Heart", Engines.FOTON_ENGINE.toString());
        spaceship.move();

        CrewMan crewMan = new CrewMan("Sonya", Relations.SIMPLE_COINCIDENCE.toString());
        System.out.println(crewMan.getFeeling());
    }
}
