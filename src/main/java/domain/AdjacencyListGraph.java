package domain;

import domain.list.ListException;
import domain.queue.LinkedQueue;
import domain.queue.QueueException;
import domain.stack.LinkedStack;
import domain.stack.StackException;

public class AdjacencyListGraph implements Graph {
    private int n;
    private Vertex vertexList[];
    //private Object adjacencyList[][];
    private int counter; //contador de vertices

    //para los recorridos dfs, bfs
    private LinkedStack stack;
    private LinkedQueue queue;

    //Constructor
    public AdjacencyListGraph(int n){
        if(n<=0) System.exit(1);
        this.n = n;
        initObjects();
    }

    private void initObjects() {
        this.counter = 0;
        this.vertexList = new Vertex[n];

        this.stack = new LinkedStack();
        this.queue = new LinkedQueue();
       // initList(); //init matriz
    }

//    private void initList() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                0;
//            }
//        }
//    }

    @Override
    public int size() throws ListException {
        return counter;
    }

    @Override
    public void clear() {
        initObjects();
    }

    @Override
    public boolean isEmpty() {
        return counter==0;
    }

    @Override
    public boolean containsVertex(Object element) throws GraphException, ListException {
       if(isEmpty()){
           throw new GraphException("Adjacency List Graph is empty");
       }
        for (int i = 0; i < counter; i++) {
            if(util.Utility.compare(vertexList[i].data, element)==0)
                return true;
        }
        return false;
    }

