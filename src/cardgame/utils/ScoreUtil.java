package cardgame.utils;

import cardgame.texasobjects.TexasHoldemGame;
import cardgame.texasobjects.TexasPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreUtil {

    public static void createScores(TexasHoldemGame game, String path) throws IOException {
        HashMap<String,Integer> playerScores = new HashMap<>();
        // read file to check if exists
        File f = new File(path);
        Path filePath = Paths.get(path);
        // if the file exists we parse the old scores with the new ones
        if(f.exists() && !f.isDirectory()) {
            List<String> lines;
            lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] splitLine = line.split("-");
                playerScores.put(splitLine[0], Integer.valueOf(splitLine[1]));
            }
            List<TexasPlayer> players = game.players;
            for (TexasPlayer player : players) {
                if (player.balance > 0) {
                    lines.add(player.name+"-"+1);
                    if (playerScores.containsKey(player.name)) {
                        playerScores.put(player.name,playerScores.get(player.name)+1);
                    } else {
                        playerScores.put(player.name,1);
                    }
                } else {
                    if (!playerScores.containsKey(player.name)) {
                        playerScores.put(player.name,0);
                    }
                }
            }
        } else {
            for (TexasPlayer player : game.players) {
                if (player.balance > 0) {
                    playerScores.put(player.name,1);
                } else {
                    playerScores.put(player.name,0);
                }
            }
        }


        List<String> newLines = new ArrayList<>();
        for (HashMap.Entry<String, Integer> entry : playerScores.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String line = key+"-"+value;
            newLines.add(line);
        }
        // Path file = Paths.get(path);
        Files.write(filePath, newLines, Charset.forName("UTF-8"));

    }

}
