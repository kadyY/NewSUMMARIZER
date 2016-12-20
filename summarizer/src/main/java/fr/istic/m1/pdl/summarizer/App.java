package fr.istic.m1.pdl.summarizer;
import java.lang.Object;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

import fr.istic.m1.pdl.summarizer.model.DataModel;
import fr.istic.m1.pdl.summarizer.visitor.PCMDataModelConstructor;

public class App {
	public static void main(String[] args) {
		// Load a PCM
				File pcmFile = new File("pcms/example.pcm");
				PCMLoader loader = new KMFJSONLoader();
				try {
					PCM pcm = loader.load(pcmFile).get(0).getPcm();
					
					//looding a dataModel from pcm
					PCMDataModelConstructor dataConstructor = new PCMDataModelConstructor();
					DataModel model = dataConstructor.createDataModel(pcm);
					 
					
					
					// retourner la liste des features
					System.out.println("La liste des features");
					 HashMap<Integer, Feature> listeFeatures = new HashMap<>();
				        int Pfeature = 0;
				        for (Feature feature : pcm.getConcreteFeatures()) {
				        	Pfeature++;
				        	listeFeatures.put(Pfeature, feature);
				        	
				        	System.out.println(Pfeature + ": "+ feature.getName());
				        }
				        
					//utiliser le model pour les calculs statistiques ici
					Map<String, List<Number>> list= model.getAllQuantitativeValues();
					ApiCalculStat b =new ApiCalculStat(model);
					for(String columnName : list.keySet()){
						List<Number> item = list.get(columnName);
						double min = b.getMinimum(item);
						double max = b.getMaximum(item);
						double moy = b.getAverage(item);
						
						 
     					System.out.println("Col "+ columnName + " : ");
						System.out.println(" \tle minimum est "+ min);
						System.out.println(" \tle max est "+ max);
						System.out.println(" \tla moy est "+ moy);
						
					}
					
					
					List<List<String>> listQ = model.getAllQualitativeValues();
					for(List<String> current : listQ){
						HashMap<String, Integer> occurences = b.getOccurrence(current); 
						System.out.println("Nombre d'occurences "+ occurences) ;
					}
					
					
					for (String name1 : list.keySet()) {
						for(String name2 : list.keySet()) {
							if(!name1.equals(name2)){
								double coef = b.CoefficentCorrelation(list.get(name1), list.get(name2));
								System.out.println("le coefficient de correlation entre  " + name1 + " et " + name2 +" : " + coef);
							}
						}
					}
					 
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 
	
		
	}

