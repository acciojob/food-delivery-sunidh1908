package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.shared.dto.FoodDto;

public class FoodConverter {

    public static FoodDto EntityToDto(FoodEntity foodEntity){
        return FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).
                foodName(foodEntity.getFoodName()).foodPrice(foodEntity.getFoodPrice()).
                foodCategory(foodEntity.getFoodCategory()).build();
    }
}
