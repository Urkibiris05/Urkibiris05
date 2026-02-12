package praktika2;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.filters.Filter;

public class holdout {
	public static void main(String[] args) throws Exception {
		String dataPath = args[0];
		String outputPath = args[1];
		
		//datuak kargatu
		DataSource source = new DataSource(dataPath);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) data.setClassIndex(data.numAttributes()-1);
		
		Randomize randomize = new Randomize();
		randomize.setSeed(1);
		randomize.setInputFormat(data);
		Instances newData = Filter.useFilter(data, randomize);
		
		
		RemovePercentage removePercentage = new RemovePercentage();
		removePercentage.setPercentage(66.0);
		removePercentage.setInputFormat(newData);
		Instances testData = Filter.useFilter(newData, removePercentage);
		
		removePercentage.setInvertSelection(true);
		removePercentage.setInputFormat(newData);
		Instances trainData = Filter.useFilter(newData, removePercentage);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(trainData);
		
		Evaluation eval = new Evaluation(newData);
		eval.evaluateModel(nb, testData);
		
		File output = new File(outputPath);
		if (!output.exists()) output.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(output, true));
		
		bf.write("\nExekuzio data --> "+java.time.LocalDate.now().toString());
		bf.write("\nSarrera datuen path-a --> "+dataPath);
		bf.write("\nIrteera fitxategiaren path-a --> "+outputPath);
		
		String summary = eval.toSummaryString("\n=======Summary========\n", false);
		bf.write(summary);
		
		bf.write("\n======Nahasmen Matrizea========\n");
		bf.write(eval.toMatrixString());
		
		int minCount = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i<testData.classAttribute().numValues(); i++) {
			int classCount = testData.classAttribute().value(i).length();
			if (classCount < minCount) {
				minIdx = i;
				minCount = classCount;
			}
		}
		bf.write("\nKlase minoritarioa --> "+testData.classAttribute().value(minIdx));
		bf.write("\nPrecision --> "+eval.precision(minIdx));
		bf.write("\nRecall --> "+eval.recall(minIdx));
		bf.write("\nF-score --> "+eval.fMeasure(minIdx));
		bf.write("\nWavg precision --> "+eval.weightedPrecision());
		bf.write("\nWavg Recall --> "+eval.weightedRecall());
		bf.write("\nWavg f-score --> "+eval.weightedFMeasure());
		bf.close();
	}
}
