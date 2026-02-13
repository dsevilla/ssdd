package es.um.sisdist.backend.dao.models.utils;

public enum ChatStatus {

    READY, BUSY, FINISHED;

    /**
     * Converts a string to a ChatStatus enum.
     *
     * @param status the string representation of the status
     * @return the corresponding ChatStatus enum
     * @throws IllegalArgumentException if the string does not match any status
     */
    public static ChatStatus fromString(String status) {
        if (status == null) {
            return null;
        }
        switch (status.toUpperCase()) {
            case "READY":
                return READY;
            case "BUSY":
                return BUSY;
            case "FINISHED":
                return FINISHED;
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }
}
