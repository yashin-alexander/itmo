package spaceship_part;


public class CrewMan{
    private String name;
    private String feeling;
    private String relation;
    private Spaceship spaceship;

    public CrewMan(String name, String relation){
        for(Relations possible_relation : Relations.values()){
            if (possible_relation.toString().equals(relation)){
                this.name = name;
                this.relation = relation;
                if (relation.equals(Relations.STRANGE_PHYSICAL_PRINCIPLE.toString()))
                    this.feeling = Feelings.UNCOMFORTABLY.toString();
                else
                    this.feeling = Feelings.GOOD.toString();
                return;
            }
        }

        throw new IllegalArgumentException("Incorrect relation type: '" + relation + "'");
    }

    public String getFeeling() {
        return feeling;
    }
}
