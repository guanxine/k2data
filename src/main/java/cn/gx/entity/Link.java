package cn.gx.entity;

/**
 * Created by guanxine on 16-4-16.
 */
public class Link {
    private Rel rel=Rel.self;
    private String href;


    public Link() {
    }

    public Link(String href, Rel rel) {
        this.href = href;
        this.rel = rel;
    }

    public Link(String href) {
        this.href=href;
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
