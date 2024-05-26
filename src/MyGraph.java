import java.util.*;

public class MyGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();
    private boolean directed;

    public MyGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V dest) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);
        sourceVertex.addAdjacentVertex(destVertex, 1.0); // Use a default weight of 1.0
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, 1.0);
        }
    }

    public Vertex<V> getVertex(V key) {
        return vertices.get(key);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}

