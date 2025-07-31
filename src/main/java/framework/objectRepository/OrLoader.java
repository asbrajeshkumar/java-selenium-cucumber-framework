package framework.objectRepository;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class OrLoader {
	private final Map<String, orStruct> map = new HashMap<>();

	public OrLoader(String filePath) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(new FileReader(filePath));

			for (Object keyObj : root.keySet()) {
				String key = (String) keyObj;
				JSONObject loc = (JSONObject) root.get(key);

				String type = (String) loc.get("type");
				String value = (String) loc.get("value");

				Object waitObj = loc.get("wait");
				int wait;
				if (waitObj instanceof Number) {
					wait = ((Number) waitObj).intValue();
				} else {
					wait = 5;
				}

				map.put(key, new orStruct(key, type, value, wait));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public orStruct get(String name) {
		return map.get(name);
	}
}
