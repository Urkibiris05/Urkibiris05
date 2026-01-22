public class App {
    public static void main(String[] args) throws Exception {
        String filePath = "/home/urkibiris/weka-3-8-6/data/heart-c.arff"; // Hemen jarri zure fitxategiaren path-a
        WekaInfoPrinter printer = WekaInfoPrinter.getWekaInfoPrinter();
        printer.printInfo(filePath);
    }
}
