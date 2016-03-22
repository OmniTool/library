package library.hibernate.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBase {

    protected int id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment_id")
    @SequenceGenerator(name = "increment_id", sequenceName = "increment_id", allocationSize = 1)
    @Column(name = "id", insertable = false)
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
