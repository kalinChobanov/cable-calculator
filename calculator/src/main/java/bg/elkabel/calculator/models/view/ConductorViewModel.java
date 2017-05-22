package bg.elkabel.calculator.models.view;

/**
 *
 * @author kalin
 */
public class ConductorViewModel {

    private long id;
    private String name;
    private String material;
    private int coreNumbers;
    private String core;
    private int degree;
    private int cut;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCoreNumbers() {
        return coreNumbers;
    }

    public void setCoreNumbers(int coreNumbers) {
        this.coreNumbers = coreNumbers;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getCut() {
        return cut;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }
    
    
}
