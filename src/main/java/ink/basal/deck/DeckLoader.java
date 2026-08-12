package ink.basal.deck;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface DeckLoader {

  Deck load(Path directory);
}
