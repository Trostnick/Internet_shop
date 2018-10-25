package internet.shop.entity;

import javax.persistence.*;

@Entity
@Table(name="order_camp_id")
public class OrderCamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean removed;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "camp_id")
    private Camp camp;

    private int count;

    public OrderCamp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {

        return String.format(
                "OrderCamp[id=%d, order=%d, camp='%s', count=%d]\r\n",
                id, this.getOrder().getId(), this.getCamp().getName(), count) ;
    }
}
