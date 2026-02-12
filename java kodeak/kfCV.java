package praktika2;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;

import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class kfCV {
	public static void main(String[] args) throws Exception {
		String dataPath = args[0];
		String outputPath = args[1];
		
		// Datuak kargatu
		DataSource source = new DataSource(dataPath);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) data.setClassIndex(data.numAttributes()-1);
		
		// Eredua inferitu eta ebaluatu
		
		NaiveBayes nb = new NaiveBayes();
		
		Evaluation eval = new Evaluation(data);
		int seed = 1;
		int k = 5;
		eval.crossValidateModel(nb, data, k, new Random(seed));
		
		File output = new File(outputPath);
		if (!output.exists()) output.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(output, true));
		String summary = eval.toSummaryString("\n=======Summary========\n", false);
		bf.write(summary);
		
		bf.write("\n======Nahasmen Matrizea========\n");
		bf.write(eval.toMatrixString());
		
		bf.write("\n=======Precision=======");
		for (int i=0; i<data.classAttribute().numValues(); i++) {
			bf.write("\nKlasea --> "+data.classAttribute().value(i)+" ; Precision --> "+eval.precision(i));
		}
		bf.write("\nWeighted avg precision --> "+eval.weightedPrecision());
		
		bf.write("\nExekuzio data --> "+java.time.LocalDate.now().toString());
		bf.write("\nSarrera datuen path-a --> "+dataPath);
		bf.write("\nIrteera fitxategiaren path-a --> "+outputPath);
		bf.close();
	}
}
