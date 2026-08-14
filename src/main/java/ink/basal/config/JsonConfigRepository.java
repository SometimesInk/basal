package ink.basal.config;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class JsonConfigRepository implements ConfigRepository {

  private static final Logger LOG = LoggerFactory.getLogger(JsonConfigRepository.class);

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
      throw new RuntimeException("Could not read config", e);
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
      if (!ConfigPaths.configDirectory().toFile().exists())
        LOG.warn("No config directory '{}'", ConfigPaths.configDirectory());
      if (!ConfigPaths.dataDirectory().toFile().exists())
        LOG.warn("No data directory '{}'", ConfigPaths.configDirectory());
      Files.createDirectories(ConfigPaths.configDirectory());
      Files.createDirectories(ConfigPaths.dataDirectory());
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize directories.", e);
    }
  }

}
