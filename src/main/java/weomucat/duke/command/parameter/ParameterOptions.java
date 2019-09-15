package weomucat.duke.command.parameter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ParameterOptions {
  private Parameter defaultParameter;
  private LinkedHashMap<String, Parameter> parameterHashMap;

  public ParameterOptions(Parameter defaultParameter) {
    this.defaultParameter = defaultParameter;
    this.parameterHashMap = new LinkedHashMap<>();
  }

  public Parameter getDefaultParameter() {
    return defaultParameter;
  }

  public ParameterOptions put(String key, Parameter value) {
    this.parameterHashMap.put(key, value);
    return this;
  }

  public Parameter get(String key) {
    return this.parameterHashMap.get(key);
  }

  public Set<Map.Entry<String, Parameter>> entrySet() {
    return this.parameterHashMap.entrySet();
  }

  public Set<String> keySet() {
    return this.parameterHashMap.keySet();
  }

  public Collection<Parameter> values() {
    return this.parameterHashMap.values();
  }
}
