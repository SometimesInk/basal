package ink.basal;

import java.nio.file.Path;

import com.google.gson.Gson;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

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
import ink.basal.evaluation.EvaluationStrategy;
import ink.basal.evaluation.ItemResolver;
import ink.basal.evaluation.PairSource;
import ink.basal.evaluation.RandomStrategy;
import ink.basal.evaluation.RecordingSource;
import ink.basal.ui.AudioPlayer;
import ink.basal.ui.JavaSoundPlayer;
import ink.basal.ui.cli.Cli;

public final class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

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
    LOG.info("Starting basal");
    ConfigRepository configRepository = new JsonConfigRepository(
        ConfigPaths.configDirectory().resolve("basal.json"), new Gson());
    configRepository.init();

    ApplicationState applicationState = new ApplicationState();

    DeckLoader deckLoader = new JsonDeckLoader();
    DeckRepository deckRepository = DocumentDeckRepository.create(deckLoader);

    EvaluationStrategy evaluationStrategy = new RandomStrategy();
    ItemResolver itemResolver = new DeckItemResolver(applicationState);
    PairSource pairSource = new DeckPairSource(applicationState);
    RecordingSource recordingSource = new DeckRecordingSource(applicationState);
    EvaluationEngine evaluationEngine = new EvaluationEngine(itemResolver,
        pairSource,
        recordingSource,
        evaluationStrategy);

    AudioPlayer audioPlayer = new JavaSoundPlayer(applicationState);
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
    LOG.info("Starting CLI");
    cli.run();
    LOG.info("Stopping CLI");
    LOG.info("Stopping basal");
  }

}
