package fr.istic.m1.pdl.summarizer.visitor;

import org.opencompare.api.java.AbstractFeature;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.FeatureGroup;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.Value;
import org.opencompare.api.java.util.PCMVisitor;
import org.opencompare.api.java.value.BooleanValue;
import org.opencompare.api.java.value.Conditional;
import org.opencompare.api.java.value.DateValue;
import org.opencompare.api.java.value.Dimension;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.Multiple;
import org.opencompare.api.java.value.NotApplicable;
import org.opencompare.api.java.value.NotAvailable;
import org.opencompare.api.java.value.Partial;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.api.java.value.StringValue;
import org.opencompare.api.java.value.Unit;
import org.opencompare.api.java.value.Version;

import fr.istic.m1.pdl.summarizer.model.Column;
import fr.istic.m1.pdl.summarizer.model.ColumnValue;
import fr.istic.m1.pdl.summarizer.model.DataModel;
import fr.istic.m1.pdl.summarizer.model.Column.TypeColumn;

public class PCMDataModelConstructor implements PCMVisitor {

	private DataModel model;
	private Column curretFeatures;
	private ColumnValue CurrentCell;
	
	private boolean valueTypeFund = false;
	/**
	 * l'ordre de la valeur dans la cellule
	 */
	private int order = 0;

	public DataModel createDataModel(PCM pcm) {
		model = new DataModel();
		pcm.accept(this);

		return model;

	}

	public void visit(PCM pcm) {
		for (AbstractFeature feature : pcm.getFeatures()) {
			feature.accept(this);
		}
	}

	public void visit(Feature feature) {
		//on initialise l'ordre 
		order = 0;
		
		curretFeatures = new Column(feature.getName());
		for (Cell cell : feature.getCells()) {
			cell.accept(this);
			order++;
		}
		model.addColumn(curretFeatures);
	}

	public void visit(FeatureGroup featureGroup) {
		// TODO Auto-generated method stub
		for (AbstractFeature feature : featureGroup.getFeatures()) {
			feature.accept(this);
		}

	}

	public void visit(Product product) {
	}

	public void visit(Cell cell) {
		CurrentCell = new ColumnValue(cell.getContent(), order);
		
		valueTypeFund = false;
		
		curretFeatures.addValue(CurrentCell);
		
		Value interpretation = cell.getInterpretation();

		if (interpretation != null) {
			interpretation.accept(this);
		}
		
		/**
		 * Si le type de contenu n'est pas parcouru, on recupere la valeur en String
		 */
		if(!valueTypeFund){
			curretFeatures.setType(TypeColumn.String);
			CurrentCell.setValue(cell.getContent());
		}

	}

	public void visit(BooleanValue booleanValue) {}

	public void visit(Conditional conditional) {}

	public void visit(DateValue dateValue) {}

	public void visit(Dimension dimension) {}

	public void visit(IntegerValue integerValue) {
		valueTypeFund = true;
		CurrentCell.setValue(integerValue.getValue());
	}

	public void visit(Multiple multiple) {
		curretFeatures.setType(TypeColumn.String);
		
		//FIXME - que faire des valeurs multiples ???
		/**
		 * on ajoute chaque sub value à part avec un mm ordre
		 */
		for(Value value : multiple.getSubValues()){
			value.accept(this);
		}

	}

	public void visit(NotApplicable notApplicable) {
		// TODO Auto-generated method stub
	}

	public void visit(NotAvailable notAvailable) {
		// TODO Auto-generated method stub

	}

	public void visit(Partial partial) {}

	public void visit(RealValue realValue) {
		valueTypeFund = true;
		CurrentCell.setValue(realValue.getValue());
	}

	public void visit(StringValue stringValue) {}

	public void visit(Unit unit) {}

	public void visit(Version version) {}

}
