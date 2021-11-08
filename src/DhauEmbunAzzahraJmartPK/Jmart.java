package DhauEmbunAzzahraJmartPK;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.*;


public class Jmart
{
    /*class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }*/

    private static List<Product> paginate(List<Product> list, int page, int pageSize, Predicate<Product> pred){
        //final Iterator<Product> it = list.iterator();
        return list.stream()
                //.filter(product -> pred.predicate(product))
                .distinct()
                .skip(page*pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public static List<Product> filterByAccountId(List<Product> list, int accountId, int page, int pageSize){
        List<Product> filtered = list.stream()
                .filter(product -> product.accountId == accountId)
                .collect(Collectors.toList());
        final Predicate<Product> pred = list::equals;
        return paginate(filtered, page, pageSize, pred);
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize){
        List<Product> filtered = list.stream()
                .filter(product -> product.name.toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
        final Predicate<Product> pred = list::equals;
        return paginate(filtered, page, pageSize, pred);
    }

    public static List<Product> read(String filepath){
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Product[] input = gson.fromJson(br, Product[].class);
            List<Product> list = Arrays.asList(input);
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category){
        List<Product> filtered = list.stream()
                .filter(product -> product.category.equals(category)).collect(Collectors.toList());
        return filtered;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice){
        if(minPrice==0.0){
            List<Product> filtered = list.stream()
                    .filter(product -> product.price<=maxPrice).collect(Collectors.toList());
            return filtered;
        }
        else if(maxPrice==0.0){
            List<Product> filtered = list.stream()
                    .filter(product -> product.price>=minPrice).collect(Collectors.toList());
            return filtered;

        }
        else {
            List<Product> filtered = list.stream()
                    .filter(product -> (product.price<=maxPrice && product.price>=minPrice)).collect(Collectors.toList());
            return filtered;
        }
    }

    
    public static void main(String[] args) 
    {
       /* try {
            List<Product> list = read("C://Proyek Jmart/Jmart/src/lib/randomProductList.json");
            List<Product> filtered = filterByName(list,"ha", 0,2);
            filtered.forEach(product -> System.out.println(product.name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Product> list = read("C://Proyek Jmart/Jmart/src/lib/randomProductList.json");
            List<Product> filtered = filterByAccountId(list,1, 0,5);
            filtered.forEach(product -> System.out.println(product.name));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*try {
            List<Product> list = read("C://Proyek Jmart/Jmart/src/lib/randomProductList.json");
            List<Product> filtered = filterByCategory(list,ProductCategory.COSMETICS);
            filtered.forEach(product -> System.out.println(product.name));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*String filepath = "C://Proyek Jmart/Jmart/src/lib/city.json";
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Country input = gson.fromJson(br, Country.class);
            System.out.println("name: "+input.name);
            System.out.println("population: "+input.population);
            System.out.println("states:");
            input.listOfStates.forEach(state-> System.out.println(state));
        }
        catch (IOException e){
            e.printStackTrace();
        }*/
        /*System.out.println("acc id: " + new Account(null,null,null,-1).id);

        System.out.println("acc id: " + new Account(null,null,null,-1).id);

        System.out.println("acc id: " + new Payment(-1,-1,-1,null).id);
        System.out.println("acc id: " + new Payment(-1,-1,-1,null).id);
        System.out.println("acc id: " + new Account(null,null,null,-1).id);
        System.out.println("acc id: " + new Payment(-1,-1,-1,null).id);
        Payment payment = new Payment(-1,-1,-1,null);
        */


                
    }
}
