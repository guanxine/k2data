package cn.gx.entity;

/**
 * Created by guanxine on 16-4-16.
 */
public class Link {

    private String href;
    private Rel rel=Rel.self;

    public Link() {
    }

    public Link(String href, Rel rel) {
        this.href = href;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Rel getRel() {
        return rel;
    }

    public void setRel(Rel rel) {
        this.rel = rel;
    }
}
