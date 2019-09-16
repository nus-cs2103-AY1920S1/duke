package duke.storage;

/**
 * Set of possible storage key types.
 */
enum StorageKey {
    TYPE,
    DONE,
    DESCRIPTION,
    TIME;

    /**
     * Override implementation of toString that returns the lower case version.
     *
     * @return Lower case string representation of enum.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
