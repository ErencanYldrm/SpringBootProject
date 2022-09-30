package kodlama.northwind.core.utilities.results;

public class SuccessResult<T> extends DataResult {
    public SuccessResult(T data,String message){
        super(data,true,message);
    }
    public SuccessResult(T data){
        super(data,true);
    }
    public SuccessResult(String message){
        super(null,true,message);
    }
}
