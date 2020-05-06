package com.yunhe.algorithm;

import java.util.*;


public class SparseState {
    private int[] indices;
    private int[] values;
    private int len;
    private int capacity;

    public SparseState(int capacity, boolean fill) {
        //System.out.println("capacity="+capacity);
        this.capacity = capacity;
        this.indices = new int[capacity];
        this.values = new int[capacity];
        if (fill==true) {
            for (int i=0;i<capacity;i++) {
                this.append(i, 0);
            }
        }
    }

    public void append(int indx, int val) {
        this.indices[this.len] = indx;
        this.values[this.len] = val;
        this.len += 1;
    }

    public int get_len() {
        return this.len;
    }

    public int get_index(int idx) throws Exception {
        if (idx >= this.len) {
            throw new Exception("index is larger than length");
        }
        return this.indices[idx];
    }

    public int get_value(int idx) throws Exception {
        if (idx >= this.len) {
            throw new Exception("value is larger than length"+idx);
        }
        return this.values[idx];
    }

    public int get_last_index() throws Exception {
        if (this.len == 0) {
            throw new Exception("No data in index");
        }
        return this.indices[this.len-1];
    }

    public int get_last_value() throws Exception {
        if (this.len == 0) {
            throw new Exception("No data in value");
        }
        return this.values[this.len-1];
    }

    public boolean is_first_valid(int max_edit) {
        if (this.len > 0 && this.indices[0] == 0 && this.values[0] < max_edit) {
            return true;
        }
        return false;
    }

    public boolean is_match() {
        if (this.len>0 && this.indices[this.len-1] == this.capacity-1) {
            return true;
        }
        return false;
    }

    public boolean can_match() {
        if (this.len > 0) {
            return true;
        }
        return false;
    }

    public void debugPrint() {
        System.out.println("=================================");
        System.out.println("len="+this.len);
        for (int i=0;i<this.len;i++) {
            System.out.println("idx="+this.indices[i]+" val="+this.values[i]);
        }
    }
}