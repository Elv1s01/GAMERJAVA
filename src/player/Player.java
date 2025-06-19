package player;

import classes.characterClass.baseCharacter.CharacterClass;

public class Player {
    private String name;
    private CharacterClass characterClass;

    public Player(String name, CharacterClass characterClass){
        this.name = name;
        this.characterClass = characterClass;
    }


    //GETTERS AND SETTERS
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
