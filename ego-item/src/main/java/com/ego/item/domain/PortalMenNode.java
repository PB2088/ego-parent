package com.ego.item.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 每个节点类型
 * @author boge.peng
 * @create 2019-03-13 17:09
 */
public class PortalMenNode implements Serializable {

    private static final long serialVersionUID = -4086591670213886810L;

    private String u;

    private String n;

    private List<Object> i;

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public List<Object> getI() {
        return i;
    }

    public void setI(List<Object> i) {
        this.i = i;
    }
}
