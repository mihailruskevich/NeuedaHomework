# NeuedaHomework

This is a test automation solution that uses mind maps 
to generate and execute tests against a remote rest service.

There is an example in project of supported mind map file format.

To instal application use: mvn package.

Application usage example:

    java -jar NeuedaHomework-1.0-SNAPSHOT-jar-with-dependencies.jar 
       -f "C:\Documents and Settings\User\mindmap\calc_tests.mm" 
       -u http://calculator.neueda.lv



Options description:

    -f    mind map file location

    -u    rest service endpoint location
