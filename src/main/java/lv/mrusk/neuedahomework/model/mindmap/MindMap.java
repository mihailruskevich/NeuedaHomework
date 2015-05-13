package lv.mrusk.neuedahomework.model.mindmap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "map")
@XmlAccessorType(XmlAccessType.FIELD)
public class MindMap {

    public MindMap() {
    }

    public MindMap(Node node) {
        this.node = node;
    }

    @XmlElement(required = true)
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node value) {
        this.node = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("node", node)
                .toString();
    }
}