import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        System.out.print("Hello and welcome!\n");
        Point p = new Point(1, 2);
        System.out.println(p.toSVG());
        System.out.println(p.translated(10, 4));
        p.translate(-1, -3);
        System.out.println(p);
        System.out.println(new Segment(new Point(0, 0), new Point(1, 1)).length());

        System.out.println(maxSegment(new Segment[] {
                new Segment(new Point(0, 0), new Point(1, 1)),
                new Segment(new Point(0, 0), new Point(3, 4))
        }).length());

        Polygon pol = new Polygon(new Point[] { new Point(-1, -1), new Point(1, 1), new Point(2, 0) });
        System.out.println(pol.toSvg());

        SvgScene ss = new SvgScene();
        ss.addPolygon(pol);
        ss.addPolygon(new Polygon(new Point[] {
                new Point(-2, 0),
                new Point(0, 2),
                new Point(0, -2),
                new Point(4, 0)
            },
            new Style("blue", "cyan", 0.02)
        ));
        ss.addPolygon(
                Polygon.square(new Segment(new Point(-1, 1), new Point(-2, 4)),
                new Style("#7FFF7F", "#FFFF7F", 0.1))
        );
        System.out.println(ss.toSVG());
        System.out.println(ss.boundingBox());
        try
        {
            ss.save("Test.svg");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println(new Style("red", "orange", 0.2).toSvg());
    }
    public static Segment maxSegment(Segment[] arr)
    {
        if (arr.length == 0) return null;
        Segment mx = arr[0];
        for (int i = 1; i < arr.length; i++)
        {
            if (mx.length() < arr[i].length()) mx = arr[i];
        }
        return mx;
    }
}