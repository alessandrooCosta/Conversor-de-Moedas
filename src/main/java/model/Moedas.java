package model;

import dao.DadosMoeda;

public class Moedas {
    private double quantidade;
    private String base_code;
    private String target_code;
    private double conversion_result;
    private String time_last_update_utc;

    public Moedas(double quantidade){
        this.quantidade = quantidade;
    }

    public Moedas(double quantidade, DadosMoeda moedaRecord) {
        this.quantidade = quantidade;
        this.base_code = moedaRecord.base_code();
        this.target_code = moedaRecord.target_code();
        this.conversion_result = Double.valueOf(moedaRecord.conversion_result());
        this.time_last_update_utc = moedaRecord.time_last_update_utc();
    }

    public double getQuantidade(){
        return quantidade;
    }

    public String dataUltimaAtualização(){
        return time_last_update_utc;
    }

    @Override
    public String toString() {
        return String.format("%.2f", quantidade)+" "+base_code+" = "+String.format("%.2f", conversion_result)+" "+target_code + "\n";
    }

}
