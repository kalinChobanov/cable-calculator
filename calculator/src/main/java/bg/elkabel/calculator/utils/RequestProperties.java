
package bg.elkabel.calculator.utils;

import bg.elkabel.calculator.entity.Material;


public class RequestProperties {
	
	private long lenght;
	private long totalLenght;
	private int multiplier;
	private int totalDrums;
        private String conductorName;
        private String coreName;
        private Material material;

    /**
     *
     * @param lenght
     * @param totalLenght
     * @param multiplier
     * @param totalDrums
     * @param conductorName
     * @param coreName
     * @param material
     */
    public RequestProperties(long lenght, long totalLenght, int multiplier, int totalDrums, String conductorName, String coreName, Material material) {
        this.lenght = lenght;
        this.totalLenght = totalLenght;
        this.multiplier = multiplier;
        this.totalDrums = totalDrums;
        this.conductorName = conductorName;
        this.coreName = coreName;
        this.material = material;
    }

    public long getLenght() {
        return lenght;
    }

    public void setLenght(long lenght) {
        this.lenght = lenght;
    }

    public long getTotalLenght() {
        return totalLenght;
    }

    public void setTotalLenght(long totalLenght) {
        this.totalLenght = totalLenght;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getTotalDrums() {
        return totalDrums;
    }

    public void setTotalDrums(int totalDrums) {
        this.totalDrums = totalDrums;
    }

    public String getConductorName() {
        return conductorName;
    }

    public void setConductorName(String conductorName) {
        this.conductorName = conductorName;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }



}