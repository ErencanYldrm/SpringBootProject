package kodlama.northwind.core.utilities.results;

public class ErrorResult<T> extends DataResult{

    public ErrorResult() {
        super(null,false);
    }
    public ErrorResult(T data,String message) {
        super(data,false,message);
    }
    public ErrorResult(T data) {
        super(data,false);
    }
}
