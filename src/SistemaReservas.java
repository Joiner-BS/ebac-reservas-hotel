import java.util.Scanner;

import java.util.Locale;

// Controla o menu e as operações do sistema de reservas.
public class SistemaReservas {

    // Vetor fixo que armazena até 10 objetos Reserva.
    private static final Reserva[] reservas = new Reserva[10];

    // Controla quantas posições do vetor estão preenchidas.
    private static int quantidadeReservas = 0;

    // Ponto de entrada do programa.
    public static void main(String[] args) {

        // Prepara a leitura dos dados pelo teclado.
        Scanner scanner = new Scanner(System.in);

        // Guarda a opção escolhida pelo usuário.
        int opcao;

        // Exibe o menu pelo menos uma vez e repete até a opção de saída.
        do {
            System.out.println("\n===== SISTEMA DE RESERVAS =====");
            System.out.println("1 - Nova reserva");
            System.out.println("2 - Listar reservas");
            System.out.println("3 - Buscar reserva por nome do hóspede");
            System.out.println("4 - Ordenar reservas por número de dias");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            // Impede que uma entrada não inteira interrompa o programa.
            while (!scanner.hasNextInt()) {
                System.out.println("Digite uma opção numérica inteira.");
                scanner.nextLine();
                System.out.print("Escolha uma opção: ");
            }

            opcao = scanner.nextInt();

            // Consome o restante da linha antes das próximas leituras.
            scanner.nextLine();

            // Executa o bloco correspondente à opção escolhida.
            switch (opcao) {
                case 1:
                    cadastrarReserva(scanner);
                    break;

                case 2:
                    listarReservas();
                    break;

                case 3:
                    buscarReserva(scanner);
                    break;

                case 4:
                    ordenarReservas();
                    break;

                case 5:
                    System.out.println("Obrigado por utilizar o sistema!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha de 1 a 5.");
                    break;
            }

        } while (opcao != 5);

        // Fecha o leitor ao encerrar o programa.
        scanner.close();
    }

    // Lê os dados, valida e armazena uma nova reserva.
    private static void cadastrarReserva(Scanner scanner) {

        // Impede cadastrar além da capacidade do vetor.
        if (quantidadeReservas >= reservas.length) {
            System.out.println("Limite de 10 reservas atingido!");
            return;
        }

        System.out.println("\n===== NOVA RESERVA =====");

        // Recebe o nome completo do hóspede.
        System.out.print("Nome do hóspede: ");
        String nomeHospede = scanner.nextLine();

        // Recebe o tipo do quarto.
        System.out.print("Tipo do quarto: ");
        String tipoQuarto = scanner.nextLine();

        int numeroDias;

        // Repete a leitura até receber um inteiro maior ou igual a 1.
        while (true) {
            System.out.print("Número de dias de estadia: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Digite um número inteiro.");
                scanner.nextLine();
                continue;
            }

            numeroDias = scanner.nextInt();
            scanner.nextLine();

            if (numeroDias < 1) {
                System.out.println("A estadia deve ter pelo menos 1 dia.");
                continue;
            }

            break;
        }

        double valorDiaria;

        // Repete a leitura até receber uma diária válida.
        while (true) {
            System.out.print("Valor da diária: ");

            if (!scanner.hasNextDouble()) {
                System.out.println("Digite um valor numérico.");
                scanner.nextLine();
                continue;
            }

            valorDiaria = scanner.nextDouble();
            scanner.nextLine();

            if (!Double.isFinite(valorDiaria) || valorDiaria <= 0) {
                System.out.println("A diária deve ser um valor finito maior que zero.");
                continue;
            }

            break;
        }

        // Cria a reserva usando o construtor completo.
        Reserva novaReserva = new Reserva(
                nomeHospede, tipoQuarto, numeroDias, valorDiaria
        );

        // Guarda o objeto na próxima posição disponível.
        reservas[quantidadeReservas] = novaReserva;

        // Atualiza o contador somente após armazenar a reserva.
        quantidadeReservas++;

        System.out.println("Reserva cadastrada com sucesso!");
        System.out.println("Quantidade de reservas: " + quantidadeReservas);
    }

    // Exibe todas as reservas cadastradas.
    private static void listarReservas() {

        // Informa quando ainda não existem reservas.
        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.println("\n===== RESERVAS CADASTRADAS =====");

        // Percorre somente as posições preenchidas do vetor.
        for (int i = 0; i < quantidadeReservas; i++) {
            System.out.println("\nReserva " + (i + 1) + ":");

            // O println utiliza o toString() do objeto Reserva.
            System.out.println(reservas[i]);
        }

        System.out.println("\nQuantidade de reservas: " + quantidadeReservas);
    }

    // Busca reservas por parte do nome, ignorando maiúsculas e minúsculas.
    private static void buscarReserva(Scanner scanner) {

        // Evita solicitar uma busca quando não existem reservas.
        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.print("\nDigite o nome ou parte do nome do hóspede: ");

        // Remove espaços das extremidades e converte para minúsculas.
        String termoBusca = scanner.nextLine()
                .trim()
                .toLowerCase(Locale.ROOT);

        // Impede que uma pesquisa vazia corresponda a todos os nomes.
        if (termoBusca.isEmpty()) {
            System.out.println("Informe um nome ou parte dele para pesquisar.");
            return;
        }

        int quantidadeEncontrada = 0;

        // Examina somente as posições preenchidas do vetor.
        for (int i = 0; i < quantidadeReservas; i++) {

            // Obtém o nome da reserva e o converte para minúsculas.
            String nomeHospede = reservas[i].getNomeHospede()
                    .toLowerCase(Locale.ROOT);

            // Verifica se o nome contém o trecho pesquisado.
            if (nomeHospede.contains(termoBusca)) {
                quantidadeEncontrada++;

                System.out.println("\nResultado " + quantidadeEncontrada + ":");

                // Exibe os dados usando o toString() da reserva.
                System.out.println(reservas[i]);
            }
        }

        // Informa se a busca encontrou alguma reserva.
        if (quantidadeEncontrada == 0) {
            System.out.println("Nenhuma reserva encontrada para o nome informado.");
        } else {
            System.out.println("\nReservas encontradas: " + quantidadeEncontrada);
        }
    }

    // Ordena as reservas por quantidade de dias, em ordem decrescente.
    private static void ordenarReservas() {

        // Evita ordenar quando não existem reservas.
        if (quantidadeReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        // Usa Bubble Sort somente nas posições preenchidas do vetor.
        for (int passagem = 0;
             passagem < quantidadeReservas - 1;
             passagem++) {

            // Compara reservas vizinhas na parte ainda não ordenada.
            for (int i = 0;
                 i < quantidadeReservas - 1 - passagem;
                 i++) {

                // Coloca primeiro a reserva com mais dias.
                if (reservas[i].getNumeroDias()
                        < reservas[i + 1].getNumeroDias()) {

                    // Troca as referências dos objetos no vetor.
                    Reserva auxiliar = reservas[i];
                    reservas[i] = reservas[i + 1];
                    reservas[i + 1] = auxiliar;
                }
            }
        }

        System.out.println("Reservas ordenadas por dias, do maior para o menor.");

        // Apresenta a listagem já na nova ordem.
        listarReservas();
    }
}