package praktika3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Resample;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

public class ereduaSortu {
	public static void main(String args[]) throws Exception{
		String dataPath = args[0];
		String modelPath = args[1];
		String estimationPath = args[2];
		DataSource source = new DataSource(dataPath);
		Instances data = source.getDataSet();
		if (data.classIndex() == -1) data.setClassIndex(data.numAttributes()-1);
		
		NaiveBayes model = new NaiveBayes();
		model.buildClassifier(data);
		SerializationHelper.write(modelPath, model);
		
		//KALITATEAREN ESTIMAZIOA
		//kfCV
		model = new NaiveBayes();
		int seed = 1; 
		int k = 10;
		Evaluation kfCV = new Evaluation(data);
		kfCV.crossValidateModel(model, data, k, new Random(1));
		
		//holdout
		model = new NaiveBayes();
		Randomize rand = new Randomize();
		rand.setSeed(seed);
		rand.setInputFormat(data);
		Instances randData = Filter.useFilter(data, rand);
		RemovePercentage rp = new RemovePercentage();
		rp.setPercentage(70.0);
		rp.setInvertSelection(true);
		rp.setInputFormat(randData);
		Instances trainData = Filter.useFilter(randData, rp);
		rp.setInvertSelection(false);
		Instances testData = Filter.useFilter(randData, rp);
		
		model.buildClassifier(trainData);
		Evaluation holdoutrp = new Evaluation(randData);
		holdoutrp.evaluateModel(model, testData);
		
		model = new NaiveBayes();
		Resample resample = new Resample();
		resample.setSeed(seed);
		//resample.setBiasToUniformClass(0.0);
		resample.setSampleSizePercent(70.0);
		//resample.setNoReplacement(true);
		resample.setInputFormat(data);
		trainData = Filter.useFilter(data, resample);
		resample.setInvertSelection(true);
		testData = Filter.useFilter(data, resample);
		
		model.buildClassifier(trainData);
		Evaluation holdoutresample = new Evaluation(data);
		holdoutresample.evaluateModel(model, testData);
		
		File estimation = new File(estimationPath);
		if (!estimation.exists()) estimation.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(estimation, true));
		bf.write("\nExekuzio data --> "+java.time.LocalDate.now().toString());
		bf.write("\nArgumentuak: \ndataPath --> "+dataPath+"; \nmodelPath --> "+modelPath+"; \nestimation --> "+estimationPath);
		String cm = kfCV.toMatrixString("\n ------------ kfCV Nahasmen Matrizea --------------\n");
		bf.write(cm);
		cm = holdoutrp.toMatrixString("\n ------------ holdoutrp Nahasmen Matrizea --------------\n");
		bf.write(cm);
		cm = holdoutresample.toMatrixString("\n ------------ holdoutresample Nahasmen Matrizea --------------\n");
		bf.write(cm);
		bf.close();
		System.out.println("Programa arazorik gabe exekutatu da!");
	}
}
