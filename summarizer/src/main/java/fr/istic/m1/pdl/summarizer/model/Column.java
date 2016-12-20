package fr.istic.m1.pdl.summarizer.model;


import java.util.ArrayList;
import java.util.List;

// represente une Future dans le cas d'un PCM
public class Column {

	private String name;
	
	/**
	 * Indique si les valeurs d'un  feature sont de type numerique ou non.
	 */
    private TypeColumn type = TypeColumn.Number;
    
    /**
     * Liste des valeurs de la colonne
     */
    List<ColumnValue> values= new ArrayList<ColumnValue>();
    
    
    public Column (String name){
    	this.name=name;
    }
    
    
    public void addValue(ColumnValue value){
		if(!values.contains(value)){
			values.add(value);
		}
	}
	
	
	public void removeValue(ColumnValue value){
		if(values.contains(value)){
			
			values.remove(value);
		}
	}
    
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public TypeColumn getType() {
		return type;
	}


	public void setType(TypeColumn type) {
		this.type = type;
	}

	public enum TypeColumn {
		Number ,
		String;

	}
	
	/**
	 * @return une copie de la liste
	 */
	public List<ColumnValue> getValues() {
		return new ArrayList<ColumnValue>(values);
	}
	
	@Override
	public String toString() {
		return name + " : type = " + type + " taille = "+ values.size() + "\n\t" + values;
	}
}






