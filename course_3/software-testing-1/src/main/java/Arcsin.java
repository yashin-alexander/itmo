public class Arcsin {

    private final static double ESP = 1.0e-10;
    private final static double INF = 1.0e8;

    public static double factorial(int n) {
        double res = 1.0;
        for (int i = n; i >= 1; i -= 2)
            res *= i;
        return res;
    }

    public static double getFunctionValue(double x) {
        double currentSum = 0, previousSum;
        int n = 1;
        if (Math.abs(x) == 1) return x * Math.PI/2;
        if (Math.abs(x) > 1) return INF;
        do {
            previousSum = currentSum;
            currentSum += (factorial(2 * n - 1) * Math.pow(x, 2 * n + 1))
                        / (factorial(2 * n) * (2 * n + 1));
            n++;
        } while ((Math.abs(currentSum - previousSum)) > ESP);
        return currentSum + x;
    }
}
