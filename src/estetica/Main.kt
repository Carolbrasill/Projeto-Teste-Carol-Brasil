package estetica

import cerebro.GerenciadorSistemas
import classes.Alunos
import classes.Cursos
import classes.Trilhas
import java.io.PrintStream
import java.io.FileOutputStream
import java.io.FileDescriptor
import java.util.Scanner

fun main() {
    System.setOut(PrintStream(FileOutputStream(FileDescriptor.out), true, "UTF-8"))

    val gerenciador = GerenciadorSistemas()
    val entrada = Scanner(System.`in`)
    var opcao: Int

    do {
        println("\n=======================================")
        println("       Gestão de Dados e Cadastros       ")
        println("=======================================")
        println("1. Cadastrar aluno.")
        println("2. Listar alunos.")
        println("3. Cadastrar curso.")
        println("4. Listar cursos.")
        println("5. Cadastrar trilha.")
        println("6. Listar trilhas.")
        println("7. Adicionar curso a uma trilha.")
        println("8. Matricular aluno em trilha.")
        println("9. Registrar progresso do aluno.")
        println("10. Exibir relatórios.")
        println("11. Sair.")
        print("Escolha uma opção: ")

        val opcaoTexto = entrada.nextLine()
        opcao = opcaoTexto.toIntOrNull() ?: -1

        when (opcao) {
            1 -> {
                println("\n--- Cadastrar aluno ---")
                print("ID (Número de matrícula até 9 algarismos): ")
                val id = entrada.nextLine().toIntOrNull()
                if (id == null || id <= 0) {
                    println("❌ Erro: O ID deve conter apenas números maiores que zero!")
                    continue
                }
                print("Nome: ")
                val nome = entrada.nextLine()
                if (nome.isBlank()) {
                    println("❌ Erro: O nome não pode ficar em branco!")
                    continue
                }
                print("Email: ")
                val email = entrada.nextLine()

                println("Escolha a Situação do Aluno:")
                println("1 - Ativo")
                println("2 - Inativo")
                println("3 - Bloqueado")
                print("Opção: ")
                val opcaoSituacao = entrada.nextLine().toIntOrNull()
                val situacaoEnum = when (opcaoSituacao) {
                    1 -> classes.SituacaoAluno.Ativo
                    2 -> classes.SituacaoAluno.Inativo
                    3 -> classes.SituacaoAluno.Bloqueado
                    else -> null
                }
                if (situacaoEnum == null) {
                    println("❌ Erro: Situação inválida!")
                    continue
                }

                val sucesso = gerenciador.cadastrarAluno(Alunos(id, nome, email, situacaoEnum.name))
                if (sucesso) println("✅ Aluno cadastrado com sucesso!")
            }

            2 -> gerenciador.listarAlunos()

            3 -> {
                println("\n--- CADASTRAR CURSO ---")
                print("ID do Curso: ")
                val id = entrada.nextLine().toIntOrNull()
                if (id == null || id <= 0) {
                    println("❌ Erro: O ID deve conter apenas números maiores que zero!")
                    continue
                }

                print("Nome do Curso: ")
                val titulo = entrada.nextLine()
                if (titulo.isBlank()) {
                    println("❌ Erro: O nome do curso não pode ficar em branco!")
                    continue
                }

                print("Carga Horária do Curso (em horas): ")
                val cargaHoraria = entrada.nextLine().toIntOrNull()
                if (cargaHoraria == null || cargaHoraria <= 0) {
                    println("❌ Erro: A carga horária deve ser um número maior que zero!")
                    continue
                }

                println("Escolha o Nível do Curso:")
                println("1 - Básico")
                println("2 - Intermediário")
                println("3 - Avançado")
                print("Opção: ")
                val opcaoNivel = entrada.nextLine().toIntOrNull()
                val nivelEnum = when (opcaoNivel) {
                    1 -> classes.NivelCurso.`Básico`
                    2 -> classes.NivelCurso.`Intermediário`
                    3 -> classes.NivelCurso.`Avançado`
                    else -> null
                }
                if (nivelEnum == null) {
                    println("❌ Erro: Nível inválido!")
                    continue
                }

                println("Escolha a Categoria do Curso:")
                println("1 - Kotlin")
                println("2 - Android")
                println("3 - Arquitetura")
                println("4 - Testes")
                println("5 - Design")
                print("Opção: ")
                val opcaoCategoria = entrada.nextLine().toIntOrNull()
                val categoriaEnum = when (opcaoCategoria) {
                    1 -> classes.CategoriaCurso.Kotlin
                    2 -> classes.CategoriaCurso.Android
                    3 -> classes.CategoriaCurso.Arquitetura
                    4 -> classes.CategoriaCurso.Testes
                    5 -> classes.CategoriaCurso.Design
                    else -> null
                }
                if (categoriaEnum == null) {
                    println("❌ Erro: Categoria inválida!")
                    continue
                }

                val sucesso = gerenciador.cadastrarCurso(Cursos(
                    id = id,
                    titulo = titulo,
                    cargaHoraria = cargaHoraria,
                    nivel = nivelEnum.name,
                    categoria = categoriaEnum.name
                ))
                if (sucesso) println("✅ Curso cadastrado com sucesso!")
            }

            4 -> gerenciador.listarCursos()

            5 -> {
                println("\n--- Cadastrar trilha---")
                print("ID da Trilha: ")
                val idTexto = entrada.nextLine()
                val id = idTexto.toIntOrNull()
                if (id == null || id <= 0 || idTexto.length > 9) {
                    println("❌ Erro: O ID deve conter apenas números válidos (máx. 9 dígitos)!")
                    continue
                }

                print("Nome da Trilha: ")
                val nome = entrada.nextLine()
                if (nome.isBlank()) {
                    println("❌ Erro: O nome da trilha não pode ficar em branco!")
                    continue
                }

                print("Descrição da Trilha: ")
                val descricao = entrada.nextLine()

                println("Escolha o Status da Trilha:")
                println("1 - Planejada")
                println("2 - Ativa")
                println("3 - Concluída")
                println("4 - Arquivada")
                print("👉 Opção: ")
                val opcaoStatus = entrada.nextLine().toIntOrNull()

                val statusEnum = when (opcaoStatus) {
                    1 -> classes.StatusTrilha.Planejada
                    2 -> classes.StatusTrilha.Ativa
                    3 -> classes.StatusTrilha.Concluída
                    4 -> classes.StatusTrilha.Arquivada
                    else -> classes.StatusTrilha.Planejada
                }

                val sucesso = gerenciador.cadastrarTrilha(Trilhas(
                    id = id,
                    nome = nome,
                    descricao = descricao,
                    status = statusEnum
                ))
                if (sucesso) println("✅ Trilha cadastrada com sucesso!")
            }
            6 -> gerenciador.listarTrilhas()

            7 -> {
                println("\n--- Adicionar curso a trilha ---")
                print("ID do Curso: ")
                val idCurso = entrada.nextLine().toIntOrNull()
                print("ID da Trilha: ")
                val idTrilha = entrada.nextLine().toIntOrNull()

                if (idCurso == null || idTrilha == null) {
                    println("❌ Erro: Os IDs devem conter apenas números!")
                    continue
                }
                val sucesso = gerenciador.adicionarCursoATrilha(idCurso, idTrilha)
                if (sucesso) println("✅ Curso adicionado à trilha com sucesso!")
            }

            8 -> {
                println("\n--- Matricular aluno em trilha ---")
                print("ID do Aluno: ")
                val idAluno = entrada.nextLine().toIntOrNull()

                print("ID da Trilha: ")
                val idTrilha = entrada.nextLine().toIntOrNull()
                if (idAluno == null || idTrilha == null) {
                    println("❌ Erro: Os IDs devem conter apenas números válidos!")
                    continue
                }
                val sucesso = gerenciador.matricularAlunoEmTrilha(idAluno, idTrilha)
                if (sucesso) println("✅ Aluno matriculado na trilha com sucesso!")
            }

            9 ->  {
            println("\n--- Registrar progresso do aluno ---")
            print("ID do Aluno: ")
            val idAluno = entrada.nextLine().toIntOrNull()
            print("ID da Trilha: ")
            val idTrilha = entrada.nextLine().toIntOrNull()

            if (idAluno == null || idTrilha == null) {
                println("❌ Erro: Os IDs devem conter apenas números!")
                continue
            }

            print("Digite a porcentagem de progresso (0 a 100): ")
            val progresso = entrada.nextLine().toDoubleOrNull()

            if (progresso == null) {
                println("❌ Erro: Digite um número válido para o progresso!")
                continue
            }

            gerenciador.registrarProgresso(idAluno, idTrilha, progresso)
        }
            10 -> gerenciador.exibirRelatorios()

            11 -> println("\n Sistema encerrado. Até logo!")
            else -> println("\nOpção inválida! Tente novamente.")
        }
    } while (opcao != 11)
}