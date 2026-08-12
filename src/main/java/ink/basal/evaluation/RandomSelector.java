package ink.basal.evaluation;

import java.util.List;
import java.util.Random;

import ink.basal.model.LexicalPair;
import ink.basal.model.LexicalItem;
import ink.basal.model.Recording;

/**
 * RandomSelector
 */
public class RandomSelector implements EvaluationSelector {
  private static final Random random = new Random();

  @Override
  public LexicalPair choosePair(List<LexicalPair> pairs) {
    return pairs.get(random.nextInt(pairs.size()));
  }

  @Override
  public LexicalItem chooseItem(List<LexicalItem> items) {
    return items.get(random.nextInt(items.size()));
  }

  @Override
  public Recording chooseRecording(List<Recording> recordings) {
    return recordings.get(random.nextInt(recordings.size()));
  }

}
