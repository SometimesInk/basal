package ink.basal.model;

/**
 * Ephemerial object used to store state before generating attempt from user
 * response to this question.
 */
public record Question(
    LexicalPair lexicalPair,
    Recording target) {
}
