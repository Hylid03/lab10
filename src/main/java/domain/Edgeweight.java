package domain;

public class Edgeweight {
    private Object edge;
    private Object weight;

    public Edgeweight(Object edge, Object weight) {
        this.edge=edge;
        this.weight=weight;
    }

    public Object getEdge() {
        return edge;
    }

    public void setEdge(Object edge) {
        this.edge = edge;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edgeweight{" +
                "edge=" + edge +
                ", weight=" + weight +
                '}';
    }
}
