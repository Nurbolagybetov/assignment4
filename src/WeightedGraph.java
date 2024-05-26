import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);
        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}

