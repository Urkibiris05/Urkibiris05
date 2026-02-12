package praktika3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

public class iragarpenakEgin {
	public static void main(String[] args) throws Exception{
		String modelPath = args[0];
		String testPath = args[1];
		String predictionsPath = args[2];
		
		DataSource source = new DataSource(testPath);
		Instances testData = source.getDataSet();
		if (testData.classIndex() == -1) testData.setClassIndex(testData.numAttributes()-1);
		
		Classifier cls = (Classifier) SerializationHelper.read(modelPath);
		
		File predictions = new File(predictionsPath);
		if (!predictions.exists()) predictions.createNewFile();
		BufferedWriter bf = new BufferedWriter(new FileWriter(predictions, true));
		bf.write("\n--------------- PREDICTIONS -----------------------");
		
		Instances dummy = source.getStructure();
		if (dummy.classIndex() == -1) dummy.setClassIndex(dummy.numAttributes()-1);
		Evaluation eval = new Evaluation(dummy);
		for (int i=0; i<testData.numInstances(); i++) {
			double classIndex = eval.evaluateModelOnceAndRecordPrediction(cls, testData.instance(i));
			double realClassIndex = testData.instance(i).classValue();

			int j = i+1;
			if (testData.classAttribute().isNominal()) {
				bf.write("\nPrediction for instance "+j+" --> "+testData.classAttribute().value((int)classIndex)+";"
						+ "and the real class was -->"+testData.classAttribute().value((int) realClassIndex));
			} else {
				bf.write("\nPrediction for instance "+j+" --> "+classIndex+";"
						+ "and the real class was -->"+realClassIndex);
			}		
		}
		bf.close();
	}
}
