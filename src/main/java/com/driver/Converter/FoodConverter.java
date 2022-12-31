package com.driver.Converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {

    public static FoodDto EntityToDto(FoodEntity foodEntity){
        return FoodDto.builder().id(foodEntity.getId()).foodId(foodEntity.getFoodId()).
                foodName(foodEntity.getFoodName()).foodPrice(foodEntity.getFoodPrice()).
                foodCategory(foodEntity.getFoodCategory()).build();
    }

    public static FoodDetailsResponse DtoToResponse(FoodDto foodDto){
        return FoodDetailsResponse.builder().foodId(foodDto.getFoodId()).foodName(foodDto.getFoodName()).
                foodPrice(foodDto.getFoodPrice()).foodCategory(foodDto.getFoodCategory()).build();
    }

//    public static FoodDetailsRequestModel DtoToRequest(FoodDto foodDto){
//        return FoodDetailsRequestModel.builder().foodName(foodDto.getFoodName()).
//                foodPrice(foodDto.getFoodPrice()).foodCategory(foodDto.getFoodCategory()).build();
//    }
//
//    public static FoodDetailsResponse RequestToResponse(FoodDetailsRequestModel foodDetailsRequestModel){
//        return FoodDetailsResponse.builder().foodName(foodDetailsRequestModel.getFoodName()).
//                foodPrice(foodDetailsRequestModel.getFoodPrice()).foodCategory(foodDetailsRequestModel.getFoodCategory()).build();
//    }
}
