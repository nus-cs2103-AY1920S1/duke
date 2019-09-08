package task;

import java.util.Comparator;

class TaskComparatorFactory {

     static Comparator<Task> getNameComparator(boolean isReversed) {
        return (t1, t2) -> {
            int output = t1.getName().compareToIgnoreCase(t2.getName());
            return isReversed ? -output : output;
        };
    }

    static Comparator<Task> getDeadlineComparator(boolean isReversed) {
        return (t1, t2) -> {
            int output;
            if (t1 instanceof Todo && t2 instanceof Todo) {
                output = 0;
            } else if (t1 instanceof Todo) {
                output = Integer.MAX_VALUE;
            } else if (t2 instanceof Todo) {
                output = Integer.MIN_VALUE;
            } else {
                output = t1.getDeadlineFromAdditionalInfo().compareTo(t2.getDeadlineFromAdditionalInfo());
            }
            return isReversed ? -output : output;
        };
    }

    static Comparator<Task> getTypeComparator(boolean isReversed) {
        return (t1, t2) -> {
            int output = t1.getTypeSymbol().compareToIgnoreCase(t2.getTypeSymbol());
            return isReversed ? -output : output;
        };
    }

    static Comparator<Task> getStatusComparator(boolean isReversed) {
        return (t1, t2) -> {
            int output;
            if (t1.getStatus() == t2.getStatus()) {
                output = 0;
            } else if (t1.getStatus() > t2.getStatus()) {
                output = 1;
            } else {
                output = -1;
            }
            return isReversed ? -output : output;
        };
    }
}
