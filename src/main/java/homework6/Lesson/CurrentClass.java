package homework6.Lesson;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrentClass {
    private final static  Logger logger =  LogManager.getLogger(CurrentClass.class);

    //logLevels
    //Fatal >> Error >> Warn >> Info >> Debug >> Trace
    //6 > 5 > 4 >                                ?>1

    public static void main(String[] args) {

        int logLevel = 3; //Info

        int a = 50;
        if (logLevel <=2){
            System.out.println();
        }
        logger.debug("a = {}", a);

        int b = 10;
        if (logLevel<=2){
            System.out.println();
        }
        logger.debug("b = {}", b);
        int c = a + b;
        if (logLevel<=3){
            System.out.println(
            );
        }
        logger.info("c = {} + {} = {}",a ,b ,c);

//        Logger, Appender, Layont



        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

    }
}
