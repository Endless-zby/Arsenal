package club.zby.excel.poi;

/**
 * @Author: 赵博雅
 * @Date: 2020/3/5 22:09
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Testpoi {

    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(Testpoi.class);

    public List<String> perExcel(String url) throws IOException {
        File excel = new File(url);
        String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！
        Workbook wb;
        //根据文件后缀（xls/xlsx）进行判断
        if ( "xls".equals(split[1]) || "csv".equals(split[1])){
            FileInputStream fiStream = new FileInputStream(excel);   //文件流对象
            wb = new HSSFWorkbook(fiStream);
        }else{
            wb = new XSSFWorkbook(new FileInputStream(excel));
        }

        //开始解析
        Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

        int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
        int lastRowIndex = sheet.getLastRowNum();
        ArrayList<String> allList = new ArrayList<String>();
        int num = 0;
        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
            Row row = sheet.getRow(rIndex);
            if (row != null) {
                int firstCellIndex = row.getFirstCellNum();
                int lastCellIndex = row.getLastCellNum();
                for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                    Cell cell = row.getCell(cIndex);
                    if (cell != null) {
                        //每一行数据
                        String[] splits = cell.toString().split(";");
                        if(splits.length == 8){
                            String substring = splits[7].substring(1,splits[7].length() - 1);
                            LOGGER.info("第【{}】行数据：【{}】",num,substring);
                            allList.add(substring);
                            num ++;
                        }
                    }
                }
            }
        }
        LOGGER.info("Excel中符合要求的数据量：【{}】",num);
        LOGGER.info("^_^  Excel解析完成。。。等待初始化数据服务。。。。");
        return allList;
    }
}
