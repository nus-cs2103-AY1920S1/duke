public enum TaskType {
    T, D, E;

    public static TaskType convertToTaskType(String typeString) {
        switch (typeString.toUpperCase()) {
        case "T": return T;
        case "D": return D;
        case "E": return E;
        default: return null;
        }
    }
}
