import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class BuildData extends JPanel {
    private static final long serialVersionUID = 1L; // idk... the program yelled at me without it
    public Point[] pts = new Point[500];

    // before drawing the data, it builds it in another function
    // so the data is accessible from outside the class
    public Point[] getData() {
        for (int i=0; i<pts.length; i++) 
        {
            float x = (float)Math.random() * (300-0+1) + 0;
            float y = (float)Math.random() * (300-0+1) + 0;

            Point pt = new Point(x, y);

            pts[i] = pt;
        }
        return pts;
    }

    // painComponent (called automatically by idk who)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        // draws points and line
        float x1 = -1;           float x2 = 300;
        float y1 = Line.f(x1);   float y2 = Line.f(x2);
        g.drawLine((int)x1,(int)y1, (int)x2,(int)y2);

        for (Point p : pts) {
            if (p.label == 1.0f) {
                g.fillOval((int)p.x, (int)p.y, 7,7);
            } else {
                g.drawOval((int)p.x, (int)p.y, 7,7);
            }
        }
        if (Guesses.guesses.size() > 0) {
            for (int i=0; i<Guesses.guesses.size(); i++) {
                // idk... apperently the comparison between Floats doesn't work normally
                // anyway: if the answer is right (difference ans-guess == 0) then colors
                // the oval in green, otherwise red.
                if (Float.compare(Guesses.guesses.get(i), Guesses.targets.get(i)) == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.RED);
                }
                g.drawOval((int)pts[i].x, (int)pts[i].y, 7,7);
            }
            g.setColor(Color.MAGENTA);
            g.drawLine((int)Guesses.line_guesses_x.get(0), (int)Guesses.line_guesses_y.get(0), (int)Guesses.line_guesses_x.get(1), (int)Guesses.line_guesses_y.get(1));
            // because it is an ArrayList and the program only adds values to them,
            // after every "learning iteration" of the perceptron, clears the values
            Guesses.guesses.clear();
            Guesses.targets.clear();
            Guesses.line_guesses_x.clear();
            Guesses.line_guesses_y.clear();
        }
    }
}
