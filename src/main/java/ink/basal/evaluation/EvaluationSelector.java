package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalItem;
import ink.basal.model.LexicalPair;
import ink.basal.model.Recording;

public interface EvaluationSelector {

  LexicalPair choosePair(List<LexicalPair> pairs);

  LexicalItem chooseItem(List<LexicalItem> items);

  Recording chooseRecording(List<Recording> recordings);
}
