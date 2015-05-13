package lv.mrusk.neuedahomework.application;

import com.beust.jcommander.JCommander;
import lv.mrusk.neuedahomework.config.SpringConfig;
import lv.mrusk.neuedahomework.model.cmd.ArgsModel;
import lv.mrusk.neuedahomework.model.mindmap.MindMap;
import lv.mrusk.neuedahomework.model.tests.TestBundle;
import lv.mrusk.neuedahomework.model.tests.TestResult;
import lv.mrusk.neuedahomework.service.TestResultsPresenter;
import lv.mrusk.neuedahomework.service.TestsRunner;
import lv.mrusk.neuedahomework.service.Unmarshaler;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {

    private static ArgsModel createArgsModel(String[] args) {
        ArgsModel argsModel = new ArgsModel();
        new JCommander(argsModel, args);
        return argsModel;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        Mapper mapper = applicationContext.getBean(Mapper.class);
        Unmarshaler unmarshaler = applicationContext.getBean(Unmarshaler.class);
        TestsRunner testsRunner = applicationContext.getBean(TestsRunner.class);
        TestResultsPresenter testResultsPresenter = applicationContext.getBean(TestResultsPresenter.class);

        try {
            ArgsModel argsModel = createArgsModel(args);
            MindMap mindMap = unmarshaler.unmarshal(MindMap.class, argsModel.getFile());
            TestBundle tests = mapper.map(mindMap, TestBundle.class);
            List<TestResult> testResultList = testsRunner.runTests(tests, argsModel.getApiUrl().toString());
            testResultsPresenter.presentTestResultList(testResultList);
        } catch (Exception e) {
            Logger log = Logger.getLogger(Application.class);
            log.error(e.getMessage());
        }
    }
}
