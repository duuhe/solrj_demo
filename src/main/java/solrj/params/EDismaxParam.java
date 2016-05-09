package solrj.params;

public class EDismaxParam extends DismaxParam {

	private String boost;
	private String ps;
	private String pf2;
	private String ps2;
	private String pf3;
	private Boolean lowercaseOperators;
	private Boolean stopwords;
	private String uf;

	public EDismaxParam(String keyword) {
		super(null);
		setKeyword(keyword);
		setDfType("edismax");
		setAlt("*:*");
	}

	public String getBoost() {
		return boost;
	}

	public void setBoost(String boost) {
		this.boost = boost;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getPf2() {
		return pf2;
	}

	public void setPf2(String pf2) {
		this.pf2 = pf2;
	}

	public String getPs2() {
		return ps2;
	}

	public void setPs2(String ps2) {
		this.ps2 = ps2;
	}

	public String getPf3() {
		return pf3;
	}

	public void setPf3(String pf3) {
		this.pf3 = pf3;
	}

	public Boolean getLowercaseOperators() {
		return lowercaseOperators;
	}

	public void setLowercaseOperators(Boolean lowercaseOperators) {
		this.lowercaseOperators = lowercaseOperators;
	}

	public Boolean getStopwords() {
		return stopwords;
	}

	public void setStopwords(Boolean stopwords) {
		this.stopwords = stopwords;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
