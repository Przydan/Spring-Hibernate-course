package pl.przydan.springDemo._2_BeanScope_XML.Coach;

import pl.przydan.springDemo._2_BeanScope_XML.fortune.FortuneService;

public class BaseballCoach implements Coach {

    // define a private field for the dependency
    private final FortuneService fortuneService;

    // define a constructor for DI
    public BaseballCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practise.";
    }

    @Override
    public String getDailyFortune() {
        // use my fortuneService to get a fortune
        return fortuneService.getFortune();
    }
}
