package ink.basal.config;

import java.nio.file.Path;

public class ConfigPaths {
  private ConfigPaths() {
  }

  public static Path configDirectory() {
    return Path.of(
        System.getProperty("user.home"),
        ".config",
        "basal");
  }

  public static Path dataDirectory() {
    return Path.of(
        System.getProperty("user.home"),
        ".local",
        "share",
        "basal");
  }
}
