package praktika2;

import java.util.Arrays;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

public class repeatedHoldout {
	public static void main(String[] args) throws Exception{
		String dataPath = args[0];
		String output = args[1];
		
		DataSource source = new DataSource(dataPath);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1 ) data.setClassIndex(data.numAttributes() -1);
		
		NaiveBayes nb;
		Evaluation eval = new Evaluation(data);
		Randomize randomize;
		int seed = 1;
		RemovePercentage removePercentage;
		float percentage = 66.0f;
		
		Instances randData;
		Instances trainData;
		Instances testData;
		
		double[] recalls = new double[50];
		
		for (int i=0; i<50; i++) {
			randomize = new Randomize();
			randomize.setSeed(seed);
			randomize.setInputFormat(data);
			randData = Filter.useFilter(data, randomize);
			
			removePercentage = new RemovePercentage();
			removePercentage.setPercentage(percentage);
			removePercentage.setInputFormat(randData);
			testData = Filter.useFilter(randData, removePercentage);
			
			removePercentage.setInvertSelection(true);
			removePercentage.setInputFormat(randData);
			trainData = Filter.useFilter(randData, removePercentage);
			
			nb = new NaiveBayes();
			nb.buildClassifier(trainData);
			eval.evaluateModel(nb, testData);
			
			//klase minoritarioa lortu
			int minidx = 0;
			int classIndex = trainData.classIndex();
			int[] counts = trainData.attributeStats(classIndex).nominalCounts;
			for (int j=0; j<counts.length; j++) {
				for (int count : counts) {
					if (count < counts[minidx]) minidx = j;	
				}
			}
			Evaluation unekoEval = new Evaluation(trainData);
			unekoEval.evaluateModel(nb, testData);
			recalls[i] = unekoEval.recall(minidx);
			seed++;
		}
		//klase minoritarioa lortu
		int minidx = 0;
		int classIndex = data.classIndex();
		int[] counts = data.attributeStats(classIndex).nominalCounts;
		for (int j=0; j<counts.length; j++) {
			for (int count : counts) {
				if (count < counts[minidx]) minidx = j;	
			}
		}
		
		double meanRecall = (Arrays.stream(recalls).sum()/recalls.length);
		double sumSq = 0.0;
		for (double value : recalls) {
			sumSq += Math.pow(value - meanRecall, 2);
		}
		double stdev = Math.sqrt(meanRecall / recalls.length);
		System.out.println("Recall (eval akumulatuarekin) --> "+eval.recall(minidx));
		System.out.println("Recall batazbestekoa --> "+meanRecall);
		System.out.println("Stdev --> "+stdev);
	}
}
