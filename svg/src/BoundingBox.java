public class BoundingBox
{
    public float minX, maxX, minY, maxY;
    public BoundingBox() { }
    public BoundingBox(float minX, float maxX, float minY, float maxY)
    {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    public void encapsulate(BoundingBox b)
    {
        minX = Math.min(minX, b.minX);
        maxX = Math.max(maxX, b.maxX);
        minY = Math.min(minY, b.minY);
        maxY = Math.max(maxY, b.maxY);
    }
    public String toString()
    {
        return "{" + minX + "," + minY + "; " + maxX + "," + maxY + "}";
    }
}
