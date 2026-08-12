package ink.basal.ui;

import ink.basal.model.Recording;

/**
 * AudioPlayer
 */
public interface AudioPlayer {

  void play(Recording recording);

  void stop();

  boolean isPlaying();
}
