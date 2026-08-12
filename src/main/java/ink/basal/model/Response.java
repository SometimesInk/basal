package ink.basal.model;

public record Response(
    String id,
    String response,
    boolean correct,
    long duration) {
}
