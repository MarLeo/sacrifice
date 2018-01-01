package model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Modules extends Components {

    private final String tag;
    private final String hierarchyGroup;
    private final String referenceModule;
    private final String referenceComment;
    private final int qty;
    private final String positionConnector;
    private final int frontDepth;
    private final int rearDepth;
    private final int frontDissipation;
    private final int rearDissipation;

    public Modules(Builder<?> builder) {
        super(builder);
        this.tag = builder.tag;
        this.hierarchyGroup = builder.hierarchyGroup;
        this.referenceModule = builder.referenceModule;
        this.referenceComment = builder.referenceComment;
        this.qty = builder.qty;
        this.positionConnector = builder.positionConnector;
        this.frontDepth = builder.frontDepth;
        this.rearDepth = builder.rearDepth;
        this.frontDissipation = builder.frontDissipation;
        this.rearDissipation = builder.rearDissipation;
    }

    @Override
    public String toString() {
        return "Modules{" + super.toString() +
                "tag='" + tag + '\'' +
                "hierarchyGroup='" + hierarchyGroup + '\'' +
                ", referenceModule='" + referenceModule + '\'' +
                ", referenceComment='" + referenceComment + '\'' +
                ", qty=" + qty +
                ", positionConnector='" + positionConnector + '\'' +
                ", frontDepth=" + frontDepth +
                ", rearDepth=" + rearDepth +
                ", frontDissipation=" + frontDissipation +
                ", rearDissipation=" + rearDissipation +
                '}';
    }

    public static class Builder<T extends Modules.Builder<T>> extends Components.Builder<T> {

        private String tag;
        private String hierarchyGroup;
        private String referenceModule;
        private String referenceComment;
        private int qty;
        private String positionConnector;
        private int frontDepth;
        private int rearDepth;
        private int frontDissipation;
        private int rearDissipation;


        public T tag(String tag) {
            this.tag = tag;
            return self();
        }

        public T hierarchyGroup(String hierarchyGroup) {
            this.hierarchyGroup = hierarchyGroup;
            return self();
        }

        public T referenceModule(String referenceModule) {
            this.referenceModule = referenceModule;
            return self();
        }

        public T referenceComment(String referenceComment) {
            this.referenceComment = referenceComment;
            return self();
        }

        public T qty(int qty) {
            this.qty = qty;
            return self();
        }

        public T positionConnector(String positionConnector) {
            this.positionConnector = positionConnector;
            return self();
        }

        public T frontDepth(int frontDepth) {
            this.frontDepth = frontDepth;
            return self();
        }

        public T rearDepth(int rearDepth) {
            this.rearDepth = rearDepth;
            return self();
        }

        public T frontDissipation(int frontDissipation) {
            this.frontDissipation = frontDissipation;
            return self();
        }

        public T rearDissipation(int rearDissipation) {
            this.rearDissipation = rearDissipation;
            return self();
        }

        public Modules build() {
            return new Modules(this);
        }
    }
}
