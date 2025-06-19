package classes.characterClass.baseCharacter;

import classes.enemyClass.baseEnemy.EnemyClass;

public abstract class CharacterClass {
    private  String nameClass;
    private  int hp, def, atk, mana;
    private boolean status = true;
    
    public CharacterClass(String nameClass, int hp, int def, int atk, int mana) {
        this.nameClass = nameClass;
        this.hp = hp;
        this.def = def;
        this.atk = atk;
        this.mana = mana;
    }


    //MÉTODO PARA ATACAR OS INIMIGOS.
    public void atk(EnemyClass enemyClass){
        status = true;
        enemyClass.setHp(enemyClass.getHp()-getAtk());
    }

    //MÉTODO PARA DEFENDER --> se defender diminui o dano pela metade.
    public void def(){
        setStatus(false);
    }




    

    //getters and setters
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

    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
    
    

    
}
