public class Point {
    float x, y;
    float label;
    Line line = new Line();
    Point(float _x, float _y) {
        x = _x;
        y = _y;
        // classification criteria
        if (y > Line.f(x)) {
            label = 1.0f;
        } else {
            label = 0.0f;
        }
    }
}