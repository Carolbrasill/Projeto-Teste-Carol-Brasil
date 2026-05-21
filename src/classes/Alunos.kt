package classes

enum class SituacaoAluno {
    Ativo,
    Inativo,
    Bloqueado
}

data class Alunos
    (
    val id: Int,
    val nome: String,
    val email: String,
    var situacao: String = SituacaoAluno.Ativo.toString()
)