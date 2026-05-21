package cerebro

import classes.Alunos
import classes.Cursos
import classes.Trilhas

class GerenciadorSistemas {
    private val listaAlunos = mutableListOf<Alunos>()
    private val listaCursos = mutableListOf<Cursos>()
    private val listaTrilhas = mutableListOf<Trilhas>()

    fun cadastrarAluno(aluno: Alunos): Boolean {
        if (aluno.id <= 0) {
            println("❌ Erro: O ID do aluno deve ser maior que zero!")
            return false
        }
        if (aluno.nome.contains("\\d".toRegex())) {
            println("❌ Erro: O nome do aluno não pode conter números!")
            return false
        }
        val emailValido =
                aluno.email.endsWith("@gmail.com") ||
                aluno.email.endsWith("@hotmail.com") ||
                aluno.email.endsWith("@yahoo.com") ||
                aluno.email.endsWith("@aluno.senai.br")

        if (!emailValido) {
            println("❌ Erro: E-mail inválido!")
            return false
        }
        if (listaAlunos.any { it.id == aluno.id }) {
            println("❌ Erro: Já existe um aluno com este ID!")
            return false
        }
        listaAlunos.add(aluno)
        return true
    }

    fun listarAlunos() {
        if (listaAlunos.isEmpty()) {
            println("Nenhum aluno cadastrado.")
            return
        }
        println("\n--- Lista alunos ---")
        listaAlunos.forEach {
            println("ID: ${it.id}")
            println("Nome: ${it.nome}")
            println("Email: ${it.email}")
            println("Situação: ${it.situacao}")
            println("--------------------------")
        }
    }

    fun cadastrarCurso(curso: Cursos): Boolean {
        if (curso.id <= 0) {
            println("❌ Erro: O ID do curso deve ser maior que zero!")
            return false
        }
        if (listaCursos.any { it.id == curso.id }) {
            println("❌ Erro: Já existe um curso com este ID!")
            return false
        }
        listaCursos.add(curso)
        return true
    }

    fun listarCursos() {
        if (listaCursos.isEmpty()) {
            println("Nenhum curso cadastrado.")
            return
        }
        println("\n--- Listar cursos ---")
        listaCursos.forEach {
            println("ID: ${it.id}")
            println("Título: ${it.titulo}")
            println("Carga Horária: ${it.cargaHoraria}h")
            println("Nível: ${it.nivel}")
            println("Categoria: ${it.categoria}")
            println("------------------------")
        }
    }

    fun cadastrarTrilha(trilha: Trilhas): Boolean {
        if (trilha.id <= 0) {
            println("❌ Erro: O ID da trilha deve ser maior que zero!")
            return false
        }
        if (listaTrilhas.any { it.id == trilha.id }) {
            println("❌ Erro: Já existe uma trilha com este ID!")
            return false
        }
        listaTrilhas.add(trilha)
        return true
    }

    fun listarTrilhas() {
        if (listaTrilhas.isEmpty()) {
            println("Nenhuma trilha cadastrada.")
            return
        }
        println("\n--- Lista de trilhas ---")
        listaTrilhas.forEach {
            println("ID: ${it.id}")
            println("Nome: ${it.nome}")
            println("Descrição: ${if (it.descricao.isBlank()) "Sem descrição" else it.descricao}")
            println("Status: ${it.status}")
            println("Quantidade de Cursos: ${it.cursos.size}")
            println("Quantidade de Alunos: ${it.alunosMatriculados.size}")
            println("-----------------------")
        }
    }


    fun adicionarCursoATrilha(idCurso: Int, idTrilha: Int): Boolean {
        val curso = listaCursos.find { it.id == idCurso }
        val trilha = listaTrilhas.find { it.id == idTrilha }

        if (curso == null) {
            println("❌ Erro: Curso não encontrado!")
            return false
        }
        if (trilha == null) {
            println("❌ Erro: Trilha não encontrada!")
            return false
        }
        return trilha.adicionarCurso(curso)
    }

    fun matricularAlunoEmTrilha(idAluno: Int, idTrilha: Int): Boolean {
        val aluno = listaAlunos.find { it.id == idAluno }
        val trilha = listaTrilhas.find { it.id == idTrilha }

        if (aluno != null && trilha != null) {
            if (trilha.alunosMatriculados.any { it.id == idAluno }) {
                println("Aluno já está matriculado nesta trilha!")
                return false
            }
            trilha.alunosMatriculados.add(aluno)
            return true
        }
        println("❌ Erro: Aluno ou Trilha não encontrados!")
        return false
    }

    fun registrarProgresso(idAluno: Int, idTrilha: Int, novoProgresso: Double): Boolean {
        val trilha = listaTrilhas.find { it.id == idTrilha }
        if (trilha == null) {
            println("❌ Erro: Trilha ou Aluno não encontrado(a)!")
            return false
        }

        val aluno = trilha.alunosMatriculados.find { it.id == idAluno }
        if (aluno == null) {
            println("❌ Erro: Aluno não está matriculado nesta trilha!")
            return false
        }

        if (novoProgresso < 0.0 || novoProgresso > 100.0) {
            println("❌ Erro: O progresso deve ser um valor entre 0 e 100!")
            return false
        }

        println("✅ Progresso do aluno ${aluno.nome} na trilha ${trilha.nome} atualizado para ${novoProgresso}%!")
        return true
    }

    fun exibirRelatorios() {
        println("\n--- Relatório geral ---")
        println("Total de Alunos: ${listaAlunos.size}")
        println("Total de Cursos: ${listaCursos.size}")
        println("Total de Trilhas: ${listaTrilhas.size}")
    }
}