package ink.basal.config;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

public class JsonConfigRepository implements ConfigRepository {

  /**
   * Path of the config json file.
   */
  private final Path path;
  private final Gson gson;

  private Config currentConfig;

  public JsonConfigRepository(Path path, Gson gson) {
    this.path = path;
    this.gson = gson;
  }

  @Override
  public Config get() {
    if (currentConfig != null)
      return currentConfig;

    try (Reader reader = Files.newBufferedReader(path)) {
      currentConfig = gson.fromJson(reader.readAllAsString(), Config.class);
      return currentConfig;
    } catch (IOException e) {
      throw new RuntimeException("Could not load config", e);
    }
  }

  @Override
  public void save(Config config) {
    try {
      Files.createDirectories(path.getParent());

      try (Writer writer = Files.newBufferedWriter(path)) {
        gson.toJson(config, writer);
      }

      currentConfig = config;
    } catch (IOException e) {
      throw new RuntimeException("Could not save config", e);
    }
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(ConfigPaths.configDirectory());
      Files.createDirectories(ConfigPaths.dataDirectory());
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize directories.", e);
    }
  }

}
