package params;

import java.util.Collection;

import common.SolrField;

public class SolrDismaxParam {
	private String dfType;
	private String keyword;
	private String alt;
	private String minimum;
	private Collection<SolrField> queryFields;
	private Collection<SolrField> phraseFields;
	private String phraseSlop;
	private String queryPhraseSlop;
	private String tieBreaker;
	private Collection<SolrField> boostQuery;
	private String boostFunctions;
	private Collection<String> filedsReturn;

	public SolrDismaxParam(String keyword) {
		this.keyword = keyword;
		this.dfType = "dismax";
		this.alt = "*:*";
	}

	public String getDfType() {
		return dfType;
	}

	public void setDfType(String dfType) {
		this.dfType = dfType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public Collection<SolrField> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(Collection<SolrField> queryFields) {
		this.queryFields = queryFields;
	}

	public Collection<SolrField> getPhraseFields() {
		return phraseFields;
	}

	public void setPhraseFields(Collection<SolrField> phraseFields) {
		this.phraseFields = phraseFields;
	}

	public String getPhraseSlop() {
		return phraseSlop;
	}

	public void setPhraseSlop(String phraseSlop) {
		this.phraseSlop = phraseSlop;
	}

	public String getQueryPhraseSlop() {
		return queryPhraseSlop;
	}

	public void setQueryPhraseSlop(String queryPhraseSlop) {
		this.queryPhraseSlop = queryPhraseSlop;
	}

	public String getTieBreaker() {
		return tieBreaker;
	}

	public void setTieBreaker(String tieBreaker) {
		this.tieBreaker = tieBreaker;
	}

	public Collection<SolrField> getBoostQuery() {
		return boostQuery;
	}

	public void setBoostQuery(Collection<SolrField> boostQuery) {
		this.boostQuery = boostQuery;
	}

	public String getBoostFunctions() {
		return boostFunctions;
	}

	public void setBoostFunctions(String boostFunctions) {
		this.boostFunctions = boostFunctions;
	}

	public Collection<String> getFiledsReturn() {
		return filedsReturn;
	}

	public void setFiledsReturn(Collection<String> filedsReturn) {
		this.filedsReturn = filedsReturn;
	}

}
