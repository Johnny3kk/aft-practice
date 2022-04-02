package ru.ibs.framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ru.ibs.framework.steps"},
        features = {"src/test/resources/scenario/"},
        tags = {"@iphoneTest or @headphoneTest"},
        plugin = {"ru.ibs.framework.utils.MyCucumberListener"})
public class CucumberRunner {
}
