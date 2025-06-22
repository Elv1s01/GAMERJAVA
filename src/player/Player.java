package player;

import classes.characterClass.baseCharacter.CharacterClass;
import classes.enemyClass.baseEnemy.EnemyClass;

public class Player {
    private String name;
    private CharacterClass characterClass;

    public Player(String name, CharacterClass characterClass){
        this.name = name;
        this.characterClass = characterClass;
    }

    public void atk(EnemyClass enemyClass){
        if (getCharacterClass().getStatus() == false) {
            getCharacterClass().setStatus(true);
        }
        getCharacterClass().atk(enemyClass);
    }

    public void def(){
        getCharacterClass().def();
    }

    public void status(){
        System.out.printf("\n%s - vida:%d defesa:%d% ataque:%d mana:%d\n",name, characterClass.getHp(), characterClass.getDef(), characterClass.getAtk(), characterClass.getMana());
    }


    //GETTERS AND SETTER
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    
}
