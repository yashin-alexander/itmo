package spaceship_part;

public class Atom {
    private Relation moleculeRelation = new StrangePhysicalPrincipleRelation();

    public Relation getMoleculeRelation(){
        return this.moleculeRelation;
    }
}
