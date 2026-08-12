package ink.basal.evaluation;

import ink.basal.deck.DeckRepository;
import ink.basal.model.Recording;

public final class DeckRecordingResolver implements RecordingResolver {

  private final DeckRepository deckRepository;

  public DeckRecordingResolver(DeckRepository deckRepository) {
    this.deckRepository = deckRepository;
  }

  @Override
  public Recording resolveRecording(String id) {
    return deckRepository.current().recordingById(id);
  }
}
