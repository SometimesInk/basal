package ink.basal.deck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import com.google.gson.Gson;

public class JsonDeckLoader implements DeckLoader {

  public Deck load(Path directory) {
    Gson gson = new Gson();

    try (BufferedReader reader = new BufferedReader(new FileReader(directory.resolve("deck.json").toFile()))) {
      return gson.fromJson(reader.readAllAsString(), Deck.class);

    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

}
