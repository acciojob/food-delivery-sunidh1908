package com.driver.service.impl;

import com.driver.Converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = FoodEntity.builder().id(food.getId()).foodName(food.getFoodName()).foodId(food.getFoodId()).
                foodCategory(food.getFoodCategory()).foodPrice(food.getFoodPrice()).build();
        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = FoodConverter.EntityToDto(foodEntity);
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = foodDetails;
        foodDto.setId(foodEntity.getId());
        foodRepository.updateFood(foodEntity.getId(), foodEntity.getFoodId(),foodEntity.getFoodName(),
                foodEntity.getFoodCategory(),foodEntity.getFoodPrice());

        return foodDto;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {

        List<FoodEntity> foodEntity = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> allFoods = new ArrayList<>();
        for(FoodEntity food : foodEntity){
            allFoods.add(FoodConverter.EntityToDto(food));
        }
        return allFoods;
    }
}