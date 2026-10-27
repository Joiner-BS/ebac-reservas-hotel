// Representa uma reserva e os dados da hospedagem.
public class Reserva {

    // Atributos privados: acessados pelos métodos da classe.
    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;

    // Inicializa a reserva com todos os dados.
    public Reserva(String nomeHospede, String tipoQuarto,
                   int numeroDias, double valorDiaria) {
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;

        // Usa os setters para validar os valores recebidos.
        setNumeroDias(numeroDias);
        setValorDiaria(valorDiaria);
    }

    // Retorna o nome do hóspede.
    public String getNomeHospede() {
        return nomeHospede;
    }

    // Permite alterar o nome do hóspede.
    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    // Retorna o tipo do quarto.
    public String getTipoQuarto() {
        return tipoQuarto;
    }

    // Permite alterar o tipo do quarto.
    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    // Retorna a quantidade de dias da hospedagem.
    public int getNumeroDias() {
        return numeroDias;
    }

    // Valida e atualiza a quantidade de dias.
    public void setNumeroDias(int numeroDias) {
        if (numeroDias < 1) {
            throw new IllegalArgumentException(
                    "O número de dias deve ser maior ou igual a 1."
            );
        }

        this.numeroDias = numeroDias;
    }

    // Retorna o valor da diária.
    public double getValorDiaria() {
        return valorDiaria;
    }

    // Valida e atualiza o valor da diária.
    public void setValorDiaria(double valorDiaria) {
        if (!Double.isFinite(valorDiaria) || valorDiaria <= 0) {
            throw new IllegalArgumentException(
                    "O valor da diária deve ser um número finito maior que zero."
            );
        }

        this.valorDiaria = valorDiaria;
    }

    // Calcula o valor total da hospedagem.
    public double calcularValorTotal() {
        return numeroDias * valorDiaria;
    }

    // Retorna uma descrição da reserva para exibição no console.
    @Override
    public String toString() {
        return String.format(
                "Hóspede: %s%n"
                        + "Tipo do quarto: %s%n"
                        + "Dias de estadia: %d%n"
                        + "Valor da diária: R$ %.2f%n"
                        + "Valor total: R$ %.2f",
                nomeHospede,
                tipoQuarto,
                numeroDias,
                valorDiaria,
                calcularValorTotal()
        );
    }
}