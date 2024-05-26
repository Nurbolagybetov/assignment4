import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final MyGraph<V> graph;
    private final V source;
    private Map<V, V> edgeTo;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        this.edgeTo = new HashMap<>();
        dfs(source);
    }

    private void dfs(V current) {
        Stack<V> stack = new Stack<>();
        stack.push(current);
        Set<V> visited = new HashSet<>();
        visited.add(current);

        while (!stack.isEmpty()) {
            V vertex = stack.pop();
            Vertex<V> currentVertex = graph.getVertex(vertex);

            for (Vertex<V> neighbor : currentVertex.getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData);
                    edgeTo.put(neighborData, vertex);
                    stack.push(neighborData);
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
