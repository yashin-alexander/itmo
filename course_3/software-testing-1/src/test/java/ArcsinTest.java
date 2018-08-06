import org.junit.Test;
import static org.junit.Assert.*;

public class ArcsinTest {

    private final static double INF = 1.0e8;
    private final static double ESP = 1.0e-5;

    private boolean isCoincideEsp(double trueFunctionValue, double myFunctionValue) {
        return Math.abs(trueFunctionValue - myFunctionValue) < ESP;
    }

    @Test
    public void testArcsin1() {
        assertTrue("arcsin(-1) = -PI/2", isCoincideEsp(Math.asin(-1), Arcsin.getFunctionValue(-1)));
    }

    @Test
    public void testArcsin2() {
        assertTrue("arcsin(1) = PI/2", isCoincideEsp(Math.asin(1), Arcsin.getFunctionValue(1)));
    }

    @Test
    public void testArcsin3() {
        assertTrue("arcsin(0) = 0", isCoincideEsp(Math.asin(0), Arcsin.getFunctionValue(0)));
    }

    @Test
    public void testArcsin4() {
        assertTrue("arcsin(2) = undef", isCoincideEsp(INF, Arcsin.getFunctionValue(2)));
    }

    @Test
    public void testArcsin5() {
        assertTrue("arcsin(-2) = undef", isCoincideEsp(INF, Arcsin.getFunctionValue(-2)));
    }

    @Test
    public void testArcsin6() {
        assertTrue("arcsin(1/2) = PI/6", isCoincideEsp(Math.asin(0.5), Arcsin.getFunctionValue(0.5)));
    }

    @Test
    public void testArcsin7() {
        assertTrue("arcsin(-1/2) = -PI/6", isCoincideEsp(Math.asin(-0.5), Arcsin.getFunctionValue(-0.5)));
    }
}
