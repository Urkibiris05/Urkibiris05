package prak2;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.classifiers.Evaluation;
import weka.core.converters.ArffSaver;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.time.LocalDate;

public class prak2_stratified_hold_out {
    public static void main(String[] args) throws Exception {
        //datuak kargatu
        String dataPath = args[0];
        String trainPath = args[1];
        String testPath = args[2];
        String outputPath = args[3];
        DataSource source = new DataSource(dataPath);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }


        //datu sorta banatu: 80/20
        int seed = 1;
        Resample resample = new Resample();
        resample.setRandomSeed(seed);
        resample.setBiasToUniformClass(0.0);
        resample.setSampleSizePercent(80.0);
        resample.setNoReplacement(true);
        //resample.setInvertSelection(false);
        resample.setInputFormat(data);
        Instances trainData = Filter.useFilter(data, resample);
        ArffSaver saver = new ArffSaver();
        saver.setInstances(trainData);
        File outputTrain = new File(trainPath);
        if (!outputTrain.exists()) outputTrain.createNewFile();
        saver.setFile(outputTrain);
        saver.writeBatch();

        resample.setInvertSelection(true);
        resample.setInputFormat(data);
        Instances testData = Filter.useFilter(data, resample);
        saver = new ArffSaver();
        saver.setInstances(testData);
        File outputTest = new File(testPath);
        if (!outputTest.exists()) outputTest.createNewFile();
        saver.setFile(outputTest);
        saver.writeBatch();

        evaluate(new String[]{trainPath, testPath, outputPath});
    }


    static void evaluate(String[] args) throws Exception{

        DataSource trainSource = new DataSource(args[0]);
        Instances trainData = trainSource.getDataSet();
        if (trainData.classIndex() == -1) {
            trainData.setClassIndex(trainData.numAttributes() - 1);
        }
        DataSource testSource = new DataSource(args[1]);
        Instances testData = testSource.getDataSet();
        if (testData.classIndex() == -1) {
            testData.setClassIndex(testData.numAttributes() - 1);
        }

        //eredu iragarlea inferitu
        NaiveBayes nb = new NaiveBayes();
        //eredua entrenatu/inferitu
        nb.buildClassifier(trainData);
        //Ebaluatu
        Evaluation eval = new Evaluation(trainData);
        eval.evaluateModel(nb, testData);

        File output = new File(args[2]);
        if (!output.exists()) output.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));
        //exekuzio data
        writer.write("\nExekuzio Data: --> ");
        writer.write(LocalDate.now().toString());
        //exekuzioaren argumentuak
        writer.write("\ntrain data: "+args[1]);
        writer.write("\ntest data: "+ args[2]);
        //laburpena
        String summary = eval.toSummaryString("\nResults\n========", false);
        writer.write(summary);
        //nahasmen matrizea
        double[][] nahasmenMatrizea = eval.confusionMatrix();
        writer.write("\nTP  FN  TN  FP\n");
        for (int i=0; i<nahasmenMatrizea.length; i++){
            for (int j=0; j<nahasmenMatrizea.length; j++){
                writer.write(nahasmenMatrizea[i][j] +" ");
            }
        }
        //precision klase bakoitzerako eta weighted avg
        for (int i = 0; i < trainData.numClasses(); i++) {
            writer.write("\nPrecision for class ");
            writer.write(trainData.classAttribute().value(i));
            writer.write(" --> ");
            writer.write(String.valueOf(eval.precision(i)));
        }
        writer.write("\nPrecision weighted avg --> ");
        writer.write(String.valueOf(eval.weightedPrecision()));
        writer.write("\n");

        writer.close();

    }
}
