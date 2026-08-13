package ink.basal.evaluation;

import java.util.List;

import ink.basal.ApplicationState;
import ink.basal.model.LexicalItem;
import ink.basal.model.Recording;

public final class DeckRecordingSource implements RecordingSource {

  private final ApplicationState applicationState;

  public DeckRecordingSource(ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  @Override
  public List<Recording> recordingsFor(LexicalItem item) {
    return applicationState.currentDeck().recordingsFor(item.id());
  }
}
