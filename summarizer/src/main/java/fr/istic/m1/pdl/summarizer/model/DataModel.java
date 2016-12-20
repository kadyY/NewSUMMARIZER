package fr.istic.m1.pdl.summarizer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.Value;

import fr.istic.m1.pdl.summarizer.model.Column.TypeColumn;

//represente un PCM dans notre cas ici
public class DataModel {

	Map<Feature, Value> featuresQuatitative = new HashMap<Feature, Value>();
	Map<Feature, Value> featuresQualitative = new HashMap<Feature, Value>();

	List<Column> columns = new ArrayList<Column>();

	public void addColumn(Column column) {
		if (!columns.contains(column)) {
			columns.add(column);
		}
	}

	public void removeColumn(Column column) {
		if (columns.contains(column)) {
			columns.remove(column);
		}
	}

	public void clearData() {
		columns.clear();
	}

	/**
	 * Permet de reucperer toutes les valeurs quantitatives.
	 * {@link Column#getType()}
	 * 
	 * @return
	 */

	/*
	 * public List<List<Number>> getAllQuantitativeValues() { // FIXME - choisir
	 * le bon format de retour selon le besoin List<List<Number>> result = new
	 * ArrayList<>();
	 * 
	 * int i = 0; for (Column column : this.columns) { List<Number> cValues =
	 * new ArrayList<Number>(); if (column.getType().equals(TypeColumn.Number))
	 * { for (ColumnValue value : column.getValues()) { cValues.add((Number)
	 * value.getValue() );
	 * 
	 * } } result.add(cValues); } return result; }
	 */

	public Map<String, List<Number>> getAllQuantitativeValues() {

		Map<String, List<Number>> result = new HashMap<>();

		for (Column column : this.columns) {
			List<Number> cValues = new ArrayList<Number>();
			if (column.getType().equals(TypeColumn.Number)) {
				for (ColumnValue value : column.getValues()) {
					cValues.add((Number) value.getValue());

				}
				result.put(column.getName(), cValues);
			}
		}
		return result;
	}


	/**
	 * Permet de reucperer toutes les valeurs qualitatives.
	 * {@link Column#getType()}
	 * 
	 * @return
	 */
	public List<List<String>> getAllQualitativeValues() {
		// FIXME - choisir le bon format de retour selon le besoin
		List<List<String>> result = new ArrayList<>();

		int i = 0;
		for (Column column : this.columns) {
			List<String> cValues = new ArrayList<String>();
			if (column.getType().equals(TypeColumn.String)) {
				for (ColumnValue value : column.getValues()) {
					cValues.add(value.getValue().toString());
				}
				result.add(cValues);
			}
		}
		return result;
	}

	/**
	 * @param type
	 * @return le nombre de cellule dont le type vaut type
	 */
	public int size(TypeColumn type) {
		int size = 0;
		for (Column column : this.columns) {
			if (column.getType().equals(TypeColumn.Number)) {
				size++;
			}
		}
		return size;
	}

	/**
	 * @return le nombre total de cellule
	 */
	public int size() {
		return columns.size();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (Column c : columns) {
			buffer.append(c + "\n");
		}
		return buffer.toString();
	}

}
	