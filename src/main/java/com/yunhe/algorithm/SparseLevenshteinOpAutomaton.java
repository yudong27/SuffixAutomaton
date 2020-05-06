package com.yunhe.algorithm;

import java.util.*;


public class SparseLevenshteinOpAutomaton {
    private String str;
    private int max_edit = 0;
    private int max_length = 50;

    public SparseLevenshteinOpAutomaton(String s, int maxedit, int maxlen) {
        this.str = s;
        this.max_edit = maxedit;
        this.max_length = maxlen;
        if (this.max_length < this.str.length()) {
            System.out.println("Too long");
        }
    }

    public SparseLevenshteinOpAutomaton(String s, int maxedit) {
        this.str = s;
        this.max_edit = maxedit;
        if (this.max_length < this.str.length()) {
            System.out.println("Too long");
        }
    }

    public SparseState init_state() {
        SparseState state = new SparseState(this.str.length()+1, true);
        return state;
    }

    public SparseState step(SparseState state, char c) throws Exception{
        //System.out.println("c="+c);
        //state.debugPrint();
        SparseState new_state = new SparseState(this.str.length()+1, false);
        if (state.is_first_valid(this.max_edit)) {
            new_state.append(0, state.get_value(0)+1);
        }
        for (int j=0; j<state.get_len();j++) {
            int i = state.get_index(j);
            if (i == this.str.length()) {
                break;
            }
            int cost = 0 ;
            if (this.str.charAt(i) != c) {
                cost = 1;
            }
            int val = state.get_value(j) + cost;
            //System.out.println("j="+j+" i="+i+" val="+val+" cost="+cost);
            if (new_state.get_len()>0 && new_state.get_last_index() == i) {
                val = Math.min(val, new_state.get_last_value()+1);
            }
            //System.out.println("j="+j+" i="+i+" val="+val+" cost="+cost);
            if (j+1 < state.get_len() && state.get_index(j+1) == i+1) {
                //System.out.println("j+1="+(j+1)+" "+new_state.get_len());
                val = Math.min(val, state.get_value(j+1)+1);
            }
            //System.out.println("j="+j+" i="+i+" val="+val+" cost="+cost);
            if (val <= this.max_edit) {
                new_state.append(i+1, val);
            }
        }

        return new_state;
    }

    public HashSet<Character> transitions(SparseState state) throws Exception {
        HashSet<Character> chars = new HashSet<Character>();
        for (int i=0;i<state.get_len();i++) {
            int j = state.get_index(i);
            if (j<this.str.length()) {
                chars.add(this.str.charAt(j));
            }
        }
        return chars;
    }
}