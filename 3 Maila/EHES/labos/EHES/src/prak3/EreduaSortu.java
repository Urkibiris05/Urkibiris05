package prak3;

import weka.classifiers.evaluation.ConfusionMatrix;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
import weka.core.SerializationHelper;

import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class EreduaSortu {

    public static void main (String[] args) throws Exception {

        String dataPath = args[0];
        String modelOutputPath = args[1];
        String estimationOutputPath = args[2];

        DataSource source = new DataSource(dataPath);
        Instances data = source.getDataSet();
        if (data.classIndex()==-1) data.setClassIndex(data.numAttributes()-1);

        //eredu iragarlea inferitu
        NaiveBayes nb = new NaiveBayes();
        //ebaluazioak egin
        //kCFV
        Evaluation kfCVeval = new Evaluation(data);
        kfCVeval.crossValidateModel(nb, data, 10, new Random(1));

        //hold-out
        nb = new NaiveBayes();
        Resample resample = new Resample();
        resample.setSampleSizePercent(70);
        resample.setBiasToUniformClass(0.0);
        resample.setNoReplacement(true);
        resample.setRandomSeed(1);
        resample.setInputFormat(data);
        Instances trainData = Filter.useFilter(data, resample);

        resample.setInvertSelection(true);
        resample.setInputFormat(data);
        Instances testData = Filter.useFilter(data, resample);

        nb.buildClassifier(trainData);
        Evaluation holdoutEval = new Evaluation(trainData);
        holdoutEval.evaluateModel(nb, testData);

        //eredua datu guztiekin entrenatu, bezeroari emateko
        nb.buildClassifier(data);
        try {
            File output = new File(estimationOutputPath);
            if (!output.exists()) output.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(output, true));

            //exekuzio data
            bw.write("\nExekuzio Data --> ");
            bw.write(java.time.LocalDate.now().toString());
            //argumentuak
            bw.write("\nData path --> " + dataPath);
            bw.write("\nModel output path --> "+modelOutputPath);
            bw.write("\nEstimations output path --> "+estimationOutputPath);

            //kfCV
            bw.write(kfCVeval.toSummaryString("\n======kfCV Emaitzak======\n", false));
            bw.write(kfCVeval.toMatrixString("\n"));

            //holdout
            bw.write(holdoutEval.toSummaryString("\n=======Holdout Emaitzak=======\n", false));
            bw.write(holdoutEval.toMatrixString("\n"));
            bw.close();

            SerializationHelper.write(modelOutputPath, nb);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
