public class Ellipse extends Shape
{
    private Point center;
    private float rx, ry;
    public Ellipse(Point center, float rx, float ry, Style style)
    {
        super(style);
        this.center = center;
        this.rx = rx;
        this.ry = ry;
    }
    public String toSvg()
    {
        return String.format("<ellipse cx=\"%.5f\" cy=\"%.5f\" rx=\"%.5f\" ry=\"%.5f\" style=\"%s\" />",
                center.getX(), center.getY(),
                rx, ry,
                style.toSvg());
    }
    public BoundingBox getBounds()
    {
        return new BoundingBox(center.getX() - rx * .5f, center.getX() + rx * .5f, center.getY() - ry * .5f, center.getY() + ry * .5f);
    }
}
