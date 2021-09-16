package com.rubicon.water.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonSerialize
@JsonDeserialize(as = ImmutableOrderDto.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Immutable
public interface OrderDto {

    @Nullable
    UUID getId();

    @NotNull
    String getStartTime();

    @NotNull
    String getDuration();

    @Nullable
    String getStatus();

    @NotNull
    UUID getWaterOrder();

}
