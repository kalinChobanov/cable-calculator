package bg.elkabel.calculator.models.view;

import bg.elkabel.calculator.entity.Material;

public class ChooseCoreView {

    private Long id;
    
    private String name;

    private double coreSize;

    private Material material;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(double coreSize) {
        this.coreSize = coreSize;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
