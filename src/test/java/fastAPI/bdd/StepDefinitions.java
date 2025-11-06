package fastAPI.bdd;

import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    @Given("an example scenario")
    public void anExampleScenario() {
        System.out.println("given scenario works");
    }

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
        System.out.println("when scenario works");
    }

    @Then("the scenario passes")
    public void theScenarioPasses() {
        System.out.println("then scenario works");
    }

}
