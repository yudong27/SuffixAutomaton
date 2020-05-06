import com.yunhe.algorithm.*;


import junit.framework.TestCase;
import java.io.*;
import java.util.*;

public class TestSparseLevenshteinOpAutomaton extends TestCase {
    public void testSLOA()  {
        SparseLevenshteinOpAutomaton slev = new SparseLevenshteinOpAutomaton("jcfjggfekikebjhjjbdajejkfdgbhedicbkgdfhcdjdicfjead", 1);
        SparseState state = slev.init_state();
        String s = "jaj";
        for (int i = 0; i < s.length(); i++) {
            try {
                state = slev.step(state, s.charAt(i));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            state.debugPrint();
        }
    }
}