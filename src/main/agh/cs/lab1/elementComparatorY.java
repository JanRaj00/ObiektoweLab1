package agh.cs.lab1;

import java.util.Comparator;
public class elementComparatorY implements Comparator <IMapElement> {
    public int compare(IMapElement o1, IMapElement o2) {
        Vector2d v1=o1.getPosition();
        Vector2d v2=o2.getPosition();
        if(v1.equals(v2))
            return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
        if(v1.y==v2.y)
            return (v1.x-v2.x);
        return (v1.y-v2.y);
    }
}
