import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuperFight{
    private ArrayList<Fighter> fighterDeck;
    private ArrayList<Ability> abilityDeck;
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader( System.in ));
    private static ArrayList<Player> players;

    /**
     * Constructor for the game.  Loads the basic deck.
     * @throws IOException
     * @throws ParseException
     */

    public SuperFight() throws IOException, ParseException {
        loadDeck("src/main/json/baseDeck.json");
    }

    /**
     * Loads the basic deck from a JSON file.  File includes characters and abilities.
     *
     * @param filename - directory of the JSON file to load cards for the game.
     * @throws IOException
     * @throws ParseException
     */
    private void loadDeck(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
        JSONObject json = (JSONObject) obj;

        loadFighters((JSONArray) json.get("fighters"));
        loadAbilities((JSONArray) json.get("abilities"));
    }

    /**
     * Helper method to loadDeck().  Logic to load just the fighters.
     * @param fighters - JSONArray of fighters from a JSON 'deck.'
     */
    private void loadFighters(JSONArray fighters){
        fighterDeck = new ArrayList<Fighter>();

        for(int i =0; i<fighters.size();i++){
            JSONObject fighter = (JSONObject) fighters.get(i);
            String name = (String)fighter.get("name");
            int strength = ((Long)fighter.get("strength")).intValue();
            int defense = ((Long)fighter.get("defense")).intValue();
            int health = ((Long)fighter.get("health")).intValue();
            int speed = ((Long)fighter.get("speed")).intValue();
            int evasiveness = ((Long)fighter.get("evasiveness")).intValue();
            int stamina = ((Long)fighter.get("stamina")).intValue();
            int intelligence = ((Long)fighter.get("intelligence")).intValue();

            fighterDeck.add(new Fighter(name,strength,defense,health,speed,stamina,evasiveness,intelligence));
        }
    }
    /**
     * Helper method to loadDeck().  Logic to load just the abilities.
     * @param abilities - JSONArray of abilities from a JSON 'deck.'
     */
    private void loadAbilities(JSONArray abilities){
        abilityDeck = new ArrayList<Ability>();

        for(int i=0; i<abilities.size();i++){
            JSONObject ability = (JSONObject) abilities.get(i);
            abilityDeck.add(new Ability((String)ability.get("ability")));
        }
    }

    /**
     * Takes 3 cards from the fighter deck and 3 cards from the ability deck and distributes them to the designated player.
     *
     * @param player - Player object of one of the players in the game.
     */
    private void deal(Player player){
        int count = 3;
        while(count>0) {
            int randAbility = (int) (Math.random() * abilityDeck.size());
            int randFighter = (int) (Math.random() * fighterDeck.size());
            player.addCard(abilityDeck.remove(randAbility));
            player.addCard(fighterDeck.remove(randFighter));
            count--;
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        SuperFight newGame = new SuperFight();
//        System.out.println("How many players are playing?");
        int numPlayers = 2;

        players = new ArrayList<Player>();

        for(int i=0; i<numPlayers;i++){
            players.add(new Player("Player" + Integer.toString(i+1)));
        }

        System.out.println("Welcome, " + players.get(0).getName());

        Player player1 = players.get(0);
        Player bot = players.get(1);

        newGame.deal(player1);
        newGame.deal(bot);

        System.out.println("These are your cards:");
        for(Fighter x : player1.getFighterCards()) System.out.println(x.getName());
        for(Ability x : player1.getAbilityCards()) System.out.println(x.getAbility());

        System.out.println("Player1, choose a fighter.");
        int fighterIndex = Integer.parseInt(stdin.readLine());
        System.out.println("Player1, choose an ability.");
        int abilityIndex = Integer.parseInt(stdin.readLine());

        player1.setFighter(player1.getFighterCard(fighterIndex),player1.getAbilityCard(abilityIndex));
        bot.setFighter(bot.getFighterCard((int)Math.random()*3),bot.getAbilityCard((int)Math.random()*3));

        System.out.println(player1.getHero().getName() + ":" + player1.getHero().getAbility().get(0).getAbility() + " VS " + bot.getHero().getName() + ":" + bot.getHero().getAbility().get(0).getAbility());
        System.out.print(player1.getHero().getStats());

    }
}
