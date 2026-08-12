package ink.basal;

import java.nio.file.Path;

import ink.basal.deck.Deck;
import ink.basal.deck.DeckLoader;
import ink.basal.deck.DeckRepository;
import ink.basal.deck.DocumentDeckRepository;
import ink.basal.deck.JsonDeckLoader;
import ink.basal.evaluation.DeckItemResolver;
import ink.basal.evaluation.DeckPairSource;
import ink.basal.evaluation.DeckRecordingSource;
import ink.basal.evaluation.EvaluationEngine;
import ink.basal.evaluation.RandomSelector;
import ink.basal.ui.JavaSoundPlayer;
import ink.basal.ui.cli.Cli;

public final class Application {

  private final Cli cli;

  private final DeckLoader deckLoader;
  private final DeckRepository deckRepository;

  private final EvaluationEngine evaluationEngine;

  public Application(Cli cli,
      DeckLoader deckLoader,
      DeckRepository deckRepository,
      EvaluationEngine evaluationEngine) {
    this.cli = cli;
    this.deckLoader = deckLoader;
    this.deckRepository = deckRepository;
    this.evaluationEngine = evaluationEngine;
  }

  public static Application create() {

    DeckLoader deckLoader = new JsonDeckLoader();
    DocumentDeckRepository deckRepository = DocumentDeckRepository.create(deckLoader);

    RandomSelector randomSelector = new RandomSelector();
    DeckItemResolver itemResolver = new DeckItemResolver(deckRepository);
    DeckPairSource pairSource = new DeckPairSource(deckRepository);
    DeckRecordingSource recordingSource = new DeckRecordingSource(deckRepository);
    EvaluationEngine evaluationEngine = new EvaluationEngine(itemResolver, pairSource, recordingSource, randomSelector);

    JavaSoundPlayer audioPlayer = new JavaSoundPlayer(deckRepository);
    Cli cli = new Cli(deckRepository, evaluationEngine, audioPlayer);
    return new Application(cli, deckLoader, deckRepository, evaluationEngine);
  }

  public void run() {
    cli.run();
  }

}
