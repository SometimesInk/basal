package ink.basal.deck;

import java.util.List;

public interface DeckRepository {

  DeckRepository add(Deck deck);

  DeckRepository remove(Deck deck);

  List<Deck> decks();

  Deck byName(String name);

  Deck byId(String id);

  int size();

}
