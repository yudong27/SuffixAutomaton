package com.yunhe.algorithm;

import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SuffixAutomaton<V> {
    State<V> root = new State<V>();
    State<V> last = null;
    protected int strnum = 0;
    protected ArrayList<ArrayList<V>> arrays = new ArrayList<ArrayList<V>>();

    
    public State<V> findSameVStateParent(State<V> currentState, V ch, State<V> newState) {
        while (currentState != null && 
               currentState.success.get(ch) == null) {
            currentState.success.put(ch, newState);
            currentState = currentState.failure;
        }
        return currentState;
    }
    public State<V> addState(State<V> laststate, V ch) {
        //System.out.println("current char: "+ch);
        State<V> newState = new State<V>(laststate.maxlen+1, -1, null, null);
        State<V> sameVStateParent = findSameVStateParent(laststate, ch, newState);
        if (sameVStateParent == null) {
            newState.minlen = 1;
            newState.failure = this.root;
            return newState;
        }

        State<V> sameVState = sameVStateParent.success.get(ch);
        if (sameVStateParent.maxlen + 1 == sameVState.maxlen) {
            newState.minlen = sameVState.maxlen + 1;
            newState.failure = sameVState;
            return newState;
        }

        State<V> dupState = new State<V>(sameVStateParent.maxlen+1, -1, sameVState.success, sameVState.failure);
        dupState.failure = sameVState.failure;
        sameVState.minlen = dupState.maxlen + 1;
        sameVState.failure = dupState;
        newState.minlen = dupState.maxlen + 1;
        newState.failure = dupState;
        State<V> currentState = sameVStateParent;
        //System.out.println("currentState.index = " +currentState.index);
        //System.out.println("currentState[ch].index = " +currentState.success.get(ch).index);
        while (currentState != null && 
               currentState.success.get(ch) == sameVState) {
            //System.out.println("currentState.index = "+currentState.index);
            currentState.success.put(ch, dupState);
            currentState = currentState.failure;
        }
        dupState.minlen = dupState.failure.maxlen + 1;
        return newState;
    }


    public void addEndpos(ArrayList<V> vArrayList) {
        State<V> pt = this.root;
        for (int i =0; i<= vArrayList.size(); i++) {
            State<V> ct = pt;
            while (ct != null) {
                ct.addEndpos(this.strnum, i);
                ct = ct.failure;
            }
            if (i< vArrayList.size()) {
                pt = pt.getNextState(vArrayList.get(i));
            }
        }
        this.strnum += 1;
    }

    public void array2State(ArrayList<V> vArrayList) {
        this.arrays.add(vArrayList);
        this.last = root;
        Iterator<V> it = vArrayList.iterator();
        while (it.hasNext()) {
            this.last = addState(this.last, it.next());
        }
    }


    public ArrayList<V> findLongestCommonSubstr(ArrayList<V> mArrayList) {
        State<V> currentState = this.root;
        State<V> resState = this.root;
        int currentlen = 0;
        int maxlen = 0;
        int maxendpos = 0;
        for (int i=0; i<mArrayList.size(); i++) {
            V ch = mArrayList.get(i);
            while (currentState != null && currentState.success.get(ch) == null) {
                currentState = currentState.failure;
                if (currentState != null) {
                    currentlen = currentState.maxlen;
                }
            }
            if (currentState == null) {
                currentState = this.root;
                currentlen = 0;
                continue;
            }
            currentState = currentState.success.get(ch);
            currentlen += 1;
            if (currentlen > maxlen) {
                maxlen = currentlen;
                maxendpos = i;
                resState = currentState;
            }
        }
        ArrayList<V> resArray = new ArrayList<V>();
        for (int i=0; i<mArrayList.size();i++) {
            if (i>maxendpos-maxlen && i<=maxendpos) {
                resArray.add(mArrayList.get(i));
            }
        }
        System.out.println("maxendpos = "+ maxendpos + " maxlen="+maxlen);
        resState.debugPrint();
        return resArray;
    }

    public void debugPrint(State<V> state, Set<Integer> used_index) {
        state.debugPrint();
        Iterator iter = state.success.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            //V k = (V) entry.getKey();
            State<V> v = (State<V>) entry.getValue();
            if (!used_index.contains(v.index)) {
                used_index.add(v.index);
                debugPrint(v, used_index);
            }
        }
    }

    public void debugPrint() {
        Set<Integer> used_index = new HashSet<Integer>();
        debugPrint(this.root, used_index);
        System.out.println("=============================");
        for (int i=0;i<arrays.size();i++) {
            System.out.println(""+i+": "+arrays.get(i));
        }
        System.out.println("\n");
    }
}