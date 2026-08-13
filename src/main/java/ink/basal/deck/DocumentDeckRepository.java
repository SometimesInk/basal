package ink.basal.deck;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ink.basal.config.ConfigPaths;
import ink.basal.config.ConfigRepository;

public class DocumentDeckRepository implements DeckRepository {

  private final List<Deck> decks;

  public DocumentDeckRepository(List<Deck> decks) {
    this.decks = decks;
  }

  public static DocumentDeckRepository create(ConfigRepository configRepository,
      DeckLoader deckLoader) {
    List<Deck> decks = new ArrayList<>();
    for (File subdirectory : ConfigPaths.dataDirectory().toFile().listFiles(File::isDirectory)) {
      decks.add(deckLoader.load(subdirectory.toPath()));
    }

    return new DocumentDeckRepository(decks);
  }

  public DeckRepository add(Deck deck) {
    decks.add(deck);
    return this;
  }

  public DeckRepository remove(Deck deck) {
    decks.remove(deck);
    return this;
  }

  public List<Deck> decks() {
    return decks;
  }

  public Deck byName(String name) {
    return decks.stream()
        .filter(deck -> deck.name().equals(name))
        .findAny()
        .orElse(null);
  }

  public Deck byId(String id) {
    return decks.stream()
        .filter(deck -> deck.id().equals(id))
        .findAny()
        .orElse(null);
  }

  public int size() {
    return decks.size();
  }
}
