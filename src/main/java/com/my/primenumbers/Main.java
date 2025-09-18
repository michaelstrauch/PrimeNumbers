package com.my.primenumbers;

import com.my.primenumbers.repository.DataImport;
import com.my.primenumbers.service.PrimeNumbers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = "InputFiles/vzorek_dat - kopie.xlsx";

        PrimeNumbers numbers = new PrimeNumbers();
        DataImport data = new DataImport();
        Logger logger = LogManager.getLogger(Main.class);

        List<Long> extractedData = data.dataFromExcel(filePath);
        List<Long> primeNumbers = numbers.getPrimeNumbers(extractedData);

        for (Long primeNumber : primeNumbers) {
            logger.info("PrimeNumber: {} ",primeNumber);
        }

    }
}
