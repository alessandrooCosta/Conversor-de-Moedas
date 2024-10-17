package model;

public class Moedas {
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private double conversion_result;

    public Moedas(DadosMoeda moedaRecord) {
        this.base_code = moedaRecord.base_code();
        this.target_code = moedaRecord.target_code();
        this.conversion_rate = Double.valueOf(moedaRecord.conversion_rate());
        this.conversion_result = Double.valueOf(moedaRecord.conversion_result());
    }

    @Override
    public String toString() {
        return conversion_result + target_code;
    }
}
