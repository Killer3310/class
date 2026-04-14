public class Segment {
    private Point a = new Point(0, 0), b = new Point(1, 1);
    public Segment() { }
    public Segment(Point a, Point b)
    {
        this.a = new Point(a);
        this.b = new Point(b);
    }
    public Point getA()
    {
        return a;
    }
    public Point getB()
    {
        return b;
    }
    public float length()
    {
        float dx = a.getX() - b.getX(), dy = a.getY() - b.getY();
        return (float)Math.sqrt(dx * dx + dy * dy);
    }
    public String toString()
    {
        return "{" + a.toString() + ", " + b.toString() + "}";
    }
    public Segment perpendicular()
    {
        Point vec = a.vecTo(b);
        vec.mul(.5f);
        Point mid = a.translated(vec);
        vec.rotate90();
        Point newA = mid.translated(vec), newB = mid.translated(vec.negated());
        return new Segment(newA, newB);
    }
    public Point mid()
    {
        return a.translated(b).muld(.5f);
    }
}
