import java.util.ArrayList;

public class Player {
    private ArrayList<Ability> abilityCards;
    private ArrayList<Fighter> fighterCards;
    private Fighter fighter;
    private String name;

    public Player(String name){
        abilityCards = new ArrayList<Ability>();
        fighterCards = new ArrayList<Fighter>();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addCard(Ability ability){
        abilityCards.add(ability);
    }

    public void addCard(Fighter fighter){
        fighterCards.add(fighter);
    }

    public ArrayList<Fighter> getFighterCards(){
        return fighterCards;
    }

    public ArrayList<Ability> getAbilityCards(){
        return abilityCards;
    }

    public Ability getAbilityCard(int index){
        return abilityCards.get(index);
    }

    public Fighter getFighterCard(int index){
        return fighterCards.get(index);
    }

    public Fighter getHero(){
        return this.fighter;
    }

    public void setFighter(Fighter fighter, Ability ability){

        this.fighter = fighter;
        this.fighter.applyAbility(ability);
        fighterCards.remove(fighter);
        abilityCards.remove(ability);
    }
}