    @Override
    public boolean containsEdge(Object a, Object b) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Adjacency List Graph is empty");
        }
        return !vertexList[indexOf(a)].edgesList.isEmpty()?vertexList[indexOf(a)].edgesList.contains(new Edgeweight(b,null)) : false;
    }

    @Override
    public void addVertex(Object element) throws GraphException, ListException {
        if(counter>=vertexList.length){
            throw new GraphException("Adjacency List graph is full");
        }
        vertexList[counter++] = new Vertex(element);
    }

    @Override
    public void addEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a) || !containsVertex(b)){
            throw new GraphException("Cannot add edge between vertexes ["
                    +a+"] and ["+b+"]");
        }
        //grafo no dirigido
        vertexList[indexOf(a)].edgesList.add(new Edgeweight(b,null));
        vertexList[indexOf(b)].edgesList.add(new Edgeweight(a,null));
    }

    private int indexOf(Object value) {
        for (int i = 0; i < counter; i++) {
            if(util.Utility.compare(vertexList[i].data, value)==0)
                return i; //retorno el indice
        }
        return -1; //significa que no lo encontro
    }

    @Override
    public void addWeight(Object a, Object b, Object weight) throws GraphException, ListException {
        if(!containsEdge(a, b)){
            throw new GraphException("Cannot add weight between vertexes ["
                    +a+"] and ["+b+"]");
        }
        Edgeweight ew = (Edgeweight) vertexList[indexOf(a)].edgesList.getNode(new Edgeweight(b,null)).data;
        ew.setWeight(weight);

        vertexList[indexOf(a)].edgesList.getNode(new Edgeweight(b,null)).setData(ew);

        ew = (Edgeweight) vertexList[indexOf(b)].edgesList.getNode(new Edgeweight(a,null)).data;
        ew.setWeight(weight);
        vertexList[indexOf(b)].edgesList.getNode(new Edgeweight(a,null)).setData(ew);
    }

    @Override
    public void addEdgeWeight(Object a, Object b, Object weight) throws GraphException, ListException {
        if(!containsVertex(a) || !containsVertex(b)){
            throw new GraphException("Cannot add edge between vertexes ["
                    +a+"] and ["+b+"]");
        }
        adjacencyList[indexOf(a)][indexOf(b)] = weight;
        adjacencyList[indexOf(b)][indexOf(a)] = weight; //grafo no dirigido
    }

    @Override
    public void removeVertex(Object element) throws GraphException, ListException {
        if(isEmpty()){
            throw new GraphException("Adjacency List Graph is empty");
        }

        if(containsVertex(element)){ //si el vertice existe en el grafo
            //run the list and delete each instance fo the edge
            //meaning this deletes every edge
            for (int i = 0; i < counter; i++) {
                if (util.Utility.compare(vertexList[i].data,element)==0){
                    for (int j = 0; j < counter; j++) {
                        if (containsEdge(vertexList[j].data,element))
                            removeEdge(vertexList[j].data,element);
                    }//supress the edge, then delete all edges
                }
                for (int j = 0; j < counter-1; j++) {
                    vertexList[j]=vertexList[j+1];
                }
            }

            counter--; //se decrementa por el vertice suprimido
        }

    }

    @Override
    public void removeEdge(Object a, Object b) throws GraphException, ListException {
        if(!containsVertex(a)||!containsVertex(b)){
            throw new GraphException("There's no some of the vertexes");
        }
        if ((!vertexList[indexOf(a)].edgesList.isEmpty())){
            vertexList[indexOf(a)].edgesList.remove(new Edgeweight(b,null));
        }
        if ((!vertexList[indexOf(b)].edgesList.isEmpty())){
            vertexList[indexOf(b)].edgesList.remove(new Edgeweight(a,null));
        }
    }

    @Override
    public String dfs() throws GraphException, StackException, ListException {
        setVisited(false);//marca todos los vertices como no vistados
        // inicia en el vertice 0
        String info =vertexList[0].data+", ";
        vertexList[0].setVisited(true); // lo marca
        stack.clear();
        stack.push(0); //lo apila
        while( !stack.isEmpty() ){
            // obtiene un vertice adyacente no visitado,
            //el que esta en el tope de la pila
            int index = adjacentVertexNotVisited((int) stack.top());
            if(index==-1) // no lo encontro
                stack.pop();
            else{
                vertexList[index].setVisited(true); // lo marca
                info+=vertexList[index].data+", "; //lo muestra
                stack.push(index); //inserta la posicion
            }
        }
        return info;
    }

    @Override
    public String bfs() throws GraphException, QueueException, ListException {
        setVisited(false);//marca todos los vertices como no visitados
        // inicia en el vertice 0
        String info =vertexList[0].data+", ";
        vertexList[0].setVisited(true); // lo marca
        queue.clear();
        queue.enQueue(0); // encola el elemento
        int v2;
        while(!queue.isEmpty()){
            int v1 = (int) queue.deQueue(); // remueve el vertice de la cola
            // hasta que no tenga vecinos sin visitar
            while((v2=adjacentVertexNotVisited(v1)) != -1 ){
                // obtiene uno
                vertexList[v2].setVisited(true); // lo marca
                info+=vertexList[v2].data+", "; //lo muestra
                queue.enQueue(v2); // lo encola
            }
        }
        return info;
    }

    //setteamos el atributo visitado del vertice respectivo
    private void setVisited(boolean value) {
        for (int i = 0; i < counter; i++) {
            vertexList[i].setVisited(value); //value==true o false
        }//for
    }

    private int adjacentVertexNotVisited(int index) throws ListException {
        Object vertexData= vertexList[index].data;
        for (int i = 0; i < counter; i++) {
            if(!vertexList[i].edgesList.isEmpty()&&vertexList[i].edgesList.contains(new Edgeweight(vertexData,null))
                    && !vertexList[i].isVisited())
                return i;//retorna la posicion del vertice adyacente no visitado
        }//for i
        return -1;
    }

    @Override
    public String toString() {
        String result = "Adjacency List Graph Content";
        for (int i = 0; i < counter; i++) {
            result+="\nThe vertex in the position: "+i+" is: "
                    +vertexList[i].data;
            if (!vertexList[i].edgesList.isEmpty());
            result+="\n...EDGES AND WEIGHTS: "+vertexList[i].edgesList.toString();
        }
        result+="\n";

        return result;
    }
}
