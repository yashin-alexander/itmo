package spaceship_part;


public class CrewMan{
    private String name;
    private String feeling;
    private Relation relation;
    private Spaceship spaceship;

    public CrewMan(String name, Relation relation, Spaceship spaceship){
        this.name = name;
        this.relation = relation;

        Relation atoma_relation = new Atom().getMoleculeRelation();

        if (relation.getDescription().equals(atoma_relation.getDescription()))
            this.feeling = Feelings.UNCOMFORTABLY.toString();
        else
            this.feeling = Feelings.GOOD.toString();

        this.spaceship = spaceship;
        spaceship.assignNewCrewMan(this);
    }

    public String getFeeling() {
        return feeling;
    }

    public Spaceship getSpaceship(){
        return spaceship;
    }
}
