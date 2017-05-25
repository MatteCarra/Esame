package problema1;

import java.util.List;

/**
 * Created by matteo on 25/05/17.
 */
public class Sequenza {
    private String nome;
    private List<String> stringhe;

    public Sequenza(String nome, List<String> stringhe) {
        this.nome = nome;
        this.stringhe = stringhe;
    }

    public void aggiungiStringa(String stringa) {
        stringhe.add(stringa);
    }

    public void aggiungiStringhe(List<String> stringhe) {
        this.stringhe.addAll(stringhe);
    }

    public int numeroStringhe() {
        return stringhe.size();
    }

    public List<String> getStringhe() {
        return stringhe;
    }

    public String getNome() {
        return nome;
    }

    public int match(Sequenza sequenza2) {
        int i = 0;
        for(String s : sequenza2.getStringhe()) {
            if(stringhe.contains(s)) {
                i++;
            }
        }
        return i;
    }
}
