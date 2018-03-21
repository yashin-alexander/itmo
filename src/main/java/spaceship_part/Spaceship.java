package spaceship_part;

import java.util.List;


public class Spaceship{
    private String name;
    private String engine;
    private List<CrewMan> crew;

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

    void move(){
        System.out.println("SpaceShip is now moves with the power of " + this.getEngine());
    }
}
