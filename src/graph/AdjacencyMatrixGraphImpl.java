package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: implement
public class AdjacencyMatrixGraphImpl<T> implements Graph<T> {

    private List<T> V;
    private int[][] A;
    private int n;
    private int alfa;

    public AdjacencyMatrixGraphImpl(){
        //V = (T[]) new Object[10];
        V = new ArrayList<T>();
        A = new int[10][10];
        n = 0;
        alfa = 0;
    }

    @Override
    public void addVertex(T x) {
        /* Queda a cargo del lector tratar el caso en que haya que agrandar el
        arreglo y la matriz*/

        if (!hasVertex(x)) {
            int[][] matrix = new int[A.length + 1][A.length + 1];
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    matrix[i][j] = A[i][j];
                }
            }
            A = matrix;

        }
        V.add(x);
        n++;
    }

    @Override
    public boolean hasVertex(T v) {
        return V.contains(v);
    }

    @Override
    public void removeVertex(T x){
        if(V.contains(x)) {
            n--;
            int[][] matrix = new int[A.length - 1][A.length - 1];
            LinkedList<Integer> values= new LinkedList<Integer>();
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length ; j++) {
                    if(i!=V.indexOf(x)&&j!=V.indexOf(x)){
                        values.add(A[i][j]);
                    }else{
                        alfa=alfa-A[i][j]/2;
                    }
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j]=values.pollFirst();
                }
            }
            V.remove(x);
            A=matrix;
        }
    }

    @Override
    public void addEdge(T v, T w) {
        if(hasVertex(v)&&hasVertex(w)){
            A[V.indexOf(v)][V.indexOf(w)]++;
            A[V.indexOf(w)][V.indexOf(v)]++;
            alfa++;
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        if(hasVertex(v)&&hasVertex(w)){
            int edgeN = A[V.indexOf(v)][V.indexOf(w)];
            if(edgeN != 0) {
                A[V.indexOf(v)][V.indexOf(w)]--;
                A[V.indexOf(v)][V.indexOf(w)]--;
                alfa--;
            }
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        if (hasVertex(v) && hasVertex(w)) {
            return (A[V.indexOf(v)][V.indexOf(w)] > 0);
        }
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
        return V;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        if(hasVertex(v)) {
            ArrayList<T> adjencyList = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                if (A[V.indexOf(v)][i]>0){
                    adjencyList.add(V.get(i));
                }
            }
            return adjencyList;
        }
        return null;
    }

}
