package fr.istic.m1.pdl.summarizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.istic.m1.pdl.summarizer.model.DataModel;

public class ApiCalculStat {

	DataModel model;

	
 	public ApiCalculStat(DataModel mod) {

		this.model = mod;

	}

	/**
    *
    * @param list
    * List<Number>
    * @return Double minimum
    */
	 
	public static double getMinimum(List<Number> list) {
		int size = list.size();
		double min = list.get(0).doubleValue();
		for (int i = 0; i < size; i++) {
			if (list.get(i).doubleValue() < min) {
				min = list.get(i).doubleValue();
			}
		}
		return min;
	}
	
	/**
    *
    * @param list
    * List<Number>
    * @return Double maximum
    */
	public static double getMaximum(List<Number> list) {
		int size = list.size();
		double max = list.get(0).doubleValue();
		for (int i = 0; i < size; i++) {
			if (list.get(i).doubleValue() > max) {
				max = list.get(i).doubleValue();
			}
		}
		return max;
	}

	/**
    *
    * @param list
    * List<Number>
    * @return Double moyenne
    */
	public static double getAverage(List<Number> list) {
		int size = list.size();
		double total = 0;
		for (int i = 0; i < size; i++) {
			total = total + list.get(i).doubleValue();
		}
		double average = total / size;

		return average;
	}

	/**
    *
    * @param list
    * List<Number>
    * @param list2
    * List<Number>
    * @return Double Coefficent de Correlation
    */

	public static double CoefficentCorrelation(List<Number> list,
			List<Number> list2) throws IllegalArgumentException {

		// precondition : list.size == list2.size
		if (list.size() != list2.size()) {
			throw new IllegalArgumentException("Les deux features n'ont pas le meme nombre d'elements");
		}

		double sum_x = 0, sum_y = 0, sum_xx = 0, sum_yy = 0, sum_zz = 0, sum_xxbarre2 = 0, sum_yybarre2 = 0;
		double xbarre = 0, ybarre = 0;
		double covariance = 0, ecartypex = 0, ecartypey = 0, resultat = 0, coefficientcorrelation = 0;

		ArrayList<Integer> xxbarre = new ArrayList<Integer>(); // (xi-xbarre)
		ArrayList<Integer> yybarre = new ArrayList<Integer>(); // (yi-ybarre)
		ArrayList<Integer> xybarre = new ArrayList<Integer>(); // (xi-xbarre)*(yi-ybarre)
		ArrayList<Integer> xxbarre2 = new ArrayList<Integer>();// (xi-xbarre)²
		ArrayList<Integer> yybarre2 = new ArrayList<Integer>();// (yi-ybarre)²
		for (int i = 0; i < list.size(); i++) {
			sum_x += list.get(i).intValue(); // la somme des xi 30
			sum_y += list2.get(i).intValue(); // la somme des yi 90

			xbarre = sum_x / list.size(); // la moyenne des xi xbarre 10
			ybarre = sum_y / list2.size(); // la moyenne des yi ybarre 30
		}

		for (int i = 0; i < list.size(); i++) {

			xxbarre.add((int) (list.get(i).intValue() - xbarre)); // calculer les (xi-xbarre)
			yybarre.add((int) (list2.get(i).intValue() - ybarre)); // calculer les (yi-ybarre)
			xxbarre2.add((int) Math.pow(list.get(i).intValue() - xbarre, 2)); // calculer les (xi-xbarre)²
			yybarre2.add((int) Math.pow(list2.get(i).intValue() - ybarre, 2)); // calculer les (yi-ybarre)²
			xybarre.add(xxbarre.get(i) * yybarre.get(i)); // calculer les (xi-xbarre)*(yi-ybarre)
			sum_xx += xxbarre2.get(i); // la somme des xxbarre2 50
			sum_yy += yybarre2.get(i); // la somme des yybarre2 800
			sum_zz += xybarre.get(i); // la somme des xybarre 200 */

		}

		covariance = sum_zz / list.size(); // la covariance
		ecartypex = Math.sqrt(sum_xx / list.size()); // l'ecart type x
		ecartypey = Math.sqrt(sum_yy / list.size());// l'ecart type y
		resultat = ecartypex * ecartypey; // l'ecart typex * l'ecart typey
		coefficientcorrelation = covariance / resultat; // le coefficient de correlation

		return coefficientcorrelation;

	}
 
	/**
     * @param list List<String> 
     * @return hashMap avec la valeur string comme clé  et 	le nombre de repetition d'une occurrence comme valeur
     */
	
	public static HashMap<String, Integer> getOccurrence(List<String> list) {
		HashMap<String, Integer> hash = new HashMap<String, Integer>();

		for (String value : list) {
			if(hash.containsKey(value)){
				hash.put(value, hash.get(value) + 1);
			}else{
				hash.put(value, 1);
			}
		}
		return hash;
	}

	   
	/**
     * @param list
     * List<Boolean>
     * @return percent
     * Return le pourcentage de true dans une feature de type Boolean
     */
	public static double getTruePercent(List<Boolean> list) {
		int taille = list.size();
		int nbTrue = 0;
		for (int i = 0; i < taille; i++) {
			if (list.get(i) == true) {
				nbTrue++;
			}
		}
		double percent = (nbTrue / taille) * 100;

		return percent;
	}

	/**
     * @param list
     * List<Boolean>
     * @return percent
     * Return le pourcentage de false dans une feature de type Boolean
     */
	public static double getFalsePercent(List<Boolean> list) {
		int taille = list.size();
		int nbFalse = 0;
		for (int i = 0; i < taille; i++) {
			if (list.get(i) == false) {
				nbFalse++;
			}
		}
		double percent = (nbFalse / taille) * 100;

		return percent;
	}

}
