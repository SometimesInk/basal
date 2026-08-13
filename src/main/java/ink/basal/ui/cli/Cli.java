package ink.basal.ui.cli;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.sun.source.util.TaskEvent;

import ink.basal.ApplicationState;
import ink.basal.config.ConfigRepository;
import ink.basal.deck.Deck;
import ink.basal.deck.DeckRepository;
import ink.basal.evaluation.EvaluationEngine;
import ink.basal.model.LexicalItem;
import ink.basal.model.Question;
import ink.basal.ui.AudioPlayer;

public class Cli {

  private final ApplicationState applicationState;
  private final ConfigRepository configRepository;
  private final DeckRepository deckRepository;
  private final EvaluationEngine evaluationEngine;
  private final AudioPlayer audioPlayer;

  public Cli(ApplicationState applicationState,
      ConfigRepository configRepository,
      DeckRepository deckRepository,
      EvaluationEngine evaluationEngine,
      AudioPlayer audioPlayer) {
    this.applicationState = applicationState;
    this.configRepository = configRepository;
    this.deckRepository = deckRepository;
    this.evaluationEngine = evaluationEngine;
    this.audioPlayer = audioPlayer;
  }

  public void run() {
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Found " + deckRepository.size() + " decks.");
      if (deckRepository.size() == 0) {
        System.out.println("No decks. Exiting.");
        return;
      }
      Deck selected = deckRepository.decks().getFirst();
      System.out.println("Automatically selecting first deck: " + selected.name());
      applicationState.load(selected);

      full: while (true) {
        switch (taskSelection(reader)) {
          case "1":
            evaluateDeck(reader);
            break;
          case "2":
            manageDecks(reader);
            break;
          case "3":
            break full;
          default:
            System.err.println("Unknown option. Enter 1, 2 or 3.");
            continue;
        }
      }
    } catch (IOException e) {
      System.err.println("IO exception");
      e.printStackTrace();
    }
  }

  public String taskSelection(BufferedReader reader) throws IOException {
    System.out.println("Tasks:");
    System.out.println("(1) evaluate deck");
    System.out.println("(2) manage decks");
    System.out.println("(3) quit");
    return reader.readLine();
  }

  public void evaluateDeck(BufferedReader reader) throws IOException {
    while (true) {
      System.out.println("In deck " + applicationState.currentDeck().name());
      System.out.println("Question:");
      Question question = evaluationEngine.nextQuestion();
      audioPlayer.play(question.target());
      LexicalItem a = applicationState.currentDeck().itemById(question.lexicalPair().lexicalItemA());
      LexicalItem b = applicationState.currentDeck().itemById(question.lexicalPair().lexicalItemB());
      System.out.println("A: " + a.orthography());
      System.out.println("B: " + b.orthography());
      String answer = question.target().lexicalItem().equals(a.id()) ? "a" : "b";

      // wait for answer
      String text = reader.readLine();
      if (text.equals("q"))
        break;

      if (text.equals(answer)) {
        System.out.println("Yes.");
      } else {
        System.out.println("No. Was " + answer);
      }
    }
  }

  public void manageDecks(BufferedReader reader) throws IOException {

  }

}
