public class Perceptron {
    private float[] weights;
    private float bias;
    private float bias_weight;
    public float lr;
    public int iterations = 0;

    // sets random weights
    public Perceptron(float _lr) {
        weights = new float[2];
        for (int x=0; x<weights.length; x++) {
            weights[x] = (float)Math.random() * (5+5+1) - 5; // * (max-min + 1) + min
        }
        bias = 1; bias_weight = (float)Math.random() * (5+5+1) - 5;
        lr = _lr;
    }


    
    // calculate the difference between the right answer and the
    // guess, then adjust the weights
    public void train(float[] inputs, float target) {
        float guess = guess(inputs);
        float error = target - guess;

        Guesses.errors.add(error); // plotting purposes

        for (int i=0; i<weights.length; i++) { // learning algorithm
            weights[i] += error * inputs[i] * lr;
        }
        bias_weight += error * 1 * lr;
    }
    private float guess(float[] inputs) {
        float guess = 0;
        for (int x=0; x<inputs.length; x++) { // weighted sum
            guess += inputs[x] * weights[x];
        }
        guess += bias * bias_weight;
        return sigmoid(guess);
    }
    private float sigmoid(float x) {
        float sigmoid_coefficient = 1.0f; // arbitrarly chosen
        return (float)(1 / (1 + Math.exp(-(sigmoid_coefficient * x))));
    }



    public float test(float[] inputs) {
        float guess = 0;
        for (int x=0; x<inputs.length; x++) {
            guess += inputs[x] * weights[x];
        }
        guess += bias * bias_weight;
        return activation_function(guess);
    }
    private float activation_function(float x) {
        if (x > 0) { return 1; }
        else { return 0; }
    }



    public int line_guess(float x) {
        float m = -(weights[0] / weights[1]);
        float q = -(bias_weight / weights[1]);
        return (int)(m * x + q);
    }
}