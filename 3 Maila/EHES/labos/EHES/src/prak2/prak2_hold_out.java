package prak2;

//import weka.attributeSelection.BestFirst;
//import weka.attributeSelection.CfsSubsetEval;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;
//import weka.filters.supervised.attribute.AttributeSelection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class prak2_hold_out {

    public static void main(String[] args) throws Exception {
        //argumentuetatik path-ak jaso
        String dataPath = args[0];
        String outputPath = args[1];
        //dataPath = "/home/urkibiris/weka-3-8-6/data/adult.train.arff";
        //datuak kargatu eta klasea zein atributu den adierazi
        ConverterUtils.DataSource source = new ConverterUtils.DataSource(dataPath);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        /*
        //atribute selection filtroa erabili, datu errelebante eta ez erredundanteak mantentzeko
        //filtroa sortu
        AttributeSelection filter = new AttributeSelection();
        //filtrorako ebaluatzailea sortu, dagozkion OPTIONS-ekin
        String[] evalOptions = {"-P", "1", "-E", "1"};
        CfsSubsetEval filterEval = new CfsSubsetEval();
        filterEval.setOptions(evalOptions);
        //filtrorako bilaketa metodoa sortu, dagozkion OPTIONS-ekin
        String[] searchOptions = {"-D", "1", "-N", "5"};
        BestFirst filterSearch = new BestFirst();
        filterSearch.setOptions(searchOptions);
        //ebaluatzailea eta bilaketa metodoa filtroari ezarri
        filter.setEvaluator(filterEval);
        filter.setSearch(filterSearch);
        filter.setInputFormat(data);
        //filtroa aplikatu
        Instances newData = Filter.useFilter(data, filter);
        */

        //EREDU IRAGARLEA INFERITU
        //ML algoritmoa aukeratu
        NaiveBayes nb = new NaiveBayes();

        //hold-out metodoaren arabera, datuak bi zatitan banatu: train (%70) eta test (%30)
        //train
        int seed = 1;
        Resample resample = new Resample();
        resample.setRandomSeed(seed);
        resample.setBiasToUniformClass(0.0);
        resample.setSampleSizePercent(80.0);
        resample.setNoReplacement(true);
        resample.setInputFormat(data);
        Instances trainData = Filter.useFilter(data, resample);
        //test
        resample.setInvertSelection(true);
        resample.setInputFormat(data);
        Instances testData = Filter.useFilter(data, resample);
        nb.buildClassifier(trainData);

        /*EREDU IRAGARLEA INFERITU BESTE ERA BATEAN
        //hold-out metodoaren arabera, datuak bi zatitan banatu: train (%70) eta dev (%30)
        int seed = 1;
        float trainSizeRatio = 0.7f;
        // int folds = 10;
        Random rand = new Random(seed); //seed=1 berdina izan dadin exekuzio guztietan
        Instances randData = new Instances(newData);
        randData.randomize(rand);
        int trainSize = (int) Math.round(randData.numInstances() * trainSizeRatio);
        int testSize = randData.numInstances() - trainSize;
        Instances trainData = new Instances(randData, 0, trainSize);
        Instances testData = new Instances(randData, trainSize, testSize);
        nb.buildClassifier(trainData);
        */

        //EBALUATU

        Evaluation eval = new Evaluation(trainData);
        eval.evaluateModel(nb, testData);

        //EMAITZAK ESKURATU eta EXPORTATU
        try {
            File output = new File(outputPath);
            if (!output.exists()) output.createNewFile();
            FileWriter fw = new FileWriter(output, true);
            BufferedWriter bw = new BufferedWriter(fw);

            //exekuzio data
            bw.write("\nExekuzio Data: --> ");
            bw.write(java.time.LocalDate.now().toString());

            //Laburpena
            String summary = eval.toSummaryString("\nResults\n======\n", false);
            bw.write(summary);

            //klase minoritarioari dagokion Precision, Recall eta F-score
            int minorityClassIndex = 0;
            int minCount = Integer.MAX_VALUE;
            int classCount;
            for (int i = 0; i < data.classAttribute().numValues(); i++) {
                classCount = data.classAttribute().value(i).length();
                if (classCount < minCount) {
                    minCount = classCount;
                    minorityClassIndex = i;
                }
            }
            String minorityClassValue = data.classAttribute().value(minorityClassIndex);
            bw.write("\nPrecision for class " + minorityClassValue + ": " + eval.precision(minorityClassIndex));
            bw.write("\nRecall for class " + minorityClassValue + ": " + eval.recall(minorityClassIndex));
            bw.write("\nF-Score for class " + minorityClassValue + ": " + eval.fMeasure(minorityClassIndex) + "\n");

            //exekuzio argumentuak
            bw.write("\nExekuzio Argumentuak: \ndataPath="+dataPath+"\noutputPath="+outputPath+"\n");

            //TODO : EBALUAZIO EMAITZAK

            bw.close();
            fw.close();
            System.out.println("Emaitzak ondo idatzi dira: " + outputPath);
        } catch (Exception e) {
            System.out.println("ERROR: Ezin izan dira emaitzak idatzi");
            System.out.println(e.getMessage());
        }
    }
}
