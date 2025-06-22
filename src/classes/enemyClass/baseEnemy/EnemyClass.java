package classes.enemyClass.baseEnemy;
import java.util.List;
import player.Player;
import java.util.Random;
import classes.characterClass.baseCharacter.CharacterClass;

public abstract class EnemyClass {
    private String nameClass;
    private Player intetion[] = new Player[5];
    private int hp, def, atk, mana;

    public EnemyClass(String nameClass, int hp, int def, int atk, int mana) {
        this.nameClass = nameClass;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.mana = mana;
    }
    
    public void intetion(List<Player> players, boolean action){
        if (action) {
            for (Player player : players) {
                if (player == intetion[0]) {
                    atk(player.getCharacterClass());
                    intetion[0] = null;
                }else{
                    System.out.printf(">Como esse jogador morreu %s pulou o turno.", getNameClass());
                }
            }
        }else{
            Random random = new Random();
            intetion[0] = players.get(random.nextInt(players.size()));
            System.out.printf("\n>O %s vai atacar o jogador %s, causando %d de damo!", getNameClass(), intetion[0].getName(), getAtk());
            
        }
    }

    public void status(){
        System.out.printf("\n%s - vida:%d defesa:%d atk:%d mana:%d\n", getNameClass(), getHp(), getDef(), getAtk(), getMana());
    }

    //MÉTODO PARA ATACAR DO INIMIGO. --> se vc se defende recebe um terço do dano.
    public void atk(CharacterClass characterClass){
        if (characterClass.getStatus()) {
            characterClass.setHp(characterClass.getHp()-getAtk());
        }else{
            characterClass.setHp(characterClass.getHp()-getAtk()*(100 - characterClass.getDef())/100);
        }
    }

    //gtters and setters
    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    
    public Player[] getIntetion(){
        return intetion;
    }

    //Método add para a array itetion, deixando mais flexível. - Futuramente implemento.
    /*public void add(int i, List<Player> players){
        
        Random random = new Random();
        intetion[0] = players.get(random.nextInt(players.size()));
        System.out.println("");

    }*/
}
