package tests

import config.BrowserTest
import config.annotations.Browser
import config.annotations.Screenshot
import config.driver.Breakpoint
import org.fluentlenium.core.domain.FluentWebElement
import org.junit.jupiter.api.Test
import java.util.*

@Browser(dimension = Breakpoint.FULLSCREEN)
@Screenshot
class BusinessMetricsTest : BrowserTest() {

    @Test
    fun `demo that metrics will be written to dashboard`() {
        `Open Business Metrics Dashboard`
        `wait seconds`(5)

        `request Middleware From Different Countries`
        `Open Business Metrics Dashboard`
        `wait seconds`(10)

        `request Middleware From Different Countries`
        `Open Business Metrics Dashboard`
        `wait seconds`(10)
    }

    private val `request Middleware From Different Countries`: Unit
        get() {
            repeat(10) { callEndpoint(Locale.GERMANY) }
            repeat(5) { callEndpoint(Locale.ITALY) }
            repeat(3) { callEndpoint(Locale.FRANCE) }
            repeat(1) { callEndpoint(Locale.UK) }
        }

    private fun List<FluentWebElement>.findLinkByText(text: String) =
        find { it.text() == text } ?: throw Exception("could not find link with text: $text")

    private fun callEndpoint(locale: Locale) {
        goTo("http://localhost:8080/example?country=${locale.country}")
    }

    private val `Open Business Metrics Dashboard`: Unit
        get() {
            goTo("http://localhost:8080")
            jq("li a").findLinkByText("Business metrics").click()
        }

    private fun `wait seconds`(seconds: Long) {
        Thread.sleep(seconds * 1000)
    }
}
