import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final MyGraph<V> graph;
    private final V source;
    private Map<V, V> edgeTo;

    public BreadthFirstSearch(MyGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.edgeTo = new HashMap<>();
        bfs();
    }

    private void bfs() {
        Queue<V> queue = new LinkedList<>();
        queue.add(source);
        Set<V> visited = new HashSet<>();
        visited.add(source);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Vertex<V> currentVertex = graph.getVertex(current);

            for (Vertex<V> neighbor : currentVertex.getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData);
                    edgeTo.put(neighborData, current);
                    queue.add(neighborData);
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

