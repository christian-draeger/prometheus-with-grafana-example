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
class BusinessMetricsTest: BrowserTest() {

    @Test
    fun `demo that metrics will be written to dashboard`() {
        // call middleware with different country-codes
        doTimes(10) { callEndpoint(Locale.GERMANY) }
        doTimes(5) { callEndpoint(Locale.ITALY) }
        doTimes(3) { callEndpoint(Locale.FRANCE) }
        doTimes(1) { callEndpoint(Locale.UK) }

        goTo("http://localhost:8080")
        jq("li a").findLinkByText("Business metrics").click()

        // see metrics has been written and will be displayed in grafana
        Thread.sleep(10000)
    }

    private fun List<FluentWebElement>.findLinkByText(text: String) =
        find { it.text() == text } ?: throw Exception("could not find link with text: $text")

    private fun doTimes(count: Int, callback: () -> Unit) {
        for (x in 0 until count) callback()
        Thread.sleep(1000)
    }

    private fun callEndpoint(locale: Locale) {
        goTo("http://localhost:8080/example?country=${locale.country}")
    }
}
