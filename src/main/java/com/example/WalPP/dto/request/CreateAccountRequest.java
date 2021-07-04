package com.example.WalPP.dto.request;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountRequest {
    @NotEmpty
    private Integer userId;
    private Float intialBalance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getIntialBalance() {
        return intialBalance;
    }

    public void setIntialBalance(Float intialBalance) {
        this.intialBalance = intialBalance;
    }
}
