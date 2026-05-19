package classes

enum class SituacaoAluno {
    Ativo,
    Inativo,
    Bloqueado
}

data class Aluno(
    val id: Int,
    val nome: String,
    val email: String,
    var situacao: SituacaoAluno = SituacaoAluno.Ativo
)