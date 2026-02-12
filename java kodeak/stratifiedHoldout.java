package praktika2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;

public class stratifiedHoldout {
	public static void main (String[] args) throws Exception {
		String[] args1 = new String[] {args[0], args[1], args[2]};
		String[] args2 = new String[] {args[1], args[2], args[3]};
		splitDataset(args1);
		evaluate(args2);
	}
	
	static void splitDataset(String[] args) throws Exception{
		String dataPath = args[0];
		String trainPath = args[1];
		String testPath = args[2];
		
		DataSource source = new DataSource(dataPath);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) data.setClassIndex(data.numAttributes()-1);
		
		int seed = 1;
		float percentage = 80.0f;
		boolean noReplacement = true;
		Resample resample = new Resample();
		resample.setSeed(seed);
		resample.setBiasToUniformClass(0.0);
		resample.setSampleSizePercent(percentage);
		resample.setNoReplacement(noReplacement);
		resample.setInputFormat(data);
		Instances trainData = Filter.useFilter(data, resample);
		
		resample.setInvertSelection(true);
		resample.setInputFormat(data);
		Instances testData = Filter.useFilter(data, resample);
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(trainData);
		File outputTrain = new File(trainPath);
		if (!outputTrain.exists()) outputTrain.createNewFile();
		saver.setFile(outputTrain);
		saver.writeBatch();
		
		saver = new ArffSaver();
		saver.setInstances(testData);
		File outputTest = new File(testPath);
		if (!outputTest.exists()) outputTest.createNewFile();
		saver.setFile(outputTest);
		saver.writeBatch();
	}
	
	static void evaluate(String[] args) throws Exception {
		String trainPath = args[0];
		String testPath = args[1];
		String outputPath = args[2];
		
		DataSource trainSource = new DataSource(trainPath);
		DataSource testSource = new DataSource(testPath);
		Instances trainData = trainSource.getDataSet();
		if (trainData.classIndex() == -1) trainData.setClassIndex(trainData.numAttributes()-1);
		Instances testData = testSource.getDataSet();
		if (testData.classIndex() == -1) testData.setClassIndex(testData.numAttributes()-1);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(trainData);
		
		Evaluation eval = new Evaluation(trainData);
		eval.evaluateModel(nb, testData);
		
		File output = new File(outputPath);
		if (!output.exists()) output.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(output));
		bf.write("\nExekuzio data --> "+java.time.LocalDate.now().toString());
		bf.write("\nExekuzio argumentuak: \ntrainPath --> "+trainPath+"\ntestPath --> "+testPath);
		bf.write(eval.toMatrixString("\n--------- Nahasmen Matrizea ----------\n"));
		bf.write("\nWavg accuracy --> "+eval.pctCorrect());	
	}
}
