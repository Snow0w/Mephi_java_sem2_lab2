package edu.mephi.reactor;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Reactor {
  @JsonProperty("class") private String classReactor;
  private double burnup;
  private double kpd;
  private double enrichment;
  @JsonProperty("termal_capacity") private double termalCapacity;
  @JsonProperty("electrical_capacity") private double electricalCapacity;
  @JsonProperty("life_time") private double lifeTime;
  @JsonProperty("first_load") private double firstLoad;

  public String getClassReactor() { return classReactor; }
  public void setClassReactor(String classReactor) {
    this.classReactor = classReactor;
  }
  public double getBurnup() { return burnup; }
  public void setBurnup(double burnup) { this.burnup = burnup; }
  public double getKpd() { return kpd; }
  public void setKpd(double kpd) { this.kpd = kpd; }
  public double getEnrichment() { return enrichment; }
  public void setEnrichment(double enrichment) { this.enrichment = enrichment; }
  public double getTermalCapacity() { return termalCapacity; }
  public void setTermalCapacity(double termalCapacity) {
    this.termalCapacity = termalCapacity;
  }
  public double getElectricalCapacity() { return electricalCapacity; }
  public void setElectricalCapacity(double electricalCapacity) {
    this.electricalCapacity = electricalCapacity;
  }
  public double getLifeTime() { return lifeTime; }
  public void setLifeTime(double lifeTime) { this.lifeTime = lifeTime; }
  public double getFirstLoad() { return firstLoad; }
  public void setFirstLoad(double firstLoad) { this.firstLoad = firstLoad; }
  @Override
  public String toString() {
    return "class: " + classReactor + "\n"
        + "burnup: " + Double.toString(burnup) + "\n"
        + "kpd: " + Double.toString(kpd) + "\n"
        + "enrichment: " + Double.toString(enrichment) + "\n"
        + "termal_capacity: " + Double.toString(termalCapacity) + "\n"
        + "electrical_capacity: " + Double.toString(electricalCapacity) + "\n"
        + "life_time: " + Double.toString(lifeTime) + "\n"
        + "first_load: " + Double.toString(firstLoad) + "\n";
  }
}
