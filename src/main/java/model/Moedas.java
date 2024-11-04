package model;

public class Moedas {
    private double quantidade;
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private double conversion_result;


    public Moedas(double quantidade){
        this.quantidade = quantidade;
    }

    public Moedas(double quantidade, DadosMoeda moedaRecord) {
        this.quantidade = quantidade;
        this.base_code = moedaRecord.base_code();
        this.target_code = moedaRecord.target_code();
        this.conversion_rate = Double.valueOf(moedaRecord.conversion_rate());
        this.conversion_result = Double.valueOf(moedaRecord.conversion_result());
    }

    public double getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(double quantidade){
        this.quantidade = quantidade;
    }

    /*
    public String getBase_code() {
        return base_code;
    }
    */

    @Override
    public String toString() {
        return String.format("%.2f", conversion_result) + " " + target_code + " " + quantidade;
    }
}
