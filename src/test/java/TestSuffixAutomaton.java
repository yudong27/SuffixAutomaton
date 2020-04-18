import com.yunhe.algorithm.SuffixAutomaton;

import junit.framework.TestCase;
import java.io.*;
import java.util.*;


public class TestSuffixAutomaton extends TestCase {
    public ArrayList<Character> str2Array(String s) {
        ArrayList<Character> resArray = new ArrayList<Character>();
        for(char c : s.toCharArray()){
            resArray.add(c);
        }
        return resArray;
    }

    public void testSAM() {
        SuffixAutomaton<Character> sam = new SuffixAutomaton<Character>();
        String s1 = "aabbabd";
        String s2 = "cccabbad";
        String s3 = "ddabbad";
        sam.array2State(str2Array(s1));
        sam.array2State(str2Array(s2));
        sam.debugPrint();
        ArrayList<Character> res = sam.findLongestCommonSubstr(str2Array(s3));
        System.out.println(res);
    }

}