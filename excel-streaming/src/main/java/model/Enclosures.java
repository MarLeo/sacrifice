package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Enclosures extends Components {

    private final int width;
    private final int height;
    private final int depth;
    private final int verticalOffset;
    private final int horizontalOffset;
    private final int numberOfDoors;
    private final String wmOrFs;


    Enclosures(Builder<?> builder) {
        super(builder);
        this.width = builder.width;
        this.height = builder.height;
        this.depth = builder.depth;
        this.verticalOffset = builder.verticalOffset;
        this.horizontalOffset = builder.horizontalOffset;
        this.numberOfDoors = builder.numberOfDoors;
        this.wmOrFs = builder.wmOrFs;
    }

    public static Builder<?> builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Enclosures{" + super.toString() +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", verticalOffset=" + verticalOffset +
                ", horizontalOffset=" + horizontalOffset +
                ", numberOfDoors=" + numberOfDoors +
                ", wmOrFs='" + wmOrFs + '\'' +
                '}';
    }

    //<editor-fold desc="Builder Class">
    public static class Builder<T extends Builder<T>> extends Components.Builder<T> {
        private int width;
        private int height;
        private int depth;
        private int verticalOffset;
        private int horizontalOffset;
        private int numberOfDoors;
        private String wmOrFs;

        public T width(int width) {
            this.width = width;
            return self();
        }

        public T height(int height) {
            this.height = height;
            return self();
        }

        public T depth(int depth) {
            this.depth = depth;
            return self();
        }

        public T verticalOffset(int verticalOffset) {
            this.verticalOffset = verticalOffset;
            return self();
        }

        public T horizontalOffset(int horizontalOffset) {
            this.horizontalOffset = horizontalOffset;
            return self();
        }

        public T numberOfDoors(int numberOfDoors) {
            this.numberOfDoors = numberOfDoors;
            return self();
        }

        public T wmOrFs(String wmOrFs) {
            this.wmOrFs = wmOrFs;
            return self();
        }

        public Enclosures build() {
            return new Enclosures(this);
        }
    }
    //</editor-fold>


}
