package ink.basal.evaluation;

import java.util.List;

import ink.basal.model.LexicalItem;
import ink.basal.model.Recording;

public interface RecordingSource {
  List<Recording> recordingsFor(LexicalItem item);

}
