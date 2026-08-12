package ink.basal.deck;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocumentDeckRepository implements DeckRepository {

  // TODO: Make this crossplatform
  // TODO: Use shared configuration interfaces instead of adding unrelated
  // boilerplate here
  private static final Path BASAL_DIRECTORY = Path.of(System.getenv("HOME"), ".basal");

  private final List<Deck> decks;

  private Deck currentDeck = null;
  private Path currentPath;

  public DocumentDeckRepository(List<Deck> decks) {
    this.decks = decks;
  }

  public static DocumentDeckRepository create(DeckLoader deckLoader) {
    List<Deck> decks = new ArrayList<>();
    for (File subdirectory : BASAL_DIRECTORY.toFile().listFiles(File::isDirectory)) {
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

  public Deck current() {
    return currentDeck;
  }

  public DeckRepository load(Deck deck) {
    currentDeck = deck;
    currentPath = BASAL_DIRECTORY.resolve(deck.name());
    return this;
  }

  public Path currentPath() {
    return currentPath;
  }
}
