public class Polygon extends Shape
{
    public static Polygon square(Segment s, Style style)
    {
        Segment perp = s.perpendicular();
        Point[] points = new Point[] { s.getA(), perp.getA(), s.getB(), perp.getB() };
        return new Polygon(points, style);
    }
    private final Point[] points;
    public Polygon(Point[] points)
    {
        super();
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) this.points[i] = new Point(points[i]);
    }
    public Polygon(Point[] points, Style style)
    {
        this(points);
        this.style = style;
    }
    public Polygon(Polygon p)
    {
        this.style = p.style;
        points = new Point[p.points.length];
        for (int i = 0; i < points.length; i++) points[i] = new Point(p.points[i]);
    }
    public Point[] getPoints() { return points; }
    public String toString()
    {
        StringBuilder out = new StringBuilder("{");
        for (int i = 0; i < points.length; i++)
        {
            out.append(points[i].toString());
            if (i != points.length - 1) out.append(", ");
        }
        return out + "}";
    }
    public String toSvg()
    {
        StringBuilder out = new StringBuilder("<polygon points=\"");
        for (int i = 0; i < points.length; i++)
        {
            out.append(points[i].toString());
            if (i != points.length - 1) out.append(" ");
        }
        out.append("\" ");
        if (style != null) out.append(style.toSvg()).append(" ");
        else out.append("style=\"fill:#FF00FF00;stroke-width:1;stroke:black\" ");
        return out.append("/>").toString();
    }
    public BoundingBox getBounds()
    {
        float minX = points[0].getX(), maxX = minX, minY = points[0].getY(), maxY = minY;
        for (Point point : points) {
            float x = point.getX(), y = point.getY();
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
        return new BoundingBox(minX, maxX, minY, maxY);
    }
}
