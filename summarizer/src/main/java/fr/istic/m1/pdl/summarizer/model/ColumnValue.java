package fr.istic.m1.pdl.summarizer.model;
//represente un individue et les valeurs de ses caracteristiques
public class ColumnValue {
	
	private Object value;
	private int order;
	
	
	public ColumnValue (String value, int order) {
		this.value=value;
		this.order = order;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	/**
	 * Indique l'ordre d'apparution de la valeur dans la cell.
	 * Pour les cell multivalue, toutes les valeurs ont le num d'ordre
	 * @return l'ordre de la valeur
	 */
	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		if(value != null){
			return value.toString();
		}
		return null;
	}

}
