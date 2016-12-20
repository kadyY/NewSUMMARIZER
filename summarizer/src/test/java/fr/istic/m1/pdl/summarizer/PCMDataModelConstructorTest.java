package fr.istic.m1.pdl.summarizer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
 
import org.junit.Before;
import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import fr.istic.m1.pdl.summarizer.model.DataModel;
import fr.istic.m1.pdl.summarizer.visitor.PCMDataModelConstructor;

public class PCMDataModelConstructorTest 
{
	
	 @Test
	    public void testMyPCM() throws IOException {

	        // Load a PCM
	        File pcmFile = new File("pcms/example.pcm");
	        PCMLoader loader = new KMFJSONLoader();
	        PCM pcm = loader.load(pcmFile).get(0).getPcm();
	        assertNotNull(pcm);

	        // Execute the printer
	    	PCMDataModelConstructor dataConstructor = new PCMDataModelConstructor();
	    	DataModel model = dataConstructor.createDataModel(pcm);
	        System.out.println(model);


	    }

}
