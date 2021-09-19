package homework;




public class HomeWork {

    public static void main(String[] args) {
        IOService ioService = new ConsoleIOService()   ;
        NumberClassConverter numberClassConverter = new NumberClassConverterRus();
        NumbersToTextConverter numbersToTextConverter = new NumbersToTextConverter(numberClassConverter);
        AplicationRunner aplicationRunner = new AplicationRunner(ioService,numbersToTextConverter);
        aplicationRunner.run();

    }
}
