package internet.shop.constant;

public enum  USER_STATUS{
    REMOVED(1L),
    CLIENT(2L),
    MANAGER(3L),
    ADMIN(4L);

    private Long value;

    USER_STATUS(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}