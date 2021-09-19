package homework;



public class AplicationRunner {
    IOService ioService;
    NumbersToTextConverter numbersToTextConverter;

    public AplicationRunner(IOService ioService, NumbersToTextConverter numbersToTextConverter) {

        this.ioService=ioService;
        this.numbersToTextConverter=numbersToTextConverter;
    }

    public void run(){
        ioService.outputStr("Введите число или команду exit");
        while (true)
        {
            String str=ioService.readString();
            if(str.equals("exit")){
                ioService.outputStr("Работа завершена");
                break;
            }
            try {
                ioService.outputStr(numbersToTextConverter.getTextNumberView(str));
            }catch (NumberFormatException e){
                ioService.outputStr(e.getMessage());
            }
//
        }

    }
}

