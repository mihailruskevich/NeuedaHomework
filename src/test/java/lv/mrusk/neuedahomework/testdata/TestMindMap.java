package lv.mrusk.neuedahomework.testdata;

import lv.mrusk.neuedahomework.model.mindmap.MindMap;

public class TestMindMap {

    public static MindMap aRegularMindMap(){
        return new MindMap(TestNode.aRegularTestBundleNode());
    }

    public static MindMap aMindMapWithoutTestSuites(){
        return new MindMap(TestNode.aTestBundleNodeWithoutChildNodes());
    }
}
