package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalItem;
import ink.basal.model.LexicalPair;
import ink.basal.model.Question;
import ink.basal.model.Recording;

/**
 * Orchestrates question generation and lexical pair selection.
 */
public final class EvaluationEngine {

  private final ItemResolver itemResolver;
  private final PairSource pairSource;
  private final RecordingSource recordingSource;

  private final EvaluationStrategy evaluationStrategy;

  public EvaluationEngine(ItemResolver itemResolver,
      PairSource pairSource,
      RecordingSource recordingSource,
      EvaluationStrategy evaluationStrategy) {
    this.itemResolver = itemResolver;
    this.pairSource = pairSource;
    this.recordingSource = recordingSource;
    this.evaluationStrategy = evaluationStrategy;
  }

  public Question nextQuestion() {
    LexicalPair pair = evaluationStrategy.choosePair(pairSource.pairs());
    List<LexicalItem> items = pair.asList().stream()
        .map(itemResolver::resolveItem)
        .toList();
    LexicalItem item = evaluationStrategy.chooseItem(items);
    List<Recording> recordings = recordingSource.recordingsFor(item);
    Recording recording = evaluationStrategy.chooseRecording(recordings);
    // TODO: Move question creation outside this method
    return new Question(pair, recording);
  }
}
