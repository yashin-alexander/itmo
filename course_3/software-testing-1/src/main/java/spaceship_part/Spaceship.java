package spaceship_part;

import java.util.ArrayList;


public class Spaceship{
    private String name;
    private String engine;
    private ArrayList<CrewMan> crew = new ArrayList<CrewMan>();

    Spaceship(String name, String engine){
        for(Engines possible_engine : Engines.values()){

            if (possible_engine.toString().equals(engine)){
                this.name = name;
                this.engine = engine;
                return;
            }
        }

        throw new IllegalArgumentException("Incorrect engine type: '" + engine + "'");
    }

    public String getEngine(){
        return this.engine;
    }

    void assignNewCrewMan(CrewMan crewMan){
        this.crew.add(crewMan);
    }

    public ArrayList<CrewMan> getCrew(){
        return this.crew;
    }

    void move(){
        System.out.println("SpaceShip is now moves with the power of " + this.getEngine());
    }
}
