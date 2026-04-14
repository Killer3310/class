import java.io.FileWriter;
import java.io.IOException;

public class SvgScene
{
    private static final int shapesMax = 4;
    private Polygon[] shapes = new Polygon[shapesMax];
    private int head = 0;
    public void addPolygon(Polygon p)
    {
        shapes[head++] = new Polygon(p);
        head %= shapesMax;
    }
    public String toSVG()
    {
        String out = "";
        if (shapes[0] != null) out = shapes[0].toSvg();
        if (shapes[1] != null) out += '\n' + shapes[1].toSvg();
        if (shapes[2] != null) out += '\n' + shapes[2].toSvg();
        return out;
    }
    public BoundingBox boundingBox()
    {
        int len = shapes[shapesMax - 1] == null ? head : shapesMax;
        BoundingBox b = new BoundingBox();
        for (int i = 0; i < len; i++)
        {
            b.encapsulate(shapes[i].getBounds());
        }
        return b;
    }
    public void save(String path) throws IOException {
        BoundingBox bb = boundingBox();
        float ext = Math.max(Math.max(-bb.minX, bb.maxX), Math.max(-bb.minY, bb.maxY));
        StringBuilder out = new StringBuilder(
                String.format("<svg width=\"1000\" height=\"1000\" viewBox=\"%f %f %f %f\" xmlns=\"http://www.w3.org/2000/svg\">", -ext, -ext, ext * 2, ext * 2));

        out.append("\n<circle fill=\"aliceblue\" cx=\"0\" cy=\"0\" r=\"999\" />");
        int len = shapes[2] == null ? head : 3;
        for (int i = 0; i < len; i++)
        {
            out.append('\n').append(shapes[i].toSvg());
        }
        FileWriter fw = new FileWriter(path, false);
        fw.write(out + "\n</svg>\n");
        fw.close();
    }
}
