package com.hqkj.example.util;


import com.hqkj.example.constants.ResultConstant;
import jxl.Workbook;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.Label;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by ghl
 */
public class CommonExcel {

    /**
     *
     * @param fileName ：文件名 + .xls
     * @param Title ：标题数组
     * @param listContent ：内容集合
     * @param properties ：显示的属性数组
     * @param response ：响应对象
     * @return
     */
    public  final static String exportExcel(String fileName, String[] Title, List listContent,String[] properties, HttpServletResponse response) throws IOException, WriteException {
        String result= ResultConstant.SUCCESS;
        // 以下开始输出到EXCEL
        WritableWorkbook workbook = null;
        try {
            //定义输出流，以便打开保存对话框
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
            // 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            //定义输出流，以便打开保存对话框_______________________end

            /** **********创建工作簿************ */
            workbook = Workbook.createWorkbook(os);

            /** **********创建工作表************ */

//            WritableSheet sheet = workbook.createSheet("Sheet1", 0);

            /** **********设置纵横打印（默认为纵打）、打印纸***************** */
//            jxl.SheetSettings sheetset = sheet.getSettings();
//            sheetset.setProtected(false);

            /** ***************以下是EXCEL开头大标题，暂时省略********************* */
            //sheet.mergeCells(0, 0, colWidth, 0);
            //sheet.addCell(new Label(0, 0, "XX报表", wcf_center));
            /** ***************以下是EXCEL第一行列标题********************* */
/*            for (int i = 0; i < Title.length; i++) {
                sheet.addCell(new Label(i, 0,Title[i],setWorkFont(1)));
                sheet.setColumnView(i, 20);    //动态设置单元格
            }*/

            //创建工作簿页
            WritableSheet sheet = null;

            /** ***************以下是EXCEL正文数据********************* */
            int i=1;
            //创建变量，用于记录行数
            int rowNum = 0;
            //用于记录工作簿序号
            int sheetNum = 0;
            if(listContent != null){
                for(Object obj:listContent){
                    //判断是否大于6万条，大于6万重新创建一个工作簿
                    if(rowNum % 60000 == 0 && (rowNum >= 60000 || rowNum == 0)){   //每6万条数据分开
                        //创建工作簿页
                        sheet = workbook.createSheet("Sheet"+sheetNum, sheetNum);
                        jxl.SheetSettings sheetset = sheet.getSettings();
                        sheetset.setProtected(false);
                        sheetset.setVerticalFreeze(1);
//                        sheetset.setHorizontalFreeze(2);
                        sheetNum++;  //累加工作簿页码
                        i=1;  //从第1行开始  0为标题
                        /** ***************以下是EXCEL第一行列标题********************* */
                        for (int ti = 0; ti < Title.length; ti++) {
                            sheet.addCell(new Label(ti, 0,Title[ti],setWorkFont(1)));
                            sheet.setColumnView(ti, 20);    //动态设置单元格
                        }
                        /** ***************以下是EXCEL正文数据********************* */
                    }
                    rowNum ++;
                    for(int j = 0;j<properties.length;j++){
                        Field field = null;   //给定变量
                        try {
                            //获取本类中的属性，如果不存在会报异常
                             field = obj.getClass().getDeclaredField(properties[j]);
                        }catch (Exception e){
                            //出现异常后，从父类中获取属性值
                            field = obj.getClass().getSuperclass().getDeclaredField(properties[j]);
                        }
                        //设置field可以获取private值
                        field.setAccessible(true);
                        //获取具体的值
                        Object object = field.get(obj);
                        //判断是否为空
                        if(object != null){    //不为空，进行转换
                            String value =null;
                            //判断对象类型
                            if(object instanceof Date){
                                //调用函数转换
                                value = DateForToStr.dateToStr((Date)object);
                            }else{
                                value = object.toString();
                            }
                            sheet.addCell(new Label(j, i,value,setWorkFont(2)));

                        }else{
                            sheet.addCell(new Label(j, i," - ",setWorkFont(3)));
                        }
                    }
                    i++;
                }

            }
            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();
        } catch (Exception e) {
            result= ResultConstant.ERROR;
        }
        return result;
    }


    /**
     * 根据类型返回字体类型
     * @param type ： 1 -标识标题  2 -标识正文
     * @return
     */
    public static WritableCellFormat setWorkFont(Integer type){
        //创建字体类型
        WritableCellFormat fontType = null;
        //过滤类型
        if(type>0 && type < 4){   //在合法值的范围
            try {
                /** ************设置单元格字体************** */
                WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
                WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
                WritableFont RedFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);

                switch(type){
                    case 1:
                        // 用于标题居中
                        fontType = new WritableCellFormat(BoldFont);
                        fontType.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
                        break;
                    case 2:
                        // 用于正文居左
                        fontType = new WritableCellFormat(NormalFont);
                        fontType.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
                        break;
                    case 3:
                        // 用于正文居左
                        fontType = new WritableCellFormat(RedFont);
                        fontType.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
                        fontType.setBackground(Colour.LIGHT_GREEN);
                        break;
                }
                fontType.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
                fontType.setAlignment(Alignment.CENTRE); // 文字水平对齐
                fontType.setWrap(false); // 文字是否换行
                //返回字体类型
                return fontType;
            } catch (WriteException e) {
                e.printStackTrace();
                //出现异常，返回null
                return null;
            }
        }else{  //不合法
            //返回null
            return null;
        }
    }



}
