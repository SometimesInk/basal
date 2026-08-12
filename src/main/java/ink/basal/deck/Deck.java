package ink.basal.deck;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ink.basal.model.Contrast;
import ink.basal.model.LexicalItem;
import ink.basal.model.LexicalPair;
import ink.basal.model.Recording;

public final class Deck {
  private final int format;
  private final String id;
  private final String name;

  private final List<Contrast> contrasts;
  private final List<LexicalPair> lexicalPairs;
  private final List<LexicalItem> lexicalItems;
  private final List<Recording> recordings;

  public Deck(int format, String id, String name, List<Contrast> contrasts,
      List<LexicalPair> lexicalPairs,
      List<LexicalItem> lexicalItems,
      List<Recording> recordings) {
    this.format = format;
    this.id = id;
    this.name = name;
    this.contrasts = contrasts;
    this.lexicalPairs = lexicalPairs;
    this.lexicalItems = lexicalItems;
    this.recordings = recordings;
  }

  public int format() {
    return format;
  }

  public String id() {
    return id;
  }

  public String name() {
    return name;
  }

  public List<Contrast> contrasts() {
    return contrasts;
  }

  public List<LexicalPair> lexicalPairs() {
    return lexicalPairs;
  }

  public List<LexicalItem> lexicalItems() {
    return lexicalItems;
  }

  public List<Recording> recordings() {
    return recordings;
  }

  public List<Recording> recordingsFor(String lexicalItem) {
    return recordings.stream()
        .filter(recording -> recording.lexicalItem().equals(lexicalItem))
        .toList();
  }

  /**
   * @return Contrast or null when missing.
   */
  public Contrast contrastById(String id) {
    return contrasts.stream()
        .filter(contrast -> contrast.id().equals(id))
        .findAny()
        .orElse(null);
  }

  /**
   * @return Pair or null when missing.
   */
  public LexicalPair pairById(String id) {
    return lexicalPairs.stream()
        .filter(pair -> pair.id().equals(id))
        .findAny()
        .orElse(null);
  }

  /**
   * @return Item or null when missing.
   */
  public LexicalItem itemById(String id) {
    return lexicalItems.stream()
        .filter(item -> item.id().equals(id))
        .findAny()
        .orElse(null);
  }

  /**
   * @return Recording or null when missing.
   */
  public Recording recordingById(String id) {
    return recordings.stream()
        .filter(recording -> recording.id().equals(id))
        .findAny()
        .orElse(null);
  }
}
