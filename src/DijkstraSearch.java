import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;
    private final V source;
    private Map<V, Double> distances;
    private Map<V, V> edgeTo;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.distances = new HashMap<>();
        this.edgeTo = new HashMap<>();
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(vertex -> distances.getOrDefault(vertex.getData(), Double.MAX_VALUE)));
        Vertex<V> sourceVertex = graph.getVertex(source);
        if (sourceVertex == null) {
            throw new IllegalArgumentException("Source vertex not found in the graph");
        }

        distances.put(source, 0.0);
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            V currentData = current.getData();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                V neighborData = neighbor.getData();
                double newDist = distances.get(currentData) + entry.getValue();

                if (newDist < distances.getOrDefault(neighborData, Double.MAX_VALUE)) {
                    distances.put(neighborData, newDist);
                    edgeTo.put(neighborData, currentData);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        LinkedList<V> path = new LinkedList<>();
        for (V at = destination; at != null; at = edgeTo.get(at)) {
            path.addFirst(at);
        }
        return path;
    }
}
