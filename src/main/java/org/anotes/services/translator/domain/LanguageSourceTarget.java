package org.anotes.services.translator.domain;


/**
 * User: gmc
 * Date: 12/05/11
 */
public class LanguageSourceTarget {
    private Language source;
    private Language target;

    public LanguageSourceTarget(Language source, Language target) {
        this.source = source;
        this.target = target;
    }

    public Language getSource() {
        return source;
    }

    public void setSource(Language source) {
        this.source = source;
    }

    public Language getTarget() {
        return target;
    }

    public void setTarget(Language target) {
        this.target = target;
    }

    public boolean sourceAndTargeAreEquals() {
        return source.equals(target);
    }

    public String getSourceAsStr() {
        return source.asStr();
    }

    public String getTargetAsStr() {
        return target.asStr();
    }
}
