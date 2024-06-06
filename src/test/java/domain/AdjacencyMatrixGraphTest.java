package domain;

import domain.list.ListException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {

    @Test
    void test() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(50);
        try {
            graph.addVertex('A');
            graph.addVertex('B');
            graph.addVertex('C');
            graph.addVertex('D');
            graph.addVertex('E');
            graph.addVertex('F');
            graph.addVertex('G');
            graph.addVertex('H');

            graph.addEdgeWeight('A', 'B', 20);
            graph.addEdgeWeight('A', 'E', 15);
            graph.addEdgeWeight('B', 'C', 10);
            graph.addEdgeWeight('E', 'F', 30);
            graph.addEdgeWeight('C', 'D', 40);
            graph.addEdgeWeight('F', 'G', 5);
            graph.addEdgeWeight('G', 'H', 7);

            //lanza una excepción
            //graph.addEdgeWeight('G', 'K', 7);

            System.out.println(graph.toString());

        } catch (GraphException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }
}