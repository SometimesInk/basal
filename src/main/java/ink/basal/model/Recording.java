package ink.basal.model;

import java.nio.file.Path;

public record Recording(
    String id,
    String lexicalItem,
    String audio) {
}
