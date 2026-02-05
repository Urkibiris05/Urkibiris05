package prak3;

import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.Instance;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.SerializationHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class IragarpenakEgin {

    public static void main (String[] args) throws Exception {
        String modelPath = args[1];
        String testDataPath = args[0];
        String outputPath = args[2];

        DataSource source = new DataSource(testDataPath);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) data.setClassIndex(data.numAttributes()-1);

        NaiveBayes nb = (NaiveBayes) SerializationHelper.read(modelPath);


        try{
            File output = new File(outputPath);
            if (!output.exists()) output.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.write("\nPredictions for each instance:\n");
            double result;
            String className;
            int j = 1;
            for (Instance i : data){
                result = nb.classifyInstance(i);
                className = data.classAttribute().value((int) result);
                bw.write("\nPrediction for instance "+j+" --> "+className);
                j++;

            }
            bw.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
