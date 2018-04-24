package principal;

public class Destino {
	private float x;
    private float y;

    public Destino(Destino d) {
    	this.x = d.getX();
    	this.y = d.getY();
    }
    
    public Destino(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
