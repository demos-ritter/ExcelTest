/**
 *
 */
package com.demo.excel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.demo.common.Common;
import com.demo.excel.util.DbUtil;
import com.demo.excel.vo.Student;


public class SaveData2DB {

    @SuppressWarnings({"rawtypes"})
    public void save() throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        Student student = null;
        List<Student> list = xlsMain.readXls();

        for (int i = 0; i < list.size(); i++) {
            student = list.get(i);
            List l = DbUtil.selectOne(Common.SELECT_STUDENT_SQL + "'%" + student.getName() + "%'", student);
            if (!l.contains(1)) {
                DbUtil.insert(Common.INSERT_STUDENT_SQL, student);
            } else {
                System.out.println("No. = " + student.getNo() + " , Name = " + student.getName() + ", Age = " + student.getAge());
            }
        }
    }
}
