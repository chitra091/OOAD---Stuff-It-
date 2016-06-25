/*
@Author:  Bikramjit Mandal
University of Texas at Dallas.
*/
package com.stuffit.www.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author Bikramjit
 */
public class myDataObject {
    
    
     static MysqlDataSource dataSource = new MysqlDataSource();
    
     public myDataObject(){
         dataSource.setUser("");
         dataSource.setPassword("");
         dataSource.setServerName("");
     }
     
     // This is the class that will handle all data object functionalities.
     
     
     public static void main(String args[]){
          
     }
    
}
