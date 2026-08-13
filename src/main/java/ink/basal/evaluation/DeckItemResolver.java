package ink.basal.evaluation;

import ink.basal.ApplicationState;
import ink.basal.model.LexicalItem;

public final class DeckItemResolver implements ItemResolver {

  private final ApplicationState applicationState;

  public DeckItemResolver(ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  @Override
  public LexicalItem resolveItem(String id) {
    return applicationState.currentDeck().itemById(id);
  }
}
