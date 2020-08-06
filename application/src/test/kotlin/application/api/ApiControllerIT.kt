package application.api

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class ApiControllerIT(
    @Autowired val mockMvc: MockMvc
) {

    @ParameterizedTest
    @ValueSource(strings = ["DE", "FR", "ES"])
    fun `can call country endpoint and increase count`(country: String) {
        repeat(5) {
            mockMvc.get("/example") {
                param("country", country)
            }.andExpect {
                status { isOk }
                content { string("OCR-App call from $country - ${(it + 1).toFloat()}") }
            }
        }
    }
}