
import java.util.ArrayList;
import java.util.List;

public class RestoranYonetimi {
    private List<Masa> masalar;
    private double ciro;

    public RestoranYonetimi() {
        masalar = new ArrayList<>();
        ciro = 0f;
        for (int i = 1; i <= 20; i++) {
            masalar.add(new Masa(i));
        }
    }

    public void ciroyaEkle(double para){
        ciro += para;
    }

    public double getCiro(){
        return ciro;
    }

    // Masaların listesini döndürür
    public List<Masa> getMasalar() {
        return masalar;
    }
}
