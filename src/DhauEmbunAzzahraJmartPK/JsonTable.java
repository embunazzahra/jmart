package DhauEmbunAzzahraJmartPK;

import com.google.gson.Gson;


import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class JsonTable<T> extends Vector<T> {
    public static final Gson gson = new Gson();
    public final String filepath;

    JsonTable(Class<T> clazz, String filepath) throws IOException{
        this.filepath = filepath;
        try {
            @SuppressWarnings("unchecked")
            Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz,0).getClass();
            T[] loaded = JsonTable.readJson(arrayType,filepath);
            Collections.addAll(this,loaded);
        } catch (FileNotFoundException e) {
            try {
                Class<T[]> arrayType = (Class<T[]>) Array.newInstance(clazz,0).getClass();
                T[] loaded = JsonTable.readJson(arrayType,filepath);
                JsonTable.writeJson(arrayType,filepath);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static <T> T readJson(Class<T> clazz, String filepath) throws FileNotFoundException{
        final BufferedReader br = new BufferedReader(new FileReader(filepath));
        return gson.fromJson(br, clazz);
    }

    public void writeJson() throws IOException{
        writeJson(this, this.filepath);
    }

    public static void writeJson(Object object, String filepath) throws IOException{
        final FileWriter writer = new FileWriter(filepath);
        writer.write(gson.toJson(object));
        writer.close();
    }
}