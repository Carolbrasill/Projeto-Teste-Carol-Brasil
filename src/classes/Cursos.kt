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

data class Curso(
    val id: Int,
    val titulo: String,
    val cargaHoraria: Int,
    val nivel: NivelCurso,
    val categoria: CategoriaCurso
)
