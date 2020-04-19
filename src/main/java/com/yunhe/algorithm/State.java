package com.yunhe.algorithm;

import java.util.*;

public class State<V> {
    protected int maxlen;
    protected int minlen;
    static int indexinr = 0;
    protected int index;
    protected boolean suffix = false;
    protected Map<V, State<V>> success = new TreeMap<V, State<V>>();
    protected State<V> failure = null;
    protected Map<Integer, HashSet<Integer>> endpos = new HashMap<Integer,HashSet<Integer>>();

    public State()
    {
        this(0,0,null,null);
    }

    public State(final int maxlen, final int minlen, final Map<V, State<V>> success, final State<V> failure) {
        this.maxlen = maxlen;
        this.minlen = minlen;
        this.index = indexinr;
        indexinr += 1;
        if (success != null && success.size() != 0) {
            final Iterator iter = success.entrySet().iterator();
            while (iter.hasNext()) {
                final Map.Entry entry = (Map.Entry) iter.next();
                final V k = (V) entry.getKey();
                final State<V> v = (State<V>) entry.getValue();
                this.success.put(k, v);
            }
        }
        if (failure != null) {
            this.failure = failure;
        }
    }

    public void setSuffix() {
        this.suffix = true;
    }

    public State<V> getNextState(V ch) {
        return this.success.get(ch);
    }

    public String isFailureNull() {
        if (this.failure == null) {
            return "NULL";
        } else {
            return "" + this.failure.index;
        }
    }

    public void addEndpos(final int strid, final int pos) {
        if (endpos.get(strid) == null) {
            final HashSet<Integer> new_set = new HashSet<Integer>();
            endpos.put(strid, new_set);
        }
        endpos.get(strid).add(pos);
    }

    public void debugPrint() {
        System.out.println("=============================");
        System.out.println("index = " + this.index);
        System.out.println("maxlen = " + this.maxlen);
        System.out.println("minlen = " + this.minlen);
        System.out.println("failure.index = " + this.isFailureNull());
        final Iterator iter = success.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry entry = (Map.Entry) iter.next();
            final V k = (V) entry.getKey();
            final State v = (State) entry.getValue();
            System.out.println("tran_code: "+k+" State: "+v.index);
        }

        final Iterator iter1 = endpos.entrySet().iterator();
        while (iter1.hasNext()) {
            final Map.Entry entry = (Map.Entry) iter1.next();
            final Integer k = (Integer) entry.getKey();
            final HashSet<Integer> v = (HashSet<Integer>) entry.getValue();
            System.out.println("strid: "+k+" endpos: "+v);
        }
    }
}