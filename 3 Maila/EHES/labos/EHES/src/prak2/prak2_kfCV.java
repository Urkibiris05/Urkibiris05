package prak2;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.BestFirst;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;


public class prak2_kfCV {

    public static void main(String[] args) throws Exception {
        //argumentuetatik path-ak jaso
        String dataPath = args[0];
        String outputPath = args[1];
        //dataPath = "/home/urkibiris/weka-3-8-6/data/adult.train.arff";
        //datuak kargatu eta klasea zein atributu den adierazi
        DataSource source = new DataSource(dataPath);
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

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

        //EREDU IRAGARLEA INFERITU
        //ML algoritmoa aukeratu
        NaiveBayes nb = new NaiveBayes();
        //kasu honetan, ez da classifier-a entrenatu behar, crossValidateModel metodoak entrenatzen baitu
        //nb.buildClassifier(newData);

        //EBALUATU
        Evaluation eval = new Evaluation(newData);
        eval.crossValidateModel(nb, newData, 10, new Random(1));

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

            //nahasmen matrizea
            bw.write("\nNahasmen Matrizea\n=================\n");
            double[][] confusionMatrix = eval.confusionMatrix();
            bw.write("TP  FN  TN  FP\n");
            for (int i = 0; i < confusionMatrix.length; i++) {
                for (int j = 0; j < confusionMatrix[i].length; j++) {
                    bw.write(confusionMatrix[i][j] + " ");
                }
            }
            bw.write("\n");

            //precision klase bakoitzerako eta weighted avg
            for (int i = 0; i < newData.numClasses(); i++) {
                bw.write("\nPrecision for class ");
                bw.write(newData.classAttribute().value(i));
                bw.write(" --> ");
                bw.write(String.valueOf(eval.precision(i)));
            }
            bw.write("\nPrecision weighted avg --> ");
            bw.write(String.valueOf(eval.weightedPrecision()));

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
