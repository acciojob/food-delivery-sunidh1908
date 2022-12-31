package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.Converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")

public class FoodController {

	@Autowired
	FoodServiceImpl foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse foodDetailsResponse = FoodConverter.DtoToResponse(foodDto);
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDto foodDto=FoodDto.builder().foodName(foodDetails.getFoodName()).foodCategory(foodDetails.getFoodCategory())
				.foodPrice(foodDetails.getFoodPrice()).build();

		FoodDto foodDto1 = foodService.createFood(foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodConverter.DtoToResponse(foodDto1);
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto=FoodDto.builder().foodName(foodDetails.getFoodName()).foodCategory(foodDetails.getFoodCategory())
				.foodPrice(foodDetails.getFoodPrice()).build();

		FoodDto foodDto1 = foodService.updateFoodDetails(id,foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodConverter.DtoToResponse(foodDto1);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		OperationStatusModel statusModel = OperationStatusModel.builder().operationName(String.valueOf(RequestOperationName.DELETE)).build();
		foodService.deleteFoodItem(id);
		return statusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDto> foods = foodService.getFoods();
		List<FoodDetailsResponse> allFoods = new ArrayList<>();

		for(FoodDto foodDto : foods){
			allFoods.add(FoodConverter.DtoToResponse(foodDto));
		}
		return allFoods;
	}
}
