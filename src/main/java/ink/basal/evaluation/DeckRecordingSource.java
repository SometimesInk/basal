package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalItem;
import ink.basal.model.Recording;
import ink.basal.deck.DeckRepository;

public final class DeckRecordingSource implements RecordingSource {

  private final DeckRepository deckRepository;

  public DeckRecordingSource(DeckRepository deckRepository) {
    this.deckRepository = deckRepository;
  }

  @Override
  public List<Recording> recordingsFor(LexicalItem item) {
    return deckRepository.current().recordingsFor(item.id());
  }
}
