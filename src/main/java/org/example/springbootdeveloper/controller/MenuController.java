package org.example.springbootdeveloper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ApiMappingPattern;
import org.example.springbootdeveloper.dto.request.MenuRequestDto;
import org.example.springbootdeveloper.dto.response.MenuResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.MENU)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    //MenuController mapping pattern 설정
    private final String MENU_POST = "/";

    private final String MENU_GET_MENU_ID = "/{menuId}";
    private final String MENU_GET_LIST = "/list";
    //private final String MENU_GET_CATEGORY = "/{menuCategory}";

    private final String MENU_PUT = "/{menuId}";

    private final String MENU_DELETE = "/{menuId}";


    @PostMapping(MENU_POST)
    public ResponseEntity<ResponseDto<MenuResponseDto>> createMenu(@Valid @RequestBody MenuRequestDto dto) {
        // @Valid
        // : 메서드으 파라미터나 객체 필드의 유효성 검사를 위해 사용
        // : 객체 필드에 설정 된 Bean Validation 제약조건 (@NotNull, @Min, @Size 등)을 검사
        
        // - @Valid 는 MenuResponseDto 의 객체 필드에 설정된 유효성 제약 조건을 검사
        ResponseDto<MenuResponseDto> result = menuService.createMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(MENU_GET_LIST)
    public ResponseEntity<ResponseDto<List<MenuResponseDto>>> getAllMenus() {
        ResponseDto<List<MenuResponseDto>> result = menuService.getAllMenus();
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping(MENU_GET_MENU_ID)
    public ResponseEntity<ResponseDto<MenuResponseDto>> getMenuById (@PathVariable Long id){
        ResponseDto<MenuResponseDto> result = menuService.getMenuById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(MENU_PUT)
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu (@PathVariable Long id, @Valid @RequestBody MenuRequestDto dto){
        ResponseDto<MenuResponseDto> result = menuService.updateMenu(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(MENU_DELETE)
    public ResponseEntity<ResponseDto<Void>> deleteMenu(@PathVariable Long id){
        ResponseDto<Void> result = menuService.deleteMenu(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
