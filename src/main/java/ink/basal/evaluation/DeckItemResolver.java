package ink.basal.evaluation;

import ink.basal.deck.DeckRepository;
import ink.basal.model.LexicalItem;

public final class DeckItemResolver implements ItemResolver {

  private final DeckRepository deckRepository;

  public DeckItemResolver(DeckRepository deckRepository) {
    this.deckRepository = deckRepository;
  }

  @Override
  public LexicalItem resolveItem(String id) {
    return deckRepository.current().itemById(id);
  }
}
