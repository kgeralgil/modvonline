package pe.edu.upc.tottus.models;

/**
 * Created by Fernando on 07/06/2018.
 */

public class Result {

    private String code;
    private String message;
    private Product data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getData() {
        return data;
    }

    public void setData(Product data) {
        this.data = data;
    }
}
