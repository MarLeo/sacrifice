package model;

import lombok.Data;

@Data
public class Components {

    private final String componentType;
    private final String reference;
    private final String description;
    private final String manufacturer;
    private final String productRange;

    public Components(Builder<?> builder) {
        this.componentType = builder.componentType;
        this.reference = builder.reference;
        this.description = builder.description;
        this.manufacturer = builder.manufacturer;
        this.productRange = builder.productRange;
    }


    public static class Builder<T extends Builder<T>> {
        private String componentType;
        private String reference;
        private String description;
        private String manufacturer;
        private String productRange;

        public Builder() {
        }

        public T componentType(String componentType) {
            this.componentType = componentType;
            return (T) this;
        }

        public T reference(String reference) {
            this.reference = reference;
            return (T) this;
        }

        public T description(String description) {
            this.description = description;
            return (T) this;
        }

        public T manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return (T) this;
        }

        public T productRange(String productRange) {
            this.productRange = productRange;
            return (T) this;
        }

        public Components build() {
            return new Components(this);
        }
    }


}
