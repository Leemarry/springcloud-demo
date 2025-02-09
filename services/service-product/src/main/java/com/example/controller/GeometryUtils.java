public class GeometryUtils {

    // 定义一个点类
    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // 判断点是否在多边形内（射线投射法）
    public static boolean isPointInPolygon(Point point, Point[] polygon) {
        int n = polygon.length;
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            if (((polygon[i].y <= point.y && point.y < polygon[j].y) ||
                    (polygon[j].y <= point.y && point.y < polygon[i].y)) &&
                    (point.x < (polygon[j].x - polygon[i].x) * (point.y - polygon[i].y) /
                            (polygon[j].y - polygon[i].y) + polygon[i].x)) {
                inside = !inside;
            }
        }

        return inside;
    }

    // 计算两条线段的交点（如果存在）
    // 返回值：如果相交，则返回交点的x和y坐标；如果不相交，则返回null
    public static Point getSegmentIntersection(Point p1, Point q1, Point p2, Point q2) {
        double d = (q1.y - p1.y) * (p2.x - q2.x) - (q1.x - p1.x) * (p2.y - q2.y);

        if (d == 0) {
            // 线段平行或共线
            return null;
        }

        double t = ((p1.x - p2.x) * (p2.y - q2.y) - (p1.y - p2.y) * (p2.x - q2.x)) / d;
        double u = ((p1.x - q1.x) * (p1.y - q2.y) - (p1.y - q1.y) * (p1.x - q2.x)) / d;

        if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {
            // 交点在线段上
            return new Point(
                    p1.x + t * (q1.x - p1.x),
                    p1.y + t * (q1.y - p1.y)
            );
        }

        // 交点不在线段上
        return null;
    }

    // 判断两条线段是否相交
    public static boolean doSegmentsIntersect(Point p1, Point q1, Point p2, Point q2) {
        return getSegmentIntersection(p1, q1, p2, q2) != null;
    }

    // 判断线段是否有部分在多边形内
    public static boolean segmentCrossesPolygon(Point lineStart, Point lineEnd, Point[] polygon) {
        // 首先检查线段的两个端点是否在多边形内
        boolean startInside = isPointInPolygon(lineStart, polygon);
        boolean endInside = isPointInPolygon(lineEnd, polygon);

        if (startInside && endInside) {
            // 线段两个端点都在多边形内，因此整个线段都在多边形内
            return true;
        } else if (startInside || endInside) {
            // 线段一个端点在多边形内，检查线段是否与多边形边相交
            for (int i = 0; i < polygon.length; i++) {
                Point p1 = polygon[i];
                Point p2 = polygon[(i + 1) % polygon.length];
                if (doSegmentsIntersect(lineStart, lineEnd, p1, p2)) {
                    return true;
                }
            }
        } else {
            // 线段两个端点都不在多边形内，但还需要检查线段是否与多边形边相交（理论上这一步是多余的，因为上面的逻辑已经涵盖了）
            // 但为了代码的完整性，这里还是保留了这个检查
            for (int i = 0; i < polygon.length; i++) {
                Point p1 = polygon[i];
                Point p2 = polygon[(i + 1) % polygon.length];
                if (doSegmentsIntersect(lineStart, lineEnd, p1, p2)) {
                    // 理论上，如果相交，则直接返回true即可，因为题目只要求判断是否有部分相交
                    // 但为了严谨性，这里不再添加额外的判断逻辑
                    return true;
                }
            }
        }

        // 如果以上条件都不满足，则线段没有部分在多边形内
        return false;
    }

    public static void main(String[] args) {
        // 示例多边形（顺时针或逆时针顺序）
        Point[] polygon = {
                new Point(0, 0),
                new Point(4, 0),
                new Point(4, 4),
                new Point(0, 3)
        };

        // 示例线段
        Point lineStart = new Point(5, 0);
        Point lineEnd = new Point(0, 5); // 这个点在多边形外，但线段穿越了多边形

        // 判断线段是否有部分在多边形内
        boolean crosses = segmentCrossesPolygon(lineStart, lineEnd, polygon);
        System.out.println("线段是否有部分在多边形内: " + crosses);
    }
}