package com.yyh.controller;

import com.yyh.dto.AnimalError;
import com.yyh.dto.Dog;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

@RestController
@Api(value="用户接口",tags={"dogAPi"})
@RequestMapping(value = "/swagger")
public class AnimalController {

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Token", value = "token", dataType = "String", required = true,defaultValue = "123")})
    @ApiOperation(value = "获取一只狗")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "请求未通过认证.", response = AnimalError.class) })
    @RequestMapping(value="/onedog", method = RequestMethod.GET)
    public Dog oneDog(){
        return new Dog();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Token", value = "token", dataType = "String", required = true,defaultValue = "123")})
    @ApiOperation(value = "创建一只狗")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "请求未通过认证.", response = AnimalError.class) })
    @RequestMapping(value="/dog/{name}", method = RequestMethod.GET)
    public Dog createDog(
            @ApiParam(defaultValue = "二哈")
            @PathVariable("name") String name){
        Dog dog = new Dog();
        dog.setName(name);
        return dog;
    }
}
