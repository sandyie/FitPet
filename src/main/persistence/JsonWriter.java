package persistence;

import model.Pet;
import org.json.JSONObject;
import ui.PetGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// The JsonWriter class includes method that help save the PetGame by writing a JSONObject

public class JsonWriter {

    private static final int TAB = 2;
    private PrintWriter writer;
    private String dataSite;

    public JsonWriter(String dataSite) {
        this.dataSite = dataSite;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(dataSite));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of game status
    public void write(PetGame pg) {
        JSONObject json = pg.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
