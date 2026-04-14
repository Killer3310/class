public abstract class Shape
{
    protected Style style;
    public Shape()
    {
        style = new Style("#00000000", "black", 0.05);
    }
    public Shape(Style style)
    {
        this.style = style;
    }
    public abstract String toSvg();
    public abstract BoundingBox getBounds();
}
