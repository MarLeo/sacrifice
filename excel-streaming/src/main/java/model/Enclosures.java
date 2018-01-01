package model;


public class Enclosures extends Components {

    private final int width;
    private final int height;
    private final int depth;
    private final int verticalOffset;
    private final int horizontalOffset;
    private final int numberOfDoors;
    private final String wmOrFs;


    public Enclosures(Builder builder) {
        super(builder);
        this.width = builder.width;
        this.height = builder.height;
        this.depth = builder.depth;
        this.verticalOffset = builder.verticalOffset;
        this.horizontalOffset = builder.horizontalOffset;
        this.numberOfDoors = builder.numberOfDoors;
        this.wmOrFs = builder.wmOrFs;
    }


    public static class Builder extends Components.Builder<Builder> {
        private int width;
        private int height;
        private int depth;
        private int verticalOffset;
        private int horizontalOffset;
        private int numberOfDoors;
        private String wmOrFs;

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder depth(int depth) {
            this.depth = depth;
            return this;
        }

        public Builder verticalOffset(int verticalOffset) {
            this.verticalOffset = verticalOffset;
            return this;
        }

        public Builder horizontalOffset(int horizontalOffset) {
            this.horizontalOffset = horizontalOffset;
            return this;
        }

        public Builder numberOfDoors(int numberOfDoors) {
            this.numberOfDoors = numberOfDoors;
            return this;
        }

        public Builder wmOrFs(String wmOrFs) {
            this.wmOrFs = wmOrFs;
            return this;
        }

        public Enclosures build() {
            return new Enclosures(this);
        }
    }


}
