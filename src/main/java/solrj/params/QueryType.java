package solrj.params;

public enum QueryType {
	DATAIMPORT("dataimport"), MMSEG4J("mmseg4j"), COLLECTIONS("collections"), CORES("cores");

	private String _value;

	QueryType(String value){
		_value = value;
	}

	public String value(){
		return _value;
	}

	public static void main(String[] args){
		System.out.println(DATAIMPORT.value());
		System.out.println(MMSEG4J.value());
	}
}
