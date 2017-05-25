package problema1;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by matteo on 25/05/17.
 */
public class Esercizio1 {
    private static HashMap<String, Sequenza> sequenzeHashMap = new HashMap<>();
    private static ArrayList<Sequenza> sequenzeList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        File input = new File("input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        while ((line = reader.readLine()) != null) {
            String name = line.substring(0, line.indexOf(":"));

            String[] stringhe = line.substring(line.indexOf(":") + 1).split(",");
            //Devo convertire String[] a ArrayList per poter aggiungere elementi dinamicamente nel caso in cui esista già una sequenza con questo nome
            //Per gio: Ci sarebbe un metodo dedicato ma il loro professore non credo l'abbia fatto
            List<String> stringArrayList = new ArrayList<>();
            for(String s : stringhe) {
                stringArrayList.add(s);
            }

            if(sequenzeHashMap.containsKey(name)) {
                System.out.println(name);
                sequenzeHashMap.get(name).aggiungiStringhe(stringArrayList);
            } else {
                sequenzeHashMap.put(name, new Sequenza(name, stringArrayList));
            }
        }
        reader.close();

        //La HashMap serve solo a popolare le sequenze, a dir la verità. La lascio cmq nel caso serva nel problema 2.
        sequenzeList = new ArrayList<>(sequenzeHashMap.values());

        punto1();
        punto2();
        punto3();
        punto4();
        punto5();
    }

    private static void punto1() throws Exception {
        File file = new File("1.txt");
        PrintWriter writer = new PrintWriter(file);
        for(Sequenza sequenza : sequenzeList) {
            if(sequenza.numeroStringhe() % 2 != 0) {
                writer.println(sequenza.getNome());
            }
        }
        writer.close();
    }

    private static void punto2() throws Exception {
        int maxNum = -1;
        String maxName = null;
        for(Sequenza sequenza : sequenzeList) {
            if(sequenza.numeroStringhe() > maxNum) {
                maxName = sequenza.getNome();
                maxNum = sequenza.numeroStringhe();
            }
        }
        File file = new File("2.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println(maxName);
        writer.close();
    }

    private static void punto3() throws Exception {
        HashMap<String, Integer> paroleHashMap = new HashMap<>();
        for(Sequenza sequenza : sequenzeList) {
            for(String parola : sequenza.getStringhe()) {
                paroleHashMap.put(parola, paroleHashMap.getOrDefault(parola, 0) + 1);
            }
        }

        int maxNum = -1;
        String maxParola = null;
        for (Map.Entry<String, Integer> entry : paroleHashMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value > maxNum) {
                maxNum = value;
                maxParola = key;
            }
        }

        File file = new File("3.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println(maxParola);
        writer.close();
    }

    private static void punto4() throws FileNotFoundException {
        File file = new File("4.txt");
        PrintWriter writer = new PrintWriter(file);

        for(Sequenza sequenza1 : sequenzeList) {
            for(Sequenza sequenza2 : sequenzeList) {
                writer.println(sequenza1.getNome() + " - " + sequenza2.getNome() + " = "  + sequenza1.match(sequenza2));
            }
            writer.println();
        }

        writer.close();
    }

    private static void punto5() throws FileNotFoundException {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        for(int i = 0; i < sequenzeList.size(); i++) {
            Sequenza sequenza1 = sequenzeList.get(i);
            for(int j = i + 1; j < sequenzeList.size(); j++) {
                Sequenza sequenza2 = sequenzeList.get(j);
                hashMap.put(sequenza1.getNome() + " " + sequenza2.getNome(), sequenza1.match(sequenza2));
            }
        }

        List<String> orderedList = new ArrayList<>();

        while (true) {
            int max = -1;
            String name = null;

            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                String text = key + " " + value;
                if(value > max && !orderedList.contains(text)) {
                    max = value;
                    name = text;
                }
            }

            if(name == null) {
                break;
            }

            orderedList.add(name);

        }

        File file = new File("5.txt");
        PrintWriter writer = new PrintWriter(file);

        for(String text : orderedList) {
            writer.println(text);
        }

        writer.close();
    }

}
