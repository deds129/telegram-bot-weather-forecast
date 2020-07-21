//48.16

import java.text.DecimalFormat;

public class Model {
    //объект который мы будемвозвращать
    private String name;
    private Double temp;
    private Double humidity;
    private String icon;
    private String main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String  getTemp() {
        temp = -273 + temp;
        String formattedDouble = new DecimalFormat("#0.00").format(temp);
        return formattedDouble ;
    }

    public void setTemp(Double temp) {
        this.temp=temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity=humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon=icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main=main;
    }
}
