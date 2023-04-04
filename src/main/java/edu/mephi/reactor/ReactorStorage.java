package edu.mephi.reactor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.mephi.exceptions.WrongFileFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ReactorStorage {
  private Map<String, Reactor> storage;
  private String type;

  public Map<String, Reactor> getStorage() { return storage; }
  public void setStorage(Map<String, Reactor> storage) {
    this.storage = storage;
  }
  public String getType() { return type; }
  public void setType(String type) { this.type = type; }

  public void readFile(String filename)
      throws WrongFileFormatException, IOException {
    if (filename.endsWith(".json")) {
      this.type = "json";
      readJSON(filename);
    } else if (filename.endsWith(".xml")) {
      this.type = "xml";
      readXML(filename);
    } else if (filename.endsWith(".yaml")) {
      this.type = "yaml";
      readYAML(filename);
    } else {
      throw new WrongFileFormatException("Wrong file format");
    }
  }

  private void readJSON(String filename) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    storage = objectMapper.readValue(
        new File(filename), new TypeReference<Map<String, Reactor>>() {});
  }

  private void readXML(String filename) throws IOException {
    XmlMapper mapper = new XmlMapper();

    storage = mapper.readValue(new File(filename),
                               new TypeReference<Map<String, Reactor>>() {});
  }

  private void readYAML(String filename) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    storage = objectMapper.readValue(
        new File(filename), new TypeReference<Map<String, Reactor>>() {});
  }
}
