package prak1;

import java.util.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import java.util.Scanner;

public class WekaInfoPrinter {

    private static WekaInfoPrinter nireWIP = null;

    private WekaInfoPrinter() {
        //eraikitzaile pribatua, singleton patroia
    }

    public static WekaInfoPrinter getWekaInfoPrinter(){
        if (nireWIP == null){
            nireWIP = new WekaInfoPrinter();
        }
        return nireWIP;
    }

    public static void main(String[] args) {
        
    }

    public void printInfo(String filePath) throws Exception {
        DataSource source = new DataSource(filePath);
        Instances data = source.getDataSet();
        if (data == null) {
            throw new Exception("No data could be loaded from: " + filePath);
        }
        if (data.classIndex() == -1) data.setClassIndex(data.numAttributes() - 1);
        //fitxategiaren path-a pantailaratu
        System.out.println("Fitxategiaren path-a: " + filePath);
        //instantzia kopurua pantailaratu
        System.out.println("Instantzia kopurua: " + data.numInstances());
        //atributu kopurua pantailaratu
        System.out.println("Atributu kopurua: " + data.numAttributes());


        // 	Atributuen analisi interaktiboa,
        interactiveAttributeAnalysis(data);
    }

    // Begizta interaktiboa: erabiltzaileari eskatu zein atributuri buruzko informazioa nahi duen
    public void interactiveAttributeAnalysis(Instances data) {
        Scanner scanner = new Scanner(System.in);
        int numAttr = data.numAttributes();
        System.out.println("\n--- Atributuen analisi interaktiboa ---");
        while (true) {
            System.out.print("Sartu aztertu nahi duzun atributuaren indizea (0.." + (numAttr - 1) + ") edo 'q' irteteko: ");
            String line;
            try {
                if (!scanner.hasNextLine()) break;
                line = scanner.nextLine().trim();
            } catch (Exception e) {
                break;
            }
            if (line.equalsIgnoreCase("q") || line.equalsIgnoreCase("quit") ) {
                System.out.println("Irten...");
                break;
            }
            int idx;
            try {
                idx = Integer.parseInt(line);
            } catch (NumberFormatException nfe) {
                System.out.println("Sartu zenbaki baliozkoa edo 'q'.");
                continue;
            }
            if (idx < 0 || idx >= numAttr) {
                System.out.println("Indizea kanpoan dago. Balio onargarriak: 0.." + (numAttr - 1));
                continue;
            }

            Attribute attr = data.attribute(idx);
            System.out.println("\nAtributua #" + idx + " - izena: " + attr.name() + " - mota: " + (attr.isNominal() ? "nominal" : "numeric"));

            Map<String, Integer> counts = new LinkedHashMap<>();
            int missing = 0;

            double sum = 0.0;
            double sumSq = 0.0;
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;
            int numericCount = 0;
            Set<String> distinct = new LinkedHashSet<>();

            for (int i = 0; i < data.numInstances(); i++) {
                Instance inst = data.instance(i);
                if (inst.isMissing(attr)) {
                    missing++;
                    continue;
                }
                if (attr.isNominal()) {
                    String key = inst.stringValue(attr);
                    counts.put(key, counts.getOrDefault(key, 0) + 1);
                    distinct.add(key);
                } else {
                    double v = inst.value(attr);
                    String key = Double.toString(v);
                    counts.put(key, counts.getOrDefault(key, 0) + 1);
                    distinct.add(key);
                    sum += v;
                    sumSq += v * v;
                    if (v < min) min = v;
                    if (v > max) max = v;
                    numericCount++;
                }
            }

            System.out.println("- Balio ezberdinak: " + distinct.size());
            System.out.println("- Missing kopurua: " + missing);

            if (counts.isEmpty()) {
                System.out.println("(Ez da baliorik aurkitu ezta denak missing)");
            } else {
                System.out.println("- Balioen banaketa (balioa: kopurua):");
                for (java.util.Map.Entry<String, Integer> e : counts.entrySet()) {
                    System.out.println("  - " + e.getKey() + ": " + e.getValue());
                }
            }

            if (!attr.isNominal()) {
                if (numericCount > 0) {
                    double mean = sum / numericCount;
                    double variance = 0.0;
                    if (numericCount > 1) {
                        variance = (sumSq - (sum * sum) / numericCount) / (numericCount - 1);
                    } else {
                        variance = 0.0;
                    }
                    double stddev = Math.sqrt(Math.max(0.0, variance));
                    System.out.println(String.format("- Min: %.6f", min));
                    System.out.println(String.format("- Max: %.6f", max));
                    System.out.println(String.format("- Mean: %.6f", mean));
                    System.out.println(String.format("- StdDev: %.6f", stddev));
                } else {
                    System.out.println("Ez da balio numerikorik aurkitu");
                }
            }

            System.out.println();
        }
    }


}