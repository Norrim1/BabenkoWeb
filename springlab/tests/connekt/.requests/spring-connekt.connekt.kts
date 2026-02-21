import org.assertj.core.api.Assertions

val baseUrl: String by env
val path = "greeting"

GET("$baseUrl/$path") {
    accept("application/json")
} then {
    Assertions.assertThat(code).isEqualTo(200)
}

GET("$baseUrl/$path?id=550e8400-e29b-41d4-a716-446655440000") {
    accept("application/json")
} then {
    Assertions.assertThat(code).isEqualTo(200)
}

GET("$baseUrl/$path/550e8400-e29b-41d4-a716-446655440000") {
    accept("application/json")
} then {
    Assertions.assertThat(code).isEqualTo(200)
}