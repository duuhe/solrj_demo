package solrj.common;

public class SolrContent {
	// Selects the query parser to be used to process the query.
	public static String DT = "defType";

	// Specifies the default operator for query expressions, overriding the
	// default operator specified in the schema.xml file. Possible values are
	// "AND" or "OR".
	public static String QO = "q.op";
	
	// symbol
	public static String UPARROW = "^";
	public static String blank = " ";
}
