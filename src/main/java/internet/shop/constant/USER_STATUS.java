package internet.shop.constant;

public enum  USER_STATUS{
    CLIENT(1L),
    MANAGER(2L),
    ADMIN(3L);

    private Long value;

    USER_STATUS(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}