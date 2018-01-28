package model;

import lombok.Data;

@Data
public class Components {

    private final String componentType;
    private final String reference;
    private final String description;
    private final String manufacturer;
    private final String productRange;

    protected Components(Builder<?> builder) {
        this.componentType = builder.componentType;
        this.reference = builder.reference;
        this.description = builder.description;
        this.manufacturer = builder.manufacturer;
        this.productRange = builder.productRange;
    }


    public static Builder<?> builder() {
        return new Builder();
    }


    //<editor-fold desc="Builder class">
    public static class Builder<T extends Builder<T>> {
        private String componentType;
        private String reference;
        private String description;
        private String manufacturer;
        private String productRange;

        protected T self() {
            return (T) this;
        }


        public T componentType(String componentType) {
            this.componentType = componentType;
            return self();
        }

        public T reference(String reference) {
            this.reference = reference;
            return self();
        }

        public T description(String description) {
            this.description = description;
            return self();
        }

        public T manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return self();
        }

        public T productRange(String productRange) {
            this.productRange = productRange;
            return self();
        }

        public Components build() {
            return new Components(this);
        }

    }
    //</editor-fold>


}
