package com.example.utils;

import java.util.Arrays;
import java.util.List;

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class LineSegment {
    Point p1, p2;

    public LineSegment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}

class Polygon {
    List<Point> vertices;

    public Polygon(List<Point> vertices) {
        this.vertices = vertices;
    }

    // Check if a point is inside the polygon
    public boolean contains(Point p) {
        int n = vertices.size();
        boolean inside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            Point v1 = vertices.get(i);
            Point v2 = vertices.get(j);

            // Check if the point is in between the y-coordinates
            if ((v1.y > p.y) != (v2.y > p.y) &&
                    (p.x < (v2.x - v1.x) * (p.y - v1.y) / (v2.y - v1.y) + v1.x)) {
                inside = !inside;
            }
        }

        return inside;
    }
}

public class Main {
    public static void main(String[] args) {
        // Define the polygon's vertices
        Polygon polygon = new Polygon(Arrays.asList(
                new Point(5, 0),
                new Point(5, 5),
                new Point(0, 5),
                new Point(0, 0)
        ));

        // Create a line segment
        LineSegment line = new LineSegment(new Point(4, 4), new Point(0, 9));

        // Check if either endpoint of the line segment is inside the polygon
        if (polygon.contains(line.p1) || polygon.contains(line.p2)) {
            System.out.println("线段的端点在多边形内");
        } else {
            System.out.println("线段的端点不在多边形内");
        }
    }
}