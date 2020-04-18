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

    public State()
    {
        this(0,0,null,null);
    }

    public State(int maxlen, 
                 int minlen, 
                 Map<V, State<V>> success, 
                 State<V> failure) {
        this.maxlen = maxlen;
        this.minlen = minlen;
        this.index = indexinr;
        indexinr += 1;
        if (success != null && success.size() !=0 ) {
            Iterator iter = success.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                V k = (V) entry.getKey();
                State<V> v = (State<V>) entry.getValue();
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

    public String isFailureNull() {
        if (this.failure == null) {
            return "NULL";
        } else {
            return ""+this.failure.index;
        }
    }

    public void debugPrint() {
        System.out.println("=============================");
        System.out.println("index = "+this.index);
        System.out.println("maxlen = "+this.maxlen);
        System.out.println("minlen = "+this.minlen);
        System.out.println("failure.index = " + this.isFailureNull());
        Iterator iter = success.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            V k = (V) entry.getKey();
            State v = (State) entry.getValue();
            System.out.println("tran_code: "+k+" State: "+v.index);
        }
    }
}