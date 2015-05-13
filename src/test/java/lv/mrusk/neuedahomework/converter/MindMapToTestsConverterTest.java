package lv.mrusk.neuedahomework.converter;

import lv.mrusk.neuedahomework.exception.MindMapFormatException;
import lv.mrusk.neuedahomework.model.tests.Parameter;
import lv.mrusk.neuedahomework.model.tests.TestBundle;
import lv.mrusk.neuedahomework.model.tests.TestCase;
import lv.mrusk.neuedahomework.testdata.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MindMapToTestsConverterTest {

    private MindMapToTestsConverter mindMapToTestsConverter = new MindMapToTestsConverter();

    @Test
    public void parseNodeText_ShouldReturnParameterForTextWithCorrectFormat() {
        Parameter expected = TestParameter.aRegularAParameter();
        Parameter actual = mindMapToTestsConverter.parseNodeText("a: 1");
        assertEquals(expected, actual);
    }

    @Test(expected = MindMapFormatException.class)
    public void parseNodeText_ShouldThrowMindMapFormatExceptionForTextWithIncorrectFormat() {
        mindMapToTestsConverter.parseNodeText("a: ");
    }

    @Test
    public void createTestsFromMindMap_ShouldCorrectlyConvertMindMapToTestBundle() {
        TestBundle expected = TestTestBundle.aRegularTestBundle();
        TestBundle actual = mindMapToTestsConverter.createTestsFromMindMap(TestMindMap.aRegularMindMap());
        assertEquals(expected, actual);
    }

    @Test
    public void createTestsFromMindMap_ShouldCorrectlyConvertMindMapWithoutTestSuites() {
        TestBundle expected = TestTestBundle.aTestBundleWithoutTestSuiteList();
        TestBundle actual = mindMapToTestsConverter.createTestsFromMindMap(TestMindMap.aMindMapWithoutTestSuites());
        assertEquals(expected, actual);
    }

    @Test(expected = MindMapFormatException.class)
    public void createTestSuite_ShouldThrowMindMapFormatExceptionForNodeWithoutChildList() {
        mindMapToTestsConverter.createTestSuite(TestNode.aNodeWithoutChildList());
    }

    @Test(expected = MindMapFormatException.class)
    public void createTestSuite_ShouldThrowMindMapFormatExceptionForTestSuiteNodeWithoutRequestNode() {
        mindMapToTestsConverter.createTestSuite(TestNode.aTestSuiteNodeWithoutRequestNode());
    }

    @Test(expected = MindMapFormatException.class)
    public void createTestSuite_ShouldThrowMindMapFormatExceptionFor() {
        mindMapToTestsConverter.createTestSuite(TestNode.aTestSuiteNodeWithoutTestCaseNodesList());
    }

    @Test(expected = MindMapFormatException.class)
    public void createRequest_ShouldThrowMindMapFormatExceptionForNodeWithoutChildList() {
        mindMapToTestsConverter.createRequest(TestNode.aNodeWithoutChildList());
    }

    @Test(expected = MindMapFormatException.class)
    public void createRequest_ShouldThrowMindMapFormatExceptionForRequestNodeWithoutMethodNode() {
        mindMapToTestsConverter.createRequest(TestNode.aRequestNodeWithoutMethodNode());
    }

    @Test(expected = MindMapFormatException.class)
    public void createTestCase_ShouldThrowMindMapFormatExceptionForNodeWithoutChildList() {
        mindMapToTestsConverter.createTestCase(TestNode.aNodeWithoutChildList());
    }

    @Test(expected = MindMapFormatException.class)
    public void createTestCase_ShouldThrowMindMapFormatExceptionForTestCaseNodeWithoutResultNode() {
        mindMapToTestsConverter.createTestCase(TestNode.aTestCaseNodeWithoutResultNode());
    }

    @Test
    public void createTestCase_() {
        TestCase expected = TestTestCase.aTestCaseWithoutParameterList();
        TestCase actual = mindMapToTestsConverter.createTestCase(TestNode.aTestCaseNodeWithoutParameterNodeList());
        assertEquals(expected, actual);
    }

}
