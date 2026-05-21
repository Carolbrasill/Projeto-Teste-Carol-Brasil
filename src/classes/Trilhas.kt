package classes

enum class StatusTrilha {
    Planejada,
    Ativa,
    Concluída,
    Arquivada
}

class Trilhas(
    val id: Int,
    val nome: String,
    val descricao: String = "",
    var status: StatusTrilha = StatusTrilha.Planejada
) {
    val alunosMatriculados = mutableListOf<Alunos>()
    private val _cursos = mutableListOf<Cursos>()

    val cursos: List<Cursos>
        get() = _cursos

    fun adicionarCurso(curso: Cursos): Boolean {
        if (_cursos.any { it.id == curso.id }) {
            println("Aviso: O curso '${curso.titulo}' já faz parte desta trilha.")
            return false
        }
        _cursos.add(curso)
        return true
    }
}