package internet.shop.model.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderPartCount {
    @Max(Integer.MAX_VALUE)
    @Min(1)
    private int newCount;

    public OrderPartCount() {
    }

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }
}
