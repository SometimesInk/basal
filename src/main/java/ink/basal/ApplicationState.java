package ink.basal;

import java.nio.file.Path;

import ink.basal.config.ConfigPaths;
import ink.basal.deck.Deck;

public final class ApplicationState {

  private Deck currentDeck;
  private Path currentDeckPath;

  public Deck currentDeck() {
    return currentDeck;
  }

  public Path currentDeckPath() {
    return currentDeckPath;
  }

  public ApplicationState load(Deck deck) {
    currentDeck = deck;
    currentDeckPath = ConfigPaths.dataDirectory().resolve(deck.name());
    return this;
  }
}
