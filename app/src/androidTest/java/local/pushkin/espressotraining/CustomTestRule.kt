package local.pushkin.espressotraining

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CustomTestRule(val tag: String) : TestRule {
    override fun apply(
        base: Statement,
        description: Description?
    ): Statement {
        return object : Statement() {
            override fun evaluate() {
                println("$tag : BEFORE")
                base.evaluate()
                println("$tag : AFTER")
            }
        }
    }
}