package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalPair;
import ink.basal.deck.DeckRepository;

public final class DeckPairSource implements PairSource {

  private final DeckRepository deckRepository;

  public DeckPairSource(DeckRepository deckRepository) {
    this.deckRepository = deckRepository;
  }

  @Override
  public List<LexicalPair> pairs() {
    return deckRepository.current().lexicalPairs();
  }
}
