package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalPair;
import ink.basal.ApplicationState;

public final class DeckPairSource implements PairSource {

  private final ApplicationState applicationState;

  public DeckPairSource(ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  @Override
  public List<LexicalPair> pairs() {
    return applicationState.currentDeck().lexicalPairs();
  }
}
