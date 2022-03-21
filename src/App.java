import javax.swing.JFrame;
import java.util.ArrayList;
//import java.io.*; // file handling

public class App {
    public static void main(String[] args) throws Exception {
        // instantiate the perceptron and the window to display the data
        Perceptron perceptron = new Perceptron(.1f);

        JFrame frame = new JFrame("Perceptron training");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BuildData data = new BuildData();
        frame.add(data);
        frame.setSize(300,300);
        frame.setVisible(true);        
        
        
        Point[] pts = data.getData();
        for (;;) {
            Thread.sleep(500);
            for (Point pt : pts) {                          // TRAINING SESSION
                float[] inputs = {pt.x, pt.y};
                float target = pt.label;
                perceptron.train(inputs, target);
            }
            for (Point pt : pts) {                          // TESTING SESSION
                float[] inputs = {pt.x, pt.y};
    
                float output = perceptron.test(inputs);
                
                Guesses.guesses.add(output);
                Guesses.targets.add(pt.label);
            }
            int line_guess_x1 = -1; int line_guess_y1 = perceptron.line_guess(line_guess_x1);
            int line_guess_x2 = 300; int line_guess_y2 = perceptron.line_guess(line_guess_x2);
            Guesses.line_guesses_x.add(line_guess_x1);
            Guesses.line_guesses_x.add(line_guess_x2);
            Guesses.line_guesses_y.add(line_guess_y1);
            Guesses.line_guesses_y.add(line_guess_y2);

            perceptron.iterations += 1;
            //store_data(Guesses.errors, perceptron.iterations);

            System.out.println(perceptron.iterations + " - " + perceptron.lr + " - " + make_mean(Guesses.errors));
            Guesses.errors.clear();

            frame.repaint(); // update the data visualization
        }
    }

    /*
    static void store_data(ArrayList<Float> _errors, float _iterations) {
        File file = new File("C:\\Users\\filip\\projects\\Java Programs\\Perceptron Training\\Perceptrons\\plot_data.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(_iterations + "\t" + make_mean(_errors) + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("File handling failed.");
            e.printStackTrace();
        }
    }
    */
    static String make_mean(ArrayList<Float> list) {
        float elem_sum = 0;
        for (Float elem : list) {
            elem_sum += elem;
        }
        Float mean = elem_sum / list.size();
        String text = Float.toString(mean);
        return text;
    }
}
