package internet.shop.constant;

public enum CAMP_TYPE {
    REMOVED(1L),
    STATIONARY(2L),
    TENT(3L),
    DAY_STAY(4L);

    private Long value;

    CAMP_TYPE(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
