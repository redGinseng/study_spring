package springbook.user.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filepath) throws IOException {
        //람다 한번 써보자
        LineCallback sumCallback =
            (String line, Integer value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallback multiplyCallback =
            new LineCallback() {
                public Integer doSomethingWithLine(String line, Integer value) {
                    return value * Integer.valueOf(line);
                }
            };
        return lineReadTemplate(filepath, multiplyCallback, 1);
    }


    public Integer lineReadTemplate(String filepath, LineCallback callback, Integer initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            Integer res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
