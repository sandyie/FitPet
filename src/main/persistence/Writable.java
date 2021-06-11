package persistence;

import org.json.JSONObject;

// Method for writing JSONObject

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
