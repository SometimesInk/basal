package ink.basal.ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import ink.basal.ApplicationState;
import ink.basal.config.ConfigRepository;
import ink.basal.deck.DeckRepository;
import ink.basal.model.Recording;

/**
 * JavaSoundPlayer
 */
public class JavaSoundPlayer implements AudioPlayer {

  private final ApplicationState applicationState;

  private boolean isPlaying = false;

  public JavaSoundPlayer(ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  public void play(Recording recording) {
    try (AudioInputStream audio = AudioSystem
        .getAudioInputStream(applicationState.currentDeckPath().resolve(recording.audio()).toFile())) {
      Clip clip = AudioSystem.getClip();
      clip.open(audio);
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {

  }

  public boolean isPlaying() {
    return isPlaying;
  }

}
