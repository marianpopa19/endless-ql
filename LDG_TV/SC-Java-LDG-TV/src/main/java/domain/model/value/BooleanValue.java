package domain.model.value;

public class BooleanValue extends Value<Boolean>{
    private Boolean value;

    public BooleanValue(Boolean value){
        this.value = value;
    }
    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void accept(Boolean b) throws Exception {
        this.value = b;
    }
}