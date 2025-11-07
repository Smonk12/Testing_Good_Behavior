package fastAPI.bdd;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fastAPI/bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "fastAPI.bdd.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports.html")
public class RunCucumberTest {
}