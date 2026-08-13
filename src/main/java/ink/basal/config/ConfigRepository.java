package ink.basal.config;

import java.nio.file.Path;

public interface ConfigRepository {

  Config get();

  void save(Config config);

  void init();
}
