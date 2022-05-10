package lambdaTutorial;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda04 {
    /*
    TASK :
    fields --> Universite (String)
               bolum (String)
               ogrcSayisi (int)
               notOrt (int)
               olan POJO clas craete edip main method içinde 5 farklı obj'den List create ediniz.
     */

    public static void main(String[] args) {
        University u01 = new University("bogazici", "matematik", 571, 93);
        University u02 = new University("istanbul teknik", "matematik", 622, 81);
        University u03 = new University("istanbul", "hukuk", 1453, 71);
        University u04 = new University("marmara", "bilgisayar muh", 1071, 77);
        University u05 = new University("yıldız teknik", "gemi", 333, 74);
        List<University> unv = new ArrayList<>(Arrays.asList(u01, u02, u03, u04, u05));

        System.out.println( notort74BykUnv (unv));
        System.out.println("\n *** *** *** ");
        System.out.println(ogrcSayisi110AzMi(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(matBolumVarmi(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(ogrSayiBkSirala( unv));
        System.out.println("\n *** *** *** ");
        ogrSayiBkSiralaVoid( unv);
        System.out.println("\n *** *** *** ");
        System.out.println(notOrtBkSialiIlkUc(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(enAzOgrcSaysi2Unv(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(notOrt63BykUnvOgrcSayisiTopla( unv));
        System.out.println("\n *** *** *** ");
        System.out.println(notOrt63BykUnvOgrcSayisiToplaToInt(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(ogrcSayisi333BykNotOrtOrtlamaAl(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(mathBolmSayısı(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(ogrcSayisi571BykMaxNotort(unv));
        System.out.println("\n *** *** *** ");
        System.out.println(ogrcSayisi1071AzMinnotOrt(unv));
        System.out.println("\n *** *** *** ");

    }

    //task 01--> notOrt'larinin 74' den buyuk oldg kontrol eden pr create ediniz.
    public static Boolean  notort74BykUnv (List<University> unv){

         return   unv.
                 stream().
                 allMatch(t->t.
                         getNotOrt()>74);

    }

    //task 02-->ogrc sayilarinin   110 den az olmadigini  kontrol eden pr create ediniz.
    public static boolean ogrcSayisi110AzMi(List<University> unv){
        return unv.
                stream().
                allMatch(t ->t.getNotOrt()>110);

    }

    //task 03-->universite'lerde herhangi birinde "matematik" olup olmadigini  kontrol eden pr create ediniz.
    public static boolean matBolumVarmi(List<University> unv){
        return unv.
                stream().
                anyMatch(t->t.getBolum().
                        toLowerCase().
                        contains("mat"));

    }

    //task 04-->universite'leri ogr sayilarina gore b->k siralayiniz.
    public static List<University> ogrSayiBkSirala(List<University> unv){
        return unv.
                stream().
                sorted(Comparator.comparing(University::getOgrSayisi).reversed()).
                collect(Collectors.toList());

    }

    public static void ogrSayiBkSiralaVoid(List<University> unv) {
        System.out.println(unv.
                stream().
                sorted(Comparator.comparing(University::getOgrSayisi).reversed()).
                collect(Collectors.toList()));//collect()->akısdaki elamanları istenen sarta gore toplar
        //Collectors.toList()->collect'e toplanan elemanlari list'e cevirir
    }

    //task 05-->universite'leri notOrt gore  b->k siralayip ilk 3 'unu print ediniz.
    public static List<University> notOrtBkSialiIlkUc(List<University> unv) {
       return unv.
               stream().//akısa alındı
               sorted(Comparator.comparing(University::getNotOrt).reversed()).//notOrt a göre b->k sıralandı
               limit(3).//akısın ilk 3 elemanı alındı
               collect(Collectors.toList());//akısın ilk 3 elemanı list'e assign edildi
        //toList());//akısın ilk 3 elemanı list'e assign edildi


    }

    //task 06--> ogrc sayisi en az olan 2. universite'yi  print ediniz.
    public static List<University> enAzOgrcSaysi2Unv(List<University> unv){
        return unv.
                stream().
                sorted(Comparator.comparing(University::getNotOrt)).
                limit(2).
                skip(1).
                collect(Collectors.toList());
    }

    //task 07--> notOrt 63 'den buyuk olan universite'lerin ogrc sayilarini toplamini print ediniz.
   public static int notOrt63BykUnvOgrcSayisiTopla(List<University> unv){
       return unv.
               stream().
               filter(t-> t.getNotOrt()>63).
               map(t->t.getOgrSayisi()).
               // reduce(Integer::sum);
               // reduce(Math::addExact);
               reduce(0,(t,u)->t+u);
   }
    public static int notOrt63BykUnvOgrcSayisiToplaToInt(List<University> unv) {
        return unv.
                stream().
                filter(t -> t.getNotOrt() > 63).
                mapToInt(t -> t.getOgrSayisi()).
                sum();
        // mapToInt() --> bu method akısdaki elemanların data type'nı
        // parameterisindeki() degere göre Int data type update eder

    }

    //task 08--> Ogrenci sayisi 333'dan buyuk olan universite'lerin notOrt'larinin ortalamasini bulunuz.
    public static OptionalDouble ogrcSayisi333BykNotOrtOrtlamaAl(List<University> unv){
        return unv.
                stream().
                filter(t-> t.getOgrSayisi()>333).
                mapToDouble(t->t.getNotOrt()).
                average();//akısdakş elamnalrın ortlaması alındı
        // mapToDouble() --> bu method akısdaki elemanların data type'nı
        // parameterisindeki degere göre dooble data type update eder


    }

    //task 09-->"matematik" bolumlerinin sayisini  print ediniz.
    public static int mathBolmSayısı(List<University> unv){
        return (int) unv.
                stream().
                filter(t->t.getBolum().contains("mat")).
                count();//akısdaki elelman sayısını return eder
        //count();-->akısdaki elelman sayısını return eder


    }

    //task 10-->Ogrenci sayilari 571'dan fazla olan universite'lerin en buyuk notOrt'unu bulunuz.
    public static OptionalInt ogrcSayisi571BykMaxNotort(List<University> unv){
        return unv.
                stream().//akıs
                filter(t->t.getOgrSayisi()>571).//unv obj akısı filtrelendi
                mapToInt(t->t.getNotOrt()).//akısdaki unv obj notOrt akısı olarak update edildi
                max();//akısın en byk degerini return eder

    }

    //task 11-->Ogrenci sayilari 1071'dan az olan universite'lerin en kucuk notOrt'unu bulunuz.
    public static OptionalInt ogrcSayisi1071AzMinnotOrt(List<University> unv){
        return unv.
                stream().
                filter(t->t.getOgrSayisi()<1071).
                mapToInt(t->t.getNotOrt()).
                min();

    }






}
