import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {


    public boolean isPrimeNumber(long number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public List<Long> getPrimeNumbers(List<Long> uncheckedNumbers) {
        List<Long> primeNumbers = new ArrayList<>();

        for (Long number : uncheckedNumbers) {
            if(isPrimeNumber(number)) {
                primeNumbers.add(number);
            }
        }
        return primeNumbers;
    }


}
