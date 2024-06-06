package domain;

import domain.list.ListException;
import org.junit.jupiter.api.Test;
import domain.GraphException;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {
    @Test
    void test() throws GraphException, ListException {
        AdjacencyListGraph graph = new AdjacencyListGraph(50);
        int num ;
        for (int i = 0; i < 20; i++) {
            num =util.Utility.getRandom(51)-1;
            graph.addVertex(num);
            System.out.println("num "+num+" was added");
        }
        System.out.println(graph);
        for (int i = 0; i < 5; i++) {

        }

        /*try {
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

    }catch (GraphException | ListException e){
            throw new RuntimeException(e);
        }*/

    }
}