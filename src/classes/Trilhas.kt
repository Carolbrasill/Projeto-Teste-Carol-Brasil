package classes

enum class StatusTrilha {
    Planejada,
    Ativa,
    Concluída,
    Arquivada
}

class Trilha(
    val id: Int,
    val nome: String,
    val descricao: String,
    var status: StatusTrilha = StatusTrilha.Planejada
) {
    private val _cursos = mutableListOf<Curso>()

    val cursos: List<Curso> get() = _cursos

    fun adicionarCurso(curso: Curso): Boolean {
        if (_cursos.any { it.id == curso.id }) {
            println("Aviso: O curso '${curso.titulo}' já faz parte desta trilha.")
            return false
        }
        _cursos.add(curso)
        return true
    }

    fun calcularCargaHorariaTotal(): Int {
        return _cursos.sumOf { it.cargaHoraria }
    }
}