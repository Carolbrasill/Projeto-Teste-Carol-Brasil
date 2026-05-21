package classes

enum class NivelCurso {
    Básico,
    Intermediário,
    Avançado
}

enum class CategoriaCurso {
    Kotlin,
    Android,
    Arquitetura,
    Testes,
    Design
}

data class Cursos(
    val id: Int,
    val titulo: String,
    val cargaHoraria: Int,
    val nivel: String?,
    val categoria: String?
)
