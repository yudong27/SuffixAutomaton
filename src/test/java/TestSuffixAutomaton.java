import com.yunhe.algorithm.*;
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

    private ArrayList<String> loadText(String path) throws IOException
    {
        ArrayList<String> sbText = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(path), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null)
        {
            sbText.add(line);
        }
        br.close();

        return sbText;
    }

    public void notestSAM() throws IOException {
        SuffixAutomaton<String> sam = new SuffixAutomaton<String>();
        ArrayList<String> text = loadText("zhihu_title.txt");
        int max_line = 1000;
        for (int i=0;i<text.size();i++) {
            //System.out.println(text.get(i));
            ArrayList<String> py = CharToPinyin.getPinYin(text.get(i));
            sam.array2State(py);
            if (i>max_line) {
                break;
            } 
        }
        for (int i=0;i<text.size();i++) {
            ArrayList<String> py = CharToPinyin.getPinYin(text.get(i));
            sam.addEndpos(py);
            if (i>max_line) {
                break;
            } 
        }
        //String s1 = "aabb";
        //String s2 = "cababd";
        //String s3 = "ddbabdc";
        //sam.array2State(str2Array(s1));
        //sam.array2State(str2Array(s2));
        //sam.addEndpos(str2Array(s1));
        //sam.addEndpos(str2Array(s2));
        //sam.debugPrint();
        String s3 = "学习阿拉伯语";
        ArrayList<String> res = sam.findLongestCommonSubstr(CharToPinyin.getPinYin(s3));
        System.out.println(res);
        //System.out.println(CharToPinyin.getPinYin("怎么掌握java编程基础"));
    }

    public void testSAM1() throws IOException {
        SuffixAutomaton<Character> sam = new SuffixAutomaton<Character>();
        String s1 = "aabb";
        //String s2 = "cababd";
        //String s3 = "ddbabdc";
        sam.array2State(str2Array(s1));
        //sam.array2State(str2Array(s2));
        //sam.debugPrint();
    }

}