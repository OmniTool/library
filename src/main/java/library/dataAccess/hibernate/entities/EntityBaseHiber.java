package library.dataAccess.hibernate.entities;

import library.dataAccess.accessPoint.active.hibernate.entities.EntityBase;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBaseHiber implements EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment_id")
    @SequenceGenerator(name = "increment_id", sequenceName = "increment_id", allocationSize = 1)
    @Column(name = "id", insertable = false)
    protected int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
