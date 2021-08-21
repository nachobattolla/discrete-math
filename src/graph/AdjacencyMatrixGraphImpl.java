package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: implement
public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {

    private T V[];
    private boolean[][] A;
    private int n;
    private int alfa;

    public AdjacencyMatrixGraphImpl(){
        V = (T[]) new Object[10];
        A = new boolean[10][10];
        n = 0;
        alfa = 0;
    }

    public AdjacencyMatrixGraphImpl(int capacidad) {
        V = (T[]) new Object[capacidad];
        A = new boolean[capacidad][capacidad];
        n = 0;
        alfa = 0;
    }

    @Override
    public void addVertex(T x) {
        /* Queda a cargo del lector tratar el caso en que haya que agrandar el
        arreglo y la matriz*/
        if (!isFull(V)) {
            V[n] = x;
            n++;
        }else{
            enlargeArray(V);
            enlargeEdgeArray(A);
            addVertex(x);
        }
    }

    public boolean isFull(T[] V){
        for (int i = 0; i < V.length; i++) {
            if (V[i] == null){
                return false;
            }
        }
        return true;
    }

    public void enlargeArray(T[] array){
        T[] arrayEnlarge =  (T[]) new Object[array.length+ 10];
        for (int i = 0; i < array.length; i++) {
            arrayEnlarge[i] = array[i];
        }
         V= arrayEnlarge;
    }

    @Override
    public boolean hasVertex(T v) {

        for (int i = 0; i < V.length; i++) {
            if (V[i]==v) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeVertex(T x) {
        for (int i = 0; i < V.length; i++) {
            if (V[i] == x){
                V[i] = null;
                n--;
            }
        }
    }

    @Override
    public void addEdge(T v, T w) {
        if (hasVertex(v) && hasVertex(w)) {
            if (!A[vertexIndex(v)][vertexIndex( w)]) {
                A[vertexIndex(v)][vertexIndex( w)] = A[vertexIndex(w)][vertexIndex(v)] = true;
                alfa++;
            }
        }
    }

    public void enlargeEdgeArray(boolean[][] array){
        boolean[][] arrayEnlarge =  new boolean[array.length+ 10][array.length+ 10];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length ; j++) {
                arrayEnlarge[i][j] = array[i][j];
            }
        }
        A = arrayEnlarge;
    }

    @Override
    public void removeEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)) {
            if (A[vertexIndex(v)][vertexIndex(w)]) {
                A[vertexIndex(v)][vertexIndex(w)] = A[vertexIndex(w)][vertexIndex(v)] = false;
                alfa--;
            }
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if(hasVertex(v) && hasVertex( w))
        return A[vertexIndex(v)][vertexIndex( w)];
        return false;
    }

    @Override
    public int order() {
        return n;
    }

    @Override
    public int alpha() {
        return alfa;
    }

    @Override
    public List<T> getVertexes() {
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < V.length; i++) {
            list.add(V[i]);
        }
        return list;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        LinkedList lst = new LinkedList();
        for (int w = 0; w < n ; w++)
            if (A[(int) v][w])
                lst.add(w);
        return lst;
    }

    public void matrixPrinter(){
        for(int i=0; i< n; i++){
            for(int j=0; j< n; j++){
                int m = 0;
                if (A[i][j]){
                    m = 1;
                }
                System.out.print( m + "  " );
            }
            System.out.println();
        }
    }

    public int[][] matrixAToInt(){
        int[][] matrix = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length ; j++) {
                if (A[i][j]){
                    matrix[i][j] = 1;
                }else{
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    private int vertexIndex(T v){
        for (int i = 0; i < V.length ; i++) {
            if(V[i]==v)
                return i;
        }
        throw new IllegalArgumentException("No such vertex on the array");
    }
}
