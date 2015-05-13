package lv.mrusk.neuedahomework.databuilder;

import lv.mrusk.neuedahomework.model.mindmap.Node;
import org.apache.commons.lang3.builder.Builder;

import java.util.List;

public class NodeBuilder implements Builder<Node> {

    private String text;
    private List<Node> childNodes;

    private NodeBuilder() {
    }

    @Override
    public Node build() {
        Node node = new Node();
        node.setText(text);
        node.setChildNodes(childNodes);
        return node;
    }

    public static NodeBuilder aNode() {
        return new NodeBuilder();
    }

    public NodeBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public NodeBuilder withChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
        return this;
    }
}
