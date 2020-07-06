package nl.avisi.demo;

public final class UpstreamUnavailableException extends RuntimeException {
    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1L;

    public UpstreamUnavailableException(final String message) {
        super(message);
    }
}
