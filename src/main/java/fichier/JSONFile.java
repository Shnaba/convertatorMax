package fichier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONFile implements Fichier{

    private String file;

    public JSONFile(String file) {
        this.file = file;
    }

    @Override
    public void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String ligne = null;
        String[] data = new String[3];
        while ((ligne = br.readLine()) != null) {
            System.out.println(ligne);
        }
    }

    @Override
    public void writeFile() throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // creation du fichier json

            ObjectNode user = mapper.createObjectNode();
            user.put("id", 1);
            user.put("nom", "Brigitte Macron");
            user.put("email", "b.macron@free.fr");
            user.put("salaire", 1800.50);
            user.put("fonction", "on sait pas");
            // creation child json object
            ObjectNode address = mapper.createObjectNode();
            address.put("adresse", "3 rue Victor Hugo");
            address.put("CodePostal", 56000);
            address.put("ville", "Vannes");
            user.set("address", address);
            //conversion ObjuectNode en JSON
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            FileWriter fichier = new FileWriter(file);
            // ecriture du json dans le fichier
            fichier.write(json);
            fichier.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
