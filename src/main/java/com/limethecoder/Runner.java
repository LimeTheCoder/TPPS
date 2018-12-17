package com.limethecoder;


import com.limethecoder.model.Country;
import com.limethecoder.model.Simulator;
import com.limethecoder.model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Runner {
    private static final String INPUT_FILE = "src/main/resources/in.txt";
    private static final String OUTPUT_FILE = "src/main/resources/out.txt";

    public static void main(String[] args) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File(INPUT_FILE));
            PrintWriter writer = new PrintWriter(new File(OUTPUT_FILE))) {
            int line = 1;
            while (scanner.hasNextLine()){
                Simulator simulator = scanNextTextSuit(scanner);
                process(simulator, writer, line);
                line++;
            }
        }
    }

    private static Simulator scanNextTextSuit(Scanner scanner){
        int countries = scanner.nextInt();
        Simulator simulator = new Simulator();
        for (int i = 0; i < countries; i++) {
            String name = scanner.next();
            Position lower = new Position(scanner.nextInt() - 1, scanner.nextInt() - 1);
            Position upper = new Position(scanner.nextInt() - 1, scanner.nextInt() - 1);

            Country country = new Country(name, lower, upper);
            simulator.addCountry(country);
        }
        return simulator;
    }

    private static void process(Simulator simulator, PrintWriter writer, int line) {
        if(!simulator.isCoordinatesValid()) {
            writer.println("Test case # " + line);
            writer.println("Wrong input!");
        }

        writer.println("Test case # " + line);
        simulator.simulate();
        simulator.showResults(writer);
    }
}
