package com.ego.commons.domain;

import java.io.Serializable;

/**
 * @author boge.peng
 * @create 2019-03-12 13:28
 */
public class EasyUITree implements Serializable {

    private static final long serialVersionUID = -2262630638708000898L;

    /** 节点的 id，它对于加载远程数据很重要。 */
    private long id;

    /** 要显示的节点文本 */
    private String text;

    /** 节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。 */
    private String state;

    public EasyUITree() {}

    public EasyUITree(long id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
