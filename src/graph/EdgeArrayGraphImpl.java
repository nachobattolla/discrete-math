package graph;

import java.util.ArrayList;
import java.util.List;

// TODO: implement
public class EdgeArrayGraphImpl<T> implements Graph<T> {
    private ArrayList<T> v = new ArrayList<>();
    private  int n;
    private  int alpha ;
    private  ArrayList<T[]> a = new ArrayList<>();

    @Override
    public void addVertex(T x) {
        v.add(x);
    }

    @Override
    public boolean hasVertex(T v){
        for (int i = 0; i <this.v.size() ; i++) {
            T h = v;
             T k =this.v.get(i);
            if(this.v.get(i).equals( v))
                return true;
        }
        return false;
    }

    @Override
    public void removeVertex(T x) {
        for (int i = 0; i <v.size() ; i++) {
            if(this.v.get(i)== x)
                v.remove(i);
        }

    }

    @Override
    public void addEdge(T v, T w) {
        if(hasVertex(v) && hasVertex(w)) {
            T[] edge = (T[]) new Object[2];
            edge[0] = v;
            edge[1] = w;
            a.add(edge);
        }
    }

    @Override
    public void removeEdge(T v, T w) {
        for (int i = 0; i <a.size() ; i++) {
            if((a.get(i)[0] == v && a.get(i)[1]==w)||(a.get(i)[0] == w && a.get(i)[1]==v))
                a.remove(i);
        }
    }

    @Override
    public boolean hasEdge(T v, T w) {
        for (int i = 0; i <a.size() ; i++) {
            if ((a.get(i)[0] .equals(v) && a.get(i)[1].equals(w)||(a.get(i)[0].equals( w) && a.get(i)[1].equals(v))))
                return true;

        }
        return false;
    }

    @Override
    public int order() {
        n=v.size();
        return n;
    }

    @Override
    public int alpha() {
        alpha = a.size();
        return alpha;
    }

    @Override
    public List<T> getVertexes() {
        return v;
    }

    @Override
    public List<T> getAdjacencyList(T v) {
        ArrayList<T> a1 = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] == v)
                a1.add(a.get(i)[1]);
            else if (a.get(i)[1] == v)
                a1.add(a.get(i)[0]);
        }

        return a1;
    }
}