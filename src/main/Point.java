package main;

public class Point {
	public int x;
    public int y;
    public Point previous;

    public Point(int x, int y, Point previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
    }

    @Override
    public String toString() { return String.format("(%d, %d)", x, y); }

    public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPrevious(Point previous) {
		this.previous = previous;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getPrevious() {
		return previous;
	}

	@Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
    public Point offset(int ox, int oy) 
    { return new Point(x + ox, y + oy, this);  }

}
