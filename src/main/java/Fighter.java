import java.util.ArrayList;

public class Fighter {
    private ArrayList<Ability> powers;
    private String name;
    private int strength;
    private int defense;
    private int health;
    private int speed;
    private int stamina;
    private int evasiveness;
    private int intelligence;

    public Fighter(String name, int strength, int defense, int health, int speed, int stamina, int evasiveness, int intelligence){
        this.name = name;
        this.strength = strength;
        this.defense = defense;
        this.health = health;
        this.speed = speed;
        this. stamina = stamina;
        this.evasiveness = evasiveness;
        this.intelligence = intelligence;
        powers = new ArrayList<Ability>();
    }

    public void applyAbility(Ability ability){
        powers.add(ability);
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Ability> getAbility(){
        return powers;
    }

    public String getStats(){
        return "Health: " + Integer.toString(health) + "\nStrength: " + Integer.toString(strength) + "\nDefense: " + Integer.toString(defense) + "\n";
    }
}
