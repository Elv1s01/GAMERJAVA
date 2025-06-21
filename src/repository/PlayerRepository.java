package repository;
import java.util.List;
import java.util.ArrayList;
import player.Player;

public class PlayerRepository {
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player){
        players.add(player);
        System.out.printf(">%s foi adicionado!", player.getName());
    }

    public void removePlayer(Player player){
        for (Player gamers : players) {
            if (gamers == player) {
                players.remove(player);
                
            } 
        }
    }

    public void showPlayer(){
        int i = 1;
        for (Player player : players) {
            System.out.printf("%d - %s, %s\n", i, player.getName(), player.getClass());
            i++;
        }
    }

    public List<Player> getList(){
        return players;
    }
    


}
