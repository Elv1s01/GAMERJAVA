package repository;
import java.util.List;

import classes.enemyClass.baseEnemy.*;

import java.util.ArrayList;

public class EnemyRepository {
    private List<EnemyClass> enemys = new ArrayList<>();

    public void addEnemy(EnemyClass enemyClass){
        enemys.add(enemyClass);
    }
    public void removeEnemy(EnemyClass enemyClass){
        enemys.remove(enemyClass);
    }
    public void showEnemy(){
        int i = 1;
        for (EnemyClass enemyClass : enemys) {
            System.out.printf("\n%d - %s", enemyClass.get);
            
        }
    }
    
}
