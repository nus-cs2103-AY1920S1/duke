public enum TaskType {
    T, D, E;

    public static TaskType convertToTaskType(String typeString) {
        switch (typeString.toUpperCase()) {
        case "T": case "TODO": return T;
        case "D": case "DEADLINE": return D;
        case "E": case "EVENT": return E;
        default: return null;
        }
    }
}
