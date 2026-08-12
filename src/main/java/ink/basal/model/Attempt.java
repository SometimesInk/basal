package ink.basal.model;

import java.time.Instant;

public record Attempt(
    String id,
    Instant timestamp,

    String questionId,

    String pairId,
    String deckId,
    String recordingId,

    Response response,
    boolean correct,

    long responseTime) {
}
