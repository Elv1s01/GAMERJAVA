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
        int amount;  //Tive que declarar a variável antes.
        while (true) {
            try {
                do {
                    amount = in.nextInt();
                    in.nextLine();
                } while (!(amount == 1) && !(amount == 2) && !(amount == 3) && !(amount == 4));

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
                        case 3:
                            characterClass = new Assassin();
                            break;
                        case 4:
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
        createEnemy();
        boolean running = true; 
        System.out.println("\n>The Game Start-----------------");
        int turn = 1;

        while(running){
            System.out.printf("\n\n\nTURNO -----> %d\n\n", turn);
            turn++;
            //Intenção dos Inimigos - Dessa forma fica possível se defender de maneira efetiva.
            for (EnemyClass enemyClass : enemyRepository.getList()) {
                enemyClass.intetion(playerRepository.getList(), false);
            }
            //Turno dos jogadores

            showStatus();
            System.out.println(">\nTurno dos jogadores!");
            for (Player player : playerRepository.getList()) {
                System.out.printf("\n>Vai %s! O que você faz?!", player.getName());
                String choice; //O proxímo bloco garante que será digitado uma opção válida.
                do{
                    System.out.println("\n1 - Atacar\n2 - Defender\n3 - Pular Turno");
                    choice = in.nextLine();

                    if (choice.equals("1")) { //Aqui está implementado o possǘel atk do player.
                        do{
                            System.out.println(">Qual monstro?\n");
                            enemyRepository.showEnemy();
                            choice = in.nextLine();
                        }while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5"));
                        EnemyClass enemy = enemyRepository.getList().get(Integer.parseInt(choice)-1);
                        player.atk(enemy);
                        System.out.printf(">%s atacou %s, causando %d de dano.", player.getName(), enemy.getNameClass(),  player.getCharacterClass().getAtk());
                        showStatus();
                        System.out.printf(">\nFim do turno de %s", player.getName());
                        running = loser();

                    }else if(choice.equals("2")){ //Aqui está implementado a possível defesa do player.
                        player.def();
                        System.out.printf("\n>%s se defendeu, diminuindo o dano em %d porcento!", player.getName(), player.getCharacterClass().getDef());
                        System.out.println(">Fim do turno!");
                        showStatus();
                        running = loser();

                    }else if(choice.equals("3")){ // Aqui o jogador pula o turno, por algum motivo.
                        System.out.printf(">%s pulou o turno!", player.getName());
                        showStatus();
                        running = loser();
                    }else{
                        System.out.printf(">%s, deixa de ser burro e escolhe uma opção válida. Nam!", player.getName());
                    }

                }while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
            }
            //Turno dos Inimigos
            for (EnemyClass enemyClass : enemyRepository.getList()) {
                System.out.printf(">%s atacou, cuidado kkkk, tu morre.", enemyClass.getNameClass());
                enemyClass.intetion(playerRepository.getList(), true);
                running = loser();
            }
            System.out.println(">Fim do turno dos inimigos\n\n");
        }
    } 

//Criar método estático para tentar resolver um problema.
    public void showStatus(){
        for (Player player : playerRepository.getList()) {
            player.status();
        }
        for (EnemyClass enemyClass : enemyRepository.getList()) {
            enemyClass.status();
        }
    }
    public boolean loser(){
        //Método resposável em parar a lista.
        enemyRepository.getList().removeIf(enemy -> enemy.getHp() <= 0);
        playerRepository.getList().removeIf(player -> player.getCharacterClass().getHp() <= 0);
        System.out.println("\n\nJogadores e monstros podem ter morridos...\n");
        if (enemyRepository.getList().isEmpty()) {
            System.out.println(">TODOS OS INIMIGOS DERROTADOS!!PARABÉNS CAMPEÕES!");
            return false;
        }else if(playerRepository.getList().isEmpty()){
            System.out.println("\n\n\n----------GAMER OVER-----------\n\n\\n");
            return false;
        }else{
            return true;
        }
    }

//Fim. Essa Peste foi trabalhosa viu...  
}
