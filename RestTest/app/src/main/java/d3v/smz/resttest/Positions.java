package d3v.smz.resttest;

/**
 * Created by Bruno on 2/17/2018.
 */

public class Positions {

    private String posID;
    private long x;
    private long y;
    private long z;

    public Positions(String posID, long x, long y, long z) {
        this.posID = posID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getPosID() {
        return posID;
    }

    public void setPosID(String posID) {
        this.posID = posID;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }
}
