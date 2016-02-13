#! /bin/sh
./activator "runMain slick.codegen.SourceCodeGenerator slick.driver.MySQLDriver com.mysql.jdbc.Driver $MYSQL_URL app dao $MYSQL_USER $MYSQL_PASS"
