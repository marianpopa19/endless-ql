package checking.value;

public class BooleanValue extends Value {
  
	private boolean value;
	public static BooleanValue FALSE = new BooleanValue(false);
	public static BooleanValue TRUE = new BooleanValue(true);

	public BooleanValue(boolean value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public BooleanValue or(Value val) {
		return val.or(this);
	}

	@Override
	public BooleanValue or(BooleanValue val) {
		boolean result = (val.value || this.value);
		return new BooleanValue(result);
	}
	
	@Override
	public BooleanValue and(Value val) {
		return val.or(this);
	}

	@Override
	public BooleanValue and(BooleanValue val) {
		boolean result = (val.value && this.value);
		return new BooleanValue(result);
	}

	@Override
	public BooleanValue not() {
	    return new BooleanValue(!(this.value));
	}
	
	@Override
	public BooleanValue eq(Value val) {
		return val.eq(this);
	}
	
	@Override
	public BooleanValue eq(BooleanValue val) {
		boolean result = (val.value == this.value);
		return new BooleanValue(result);
	}
	
	@Override
	public BooleanValue eq(StringValue val) {
		return BooleanValue.FALSE;
	}
	
	@Override
	public BooleanValue eq(IntegerValue val) {
		return BooleanValue.FALSE;
	}
	
	@Override
	public BooleanValue eq(DecimalValue val) {
		return BooleanValue.FALSE;
	}

	@Override
	public BooleanValue eq(DateValue val) {
		return BooleanValue.FALSE;
	}
	
	@Override
	public BooleanValue gt(Value val) {
		return throwException();
	}

	@Override
	public BooleanValue gEq(Value val) {
		return throwException();
	}

	@Override
	public BooleanValue lt(Value val) {
		return throwException();
	}

	@Override
	public BooleanValue lEq(Value val) {
		return throwException();
	}

	@Override
	public Value add(Value val) {
		return throwException();
	}

	@Override
	public Value sub(Value val) {
		return throwException();
	}

	@Override
	public Value mul(Value val) {
		return throwException();
	}

	@Override
	public Value div(Value val) {
		return throwException();
	}

	@Override
	public Value check(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value neg() {
		return throwException();
	}

	@Override
	public Value pos() {
		return throwException();
	}
	

	@Override
	public <T extends Value> T throwException() {
		throw new UnsupportedOperationException(getClass().getName());
	}
}