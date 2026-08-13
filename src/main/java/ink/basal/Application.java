package ink.basal;

import java.nio.file.Path;

import com.google.gson.Gson;

import ink.basal.config.ConfigPaths;
import ink.basal.config.ConfigRepository;
import ink.basal.config.JsonConfigRepository;
import ink.basal.deck.Deck;
import ink.basal.deck.DeckLoader;
import ink.basal.deck.DeckRepository;
import ink.basal.deck.DocumentDeckRepository;
import ink.basal.deck.JsonDeckLoader;
import ink.basal.evaluation.DeckItemResolver;
import ink.basal.evaluation.DeckPairSource;
import ink.basal.evaluation.DeckRecordingSource;
import ink.basal.evaluation.EvaluationEngine;
import ink.basal.evaluation.RandomStrategy;
import ink.basal.ui.JavaSoundPlayer;
import ink.basal.ui.cli.Cli;

public final class Application {

  private final ConfigRepository configRepository;
  private final ApplicationState applicationState;

  private final Cli cli;

  private final DeckLoader deckLoader;
  private final DeckRepository deckRepository;

  private final EvaluationEngine evaluationEngine;

  public Application(
      ConfigRepository configRepository,
      ApplicationState applicationState,
      Cli cli,
      DeckLoader deckLoader,
      DeckRepository deckRepository,
      EvaluationEngine evaluationEngine) {
    this.configRepository = configRepository;
    this.applicationState = applicationState;
    this.cli = cli;
    this.deckLoader = deckLoader;
    this.deckRepository = deckRepository;
    this.evaluationEngine = evaluationEngine;
  }

  public static Application create() {
    JsonConfigRepository configRepository = new JsonConfigRepository(
        ConfigPaths.configDirectory().resolve("basal.json"), new Gson());
    configRepository.init();

    ApplicationState applicationState = new ApplicationState();

    DeckLoader deckLoader = new JsonDeckLoader();
    DocumentDeckRepository deckRepository = DocumentDeckRepository.create(configRepository,
        deckLoader);

    RandomStrategy evaluationStrategy = new RandomStrategy();
    DeckItemResolver itemResolver = new DeckItemResolver(applicationState);
    DeckPairSource pairSource = new DeckPairSource(applicationState);
    DeckRecordingSource recordingSource = new DeckRecordingSource(applicationState);
    EvaluationEngine evaluationEngine = new EvaluationEngine(itemResolver,
        pairSource,
        recordingSource,
        evaluationStrategy);

    JavaSoundPlayer audioPlayer = new JavaSoundPlayer(applicationState);
    Cli cli = new Cli(applicationState,
        configRepository,
        deckRepository,
        evaluationEngine,
        audioPlayer);

    return new Application(configRepository,
        applicationState,
        cli,
        deckLoader,
        deckRepository,
        evaluationEngine);
  }

  public void run() {
    configRepository.init();

    cli.run();
  }

}
