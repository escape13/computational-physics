import org.opensourcephysics.controls.AbstractCalculation;
import org.opensourcephysics.frames.LatticeFrame;

import java.awt.*;

public class OneDimensionalAutomationApp extends AbstractCalculation {

    LatticeFrame automation = new LatticeFrame("");
    int[] update = new int[8];

    public void calculate() {
        control.clearMessages();
        int L = control.getInt("Linear dimension");
        int tmax = control.getInt("Maximum time");
        automation.resizeLattice(L, tmax);
        automation.setValue(L / 2, 0, 1);
        automation.setIndexedColor(0, Color.YELLOW);
        automation.setIndexedColor(1, Color.BLUE);
        setRule(control.getInt("Rule number"));
        for(int t = 1; t < tmax; ++t) {
            iterate(t, L);
        }
    }

    public void iterate(int t, int L) {
        for(int i = 0; i < L; ++i) {
            int left = automation.getValue((i-1 + L)%L, t - 1);
            int center = automation.getValue(i, t - 1);
            int right = automation.getValue((i+1)%L, t - 1);

        }
    }

    public void reset() {

    }

    public static void main(String[] args) {

    }
}
