package service;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.characterClass.Assassin;
import classes.characterClass.Barbarian;
import classes.characterClass.Warrior;
import classes.characterClass.Wizard;
import classes.characterClass.baseCharacter.*;
import classes.enemyClass.Golem;
import classes.enemyClass.Skeleton;
import classes.enemyClass.Slime;
import classes.enemyClass.Witch;
import classes.enemyClass.baseEnemy.EnemyClass;
import player.Player;
import repository.EnemyRepository;
import repository.PlayerRepository;
import java.util.Random;

public class MenuService {
    private PlayerRepository playerRepository = new PlayerRepository();
    private EnemyRepository enemyRepository = new EnemyRepository();

    Scanner in = new Scanner(System.in);
    Random random = new Random();

    //Método new game -> Resposável po identificar um novo jogo.
    public void newGame(){
        while(true){ //Permitir continuar o loop até receber uma entrada válida.
            try {
                int choice = in.nextInt();
                in.nextLine();

                if (choice == 1) {
                    createPlayer();
                    break;

                    
                }else{

                }



                
            } catch (InputMismatchException e) {
                System.out.println(">Número não se encntra entre as opções.");
                System.out.println(">Digite um número válido.");
                in.nextLine();
            }
        }
    }
    public void createPlayer(){
        System.out.println(">Quantos jogadores vão participar?");
        System.out.println("> 1\n> 2\n> 3\n> 4");
        while (true) {
            try {
                int amount = in.nextInt();
                in.nextLine();
                for(int i = 1; i <= amount; i++){
                    System.out.printf("\n>Jogador %d, informe seu nome:", i);
                    String name = in.nextLine();
                    System.out.printf(">Bem vindo %s, qual classe você deseja?", name);
                    System.out.println("\n1 - Warrior.\n2 - Barbarian.\n3 - Assassin.\n4 - Wizard.");
                    int choice = in.nextInt();  //Possível exceçao futuramente, caso o usuário digite uma string.
                    in.nextLine();
                    CharacterClass characterClass;
                    
                    switch (choice) {
                        case 1:
                            characterClass = new Warrior();
                            break;
                        case 2:
                            characterClass = new Barbarian();
                            break;
                        case 4:
                            characterClass = new Assassin();
                            break;
                        case 5:
                            characterClass = new Wizard();
                            break;
                        default:
                            System.out.println(">Como você digitou uma opção inválida ficará com classe padrão. WARRIOR.");
                            characterClass = new Warrior();
                            break;
                    }
                    playerRepository.addPlayer(new Player(name, characterClass));
                }
                break;  //Quando sair do for() eu paro o loop while()
                
            } catch (InputMismatchException e) {
                System.out.println(">Digite uma opção válida, por favor.");
                in.nextLine(); //Proceso de limpar o buffer, ou se não o loop se repete infinitamente.
                //continue - provavelmente é algo desnecessário.
            }  
        }
    }
    public void createEnemy(){
        int amount = random.nextInt(5) + 1; //Recebe um valor aleátorio de 1 a 5.
        for(int i = 1; i <= amount; i++){
            int enemy = random.nextInt(4) + 1; //Claase do inimigo gerda automaticamente.
            //Cada loop no for a lsita de inimigos deve receber um inimigo aleátorio.
            switch (enemy) {
                case 1:
                    enemyRepository.addEnemy(new Skeleton());
                    break;
                case 2:
                    enemyRepository.addEnemy(new Slime());
                    break;
                case 3:
                    enemyRepository.addEnemy(new Golem());
                    break;
                case 4:
                    enemyRepository.addEnemy(new Witch());
                    break;
            }
        }
    }
    public void startGame(){
        boolean running = true; 

        while(running){
            //Mostra os player com seus status atuais.
            for (Player player : playerRepository.getList()) {
                player.status();
            }
            //Mostra os monstros e seus status
            for (EnemyClass enemyClass : enemyRepository.getList()) {
                enemyClass.status();
            }


        }
    } 



    
}
