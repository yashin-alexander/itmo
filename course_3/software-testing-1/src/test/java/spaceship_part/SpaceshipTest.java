package spaceship_part;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class SpaceshipTest {
    private final static String SPACESHIP_NAME = "Golden Heart";

    private final static String COAL_ENGINE = Engines.COAL_ENGINE.toString();
    private final static String FOTON_ENGINE = Engines.FOTON_ENGINE.toString();

    private final static String GOOD_FEELING = Feelings.GOOD.toString();
    private final static String UNCOMFORTABLE_FEELING = Feelings.UNCOMFORTABLY.toString();

    private final static Relation OWN_WILL = new OwnWillRelation();
    private final static Relation SIMPLE_COINCIDENCE = new SimpleCoincidenceRelation();
    private final static Relation STRANGE_PHYSICAL_PRINCIPLE = new StrangePhysicalPrincipleRelation();

    private Spaceship fotonSpaceship;
    private Spaceship coalSpaceship;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        coalSpaceship = new Spaceship(SPACESHIP_NAME, COAL_ENGINE);
        fotonSpaceship = new Spaceship(SPACESHIP_NAME, FOTON_ENGINE);
    }

    @After
    public void destroy(){
        System.setOut(System.out);
        coalSpaceship = null;
        fotonSpaceship = null;
    }

    @Test
    public void uncomfortableFeelingsDefinitionTest(){
        CrewMan sonya = new CrewMan("Sonya", STRANGE_PHYSICAL_PRINCIPLE, fotonSpaceship);
        assertEquals(sonya.getFeeling(), (UNCOMFORTABLE_FEELING));
    }

    @Test
    public void goodFeelingsDefinitionTest(){
        CrewMan sasha = new CrewMan("Sasha", OWN_WILL, fotonSpaceship);
        assertEquals(sasha.getFeeling(), (GOOD_FEELING));
        CrewMan lolo = new CrewMan("Lolo", SIMPLE_COINCIDENCE, fotonSpaceship);
        assertEquals(lolo.getFeeling(), GOOD_FEELING);
    }

    @Test
    public void AssignedCrewTest(){
        this.assignCrewManScenario(fotonSpaceship);
        this.assignCrewManScenario(coalSpaceship);
    }

    private void assignCrewManScenario(Spaceship assignedSpaceship){
        CrewMan pepe = new CrewMan("Pepe", STRANGE_PHYSICAL_PRINCIPLE, assignedSpaceship);
        assertEquals(pepe.getSpaceship(), assignedSpaceship);
        assertEquals(pepe.getFeeling(), UNCOMFORTABLE_FEELING);

        ArrayList<CrewMan> crew = assignedSpaceship.getCrew();
        assertEquals(crew.remove(0), pepe);
        assertTrue(crew.isEmpty());
    }

    @Test
    public void assignedCrewTest(){
        final Integer CREW_SIZE = 10;
        ArrayList<CrewMan> input_crew = new ArrayList<CrewMan>();

        for (int i=CREW_SIZE; i>0; i--)
            input_crew.add(new CrewMan("Pepe", STRANGE_PHYSICAL_PRINCIPLE, fotonSpaceship));

        ArrayList<CrewMan> output_crew = fotonSpaceship.getCrew();
        assertTrue(output_crew.size() == CREW_SIZE);

        for (int i=CREW_SIZE; i>0; i--)
            assertEquals(output_crew.remove(0), input_crew.remove(0));
    }

    @Test
    public void moveViaCoalTest(){
        moveViaEngineScenario(coalSpaceship, COAL_ENGINE);
    }

    @Test
    public void  moveViaFotoneTest(){
        moveViaEngineScenario(fotonSpaceship, FOTON_ENGINE);
    }

    private void moveViaEngineScenario(Spaceship spaceship, String engineType){
        assertEquals(spaceship.getEngine(), engineType);
        spaceship.move();
        assertEquals("SpaceShip is now moves with the power of " + engineType + "\n", outContent.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalEngine(){
        new Spaceship(SPACESHIP_NAME, "WRONG ENGINE");
    }
}
