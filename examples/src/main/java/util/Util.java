package util;

import lombok.Data;
import model.Person;

import java.util.Comparator;

public class Util {


    public static class AgeComparator implements Comparator<Person> {

        @Override
        public int compare(final Person left, final Person right) {
            return Integer.compare(left.getAge(), right.getAge());
        }
    }

    public static class ReverseComparator<T> implements Comparator<T> {

        private final Comparator<T> delegateComparator;

        public ReverseComparator(Comparator<T> delegateComparator) {
            this.delegateComparator = delegateComparator;
        }

        @Override
        public int compare(T left, T right) {
            return -1 * delegateComparator.compare(left, right);
        }
    }


    @Data
    public static class SortedPair<T extends Comparable<T>> {

        private final T first;
        private final T second;

        public SortedPair(T left, T right) {

            if (left.compareTo(right) < 0) {
                this.first = left;
                this.second = right;
            } else {
                first = right;
                second = left;
            }

        }
    }

}
