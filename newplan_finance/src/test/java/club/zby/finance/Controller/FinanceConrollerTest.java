package club.zby.finance.Controller;

import club.zby.commen.Config.IdWorker;
import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import club.zby.finance.Untlis.ToToken;
import feign.template.HeaderTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/4 17:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FinanceConrollerTest extends FinanceConroller {


    private MockMvc mvc;

    private HttpServletRequest request;

    private HttpHeaders httpHeaders;

    private MockHttpSession session;

    @Test
    public void findAllByPage() throws Exception {


        String json="66341505371082752";
        httpHeaders.add("Authrorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjM0MTUwNTM3MTA4Mjc1MiIsInN1YiI6IjE4MjIwNTg5MTc4IiwiaWF0IjoxNTc1Mzc3OTk4LCJleHAiOjE1NzU0NjQzOTgsInJvbGVzIjoiMCJ9._IszI-_DeYecbt2uLqmxoZKwTKAj1PCDodV3Sv78PJM");
        mvc.perform(MockMvcRequestBuilders.get("/Finance/showfinance")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
                .headers(httpHeaders)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}