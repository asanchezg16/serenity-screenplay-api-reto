package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/mobile",
        glue = {"stepdefinitions"},
        tags = "@mobile and not @ignore",
        plugin = {"pretty"}
)
public class MobileTestSuite {

}