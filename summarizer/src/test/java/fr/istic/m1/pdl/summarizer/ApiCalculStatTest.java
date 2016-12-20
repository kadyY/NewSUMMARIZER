package fr.istic.m1.pdl.summarizer;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.pdl.summarizer.model.DataModel;
 

public class ApiCalculStatTest {


    DataModel model = new DataModel();

	@Before
	public void initialize()  {
		
		ApiCalculStat ApiCalculStat = new ApiCalculStat(model);
	}

	 
 
	@Test
	public void testgetMaximum()
	{
		Map<String, List<Number>> list= model.getAllQuantitativeValues();
	    ApiCalculStat.getMaximum((List<Number>) list);
    }

	
	@Test
	public void testgetMiniimum() 
	{
		
 
	}
	
	
	@Test
	public void testgetAverage() 
	{
	 
	}
	
}
