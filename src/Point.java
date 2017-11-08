public class Point implements Comparable{

    private int x;
    private int y;

    public Point(){
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        Point anotherPoint = (Point) o;

        if(this.x < anotherPoint.x) {
            return -1;
        }
        else if(this.x == anotherPoint.x) {
            return Integer.compare(this.y, anotherPoint.y);
        }
        else {
            return 1;
        }
    }
}
