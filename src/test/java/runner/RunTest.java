package runner;

import courgette.api.*;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.FEATURE,
//        rerunFailedScenarios = true,
//        rerunAttempts = 1,
        testOutput = CourgetteTestOutput.CONSOLE,
        reportTitle = "Courgette-JVM Example",
        reportTargetDir = "target",
        cucumberOptions = @CucumberOptions(
                features = "src/test/java/Features",
                glue = "StepsDefinition",
                tags = "@Flights",
                publish = true,
                plugin = {"json:target/cucumber/cucumber.json"}
        ))
public class RunTest {
}