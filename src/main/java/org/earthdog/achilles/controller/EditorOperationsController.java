package org.earthdog.achilles.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.earthdog.achilles.entity.dto.ApiSaveDTO;
import org.earthdog.achilles.entity.vo.BaseVO;
import org.earthdog.achilles.exception.ClassCreateException;
import org.earthdog.achilles.service.EditOperationsService;
import org.earthdog.achilles.tools.loader.ApiMappingInfo;
import org.earthdog.achilles.tools.loader.SourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Date 2024/7/9 13:30
 * @Author DZN
 * @Desc BaseOperationsController
 */
@RestController
@RequestMapping("/editor")
public class EditorOperationsController {

    public static final String RunMethod = "execute";
    private final SourceLoader sourceLoader;
    private final RequestMappingHandlerMapping requestMapping;
    private final BaseOperationController baseOperationController;
    private final EditOperationsService editOperationsService;

    public EditorOperationsController(SourceLoader sourceLoader, RequestMappingHandlerMapping requestMapping, BaseOperationController baseOperationController, EditOperationsService editOperationsService) {
        this.sourceLoader = sourceLoader;
        this.requestMapping = requestMapping;
        this.baseOperationController = baseOperationController;
        this.editOperationsService = editOperationsService;
    }

    @PostMapping("/run")
    public BaseVO<?> run(@RequestBody ApiSaveDTO apiSaveDTO)
            throws ClassCreateException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        save(apiSaveDTO);

        String code = apiSaveDTO.getCode();

        String path = apiSaveDTO.getPath();
        String qualifiedName = apiSaveDTO.getQualifiedName();
        String method = apiSaveDTO.getMethod();

        ApiMappingInfo apiMappingInfo = ApiMappingInfo.builder().path(path).method(method).qualifiedName(qualifiedName).code(code).build();

        sourceLoader.load(apiMappingInfo);

        RequestMappingInfo mappingInfo = RequestMappingInfo
                .paths(path)
                .methods(RequestMethod.valueOf(apiSaveDTO.getMethod().toUpperCase()))
                .build();

        requestMapping.unregisterMapping(mappingInfo);

        requestMapping.registerMapping(mappingInfo, baseOperationController, baseOperationController.getClass().getDeclaredMethod(RunMethod, HttpServletRequest.class));

        return BaseVO.OK();
    }

    @PostMapping
    public BaseVO<?> save(@RequestBody ApiSaveDTO apiSaveDTO) {
        return editOperationsService.save(apiSaveDTO);
    }

}
