package ink.basal.evaluation;

import ink.basal.ApplicationState;
import ink.basal.model.Recording;

public final class DeckRecordingResolver implements RecordingResolver {

  private final ApplicationState applicationState;

  public DeckRecordingResolver(ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  @Override
  public Recording resolveRecording(String id) {
    return applicationState.currentDeck().recordingById(id);
  }
}
