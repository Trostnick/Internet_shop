package internet.shop.constant;

public enum  ORDER_STATUS{
    REMOVED(1L),
    IN_BASKET(2L),
    BOOKED(3L),
    PAYED(4L),
    DELIEVERED(5L),
    FINISHED(6L);

    private Long value;

    ORDER_STATUS(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}

