package club.zby.elasticsearch.Controller;

import club.zby.commen.Config.PageResult;
import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import club.zby.elasticsearch.Entity.EsFile;
import club.zby.elasticsearch.Service.EsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/24 9:36
 */

@Api(value = "ES")
@RestController
public class EsController {

    @Autowired
    private EsService esService;

    //搜索引擎
    @ResponseBody
    @ApiOperation(value="es搜索引擎", notes="按文件名搜索")
    @GetMapping("{filename}/{start}/{pageSize}")
    public Result findAllByFilename(@PathVariable("filename") String filename, @PathVariable int start, @PathVariable("pageSize") int ps){
        Page<EsFile> articlePage = esService.findAllByFilename(filename, start-1, ps);
        return new Result(true, StatusCode.OK,"搜索成功" ,
                new PageResult<EsFile>(articlePage.getTotalElements(),articlePage.getContent() )) ;
    }


}
