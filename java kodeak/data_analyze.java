package praktika1;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import weka.core.Attribute;


public class data_analyze {

	public static void main(String[] args) throws Exception {
		String path = args[0];
		DataSource source = new DataSource(path);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) data.setClassIndex(data.numAttributes() - 1);
		
		// fitxategiaren path-a imprimatu
		System.out.println(path);
		//instantzia kopurua
		System.out.println("Instantzia kopurua --> "+data.numInstances());
		//atributu kopurua
		System.out.println("Atributu kopurua --> "+data.numAttributes());
		// lehenengo/azken atributuak har ditzakeen balio ezberdinak 
		Attribute attr = data.attribute(12);
		System.out.println("\nAtributu #0 - izena: " +attr.name() + " - mota: " + (attr.isNominal() ? "nominal":"numeric"));
		System.out.println("Balioak: ");
		if (attr.isNominal()) {
			for (int i=0; i<attr.numValues(); i++) {
				int j = i+1;
				System.out.println("--> "+j+": "+attr.value(i));
			}
		} else {
			Map<String, Integer> counts = new LinkedHashMap<>();
			Set<String> distinct = new LinkedHashSet<>();
			for (int i=0; i<data.numInstances(); i++) {
				Instance instance = data.instance(i);
				double v = instance.value(attr);
				String key = Double.toString(v);
				counts.put(key,  counts.getOrDefault(key,0) + 1);
				distinct.add(key);
			}
			for (String s: counts.keySet()) {
				System.out.println("--> "+s+": "+counts.get(s));
				System.out.println(distinct.size()+ " balio ezberdin");
				
			}
				

		}
		//azken aurreko atributuak dituen missing value kopurua
		attr = data.attribute(data.numAttributes()-2);
		int missing = 0;
		for (int i=0; i<data.numInstances(); i++) {
			Instance inst = data.instance(i);
			if(inst.isMissing(attr)) missing++;
		}
		System.out.println("Azken aurreko atributuak dituen missing value kopurua: "+missing);
		
		
		
	}
}
