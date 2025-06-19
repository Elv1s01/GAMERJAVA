package service;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.characterClass.Assassin;
import classes.characterClass.Barbarian;
import classes.characterClass.Warrior;
import classes.characterClass.Wizard;
import classes.characterClass.baseCharacter.*;
import player.Player;
import repository.PlayerRepository;

public class MenuService {
    private PlayerRepository playerRepository = new PlayerRepository();

    Scanner in = new Scanner(System.in);

    //Método new game -> Resposável po identificar um novo jogo.
    public void newGame(){
        while(true){ //Permitir continuar o loop até receber uma entrada válida.
            try {
                int choice = in.nextInt();
                in.nextLine();

                if (choice == 1) {
                    createPlayer();;

                    
                }else{

                }



                
            } catch (InputMismatchException e) {
                System.out.println(">Número não se encntra entre as opções.");
                System.out.println(">Digite um número válido.");
                continue;

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
                    System.out.printf(">Jogador %d, informe seu nome:", i);
                    String name = in.nextLine();
                    System.out.printf(">Bem vindo %s, qual classe você deseja?", name);
                    System.out.println("1 - Warrior.\n2 - Barbarian.\n3 - Assassin.\n4 - Wizard.");
                    int choice = in.nextInt();
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
                
            } catch (InputMismatchException e) {
                System.out.println(">Digite uma opção válida, por favor.");
                continue;
            }
            
        }



    }

    



    
}
