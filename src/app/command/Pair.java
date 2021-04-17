package app.command;

public class Pair {

    private int first;
    private int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pair){
            Pair pair = (Pair) obj;
            if(pair.getFirst() == this.first && pair.getSecond() == this.getSecond())
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }
}
