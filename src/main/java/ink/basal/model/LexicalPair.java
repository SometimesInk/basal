package ink.basal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record LexicalPair(
    String id,
    String lexicalItemA,
    String lexicalItemB,
    String contrast) {

  public List<String> asList() {
    return Arrays.asList(lexicalItemA, lexicalItemB);
  }
}
